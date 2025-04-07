package farmstory.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import farmstory.util.ResponseBodyWriter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/auth")
public class EmailAuthenticator extends HttpServlet {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailAuthenticator.class.getName());
  private static final String AUTH_CODE_ATTR_NAME = "emailAuthCode";
  private final Properties props = new Properties();
  private final Properties gmailConf = new Properties();

  @Override
  public void init(ServletConfig config) throws ServletException {
    try (
        InputStream in = config.getServletContext()
            .getResourceAsStream("/WEB-INF/resources/application.properties");
        InputStream gmailConfIn = config.getServletContext()
            .getResourceAsStream("/WEB-INF/resources/gmailconf.properties")) {
      props.load(in);
      gmailConf.load(gmailConfIn);
    } catch (IOException e) {
      throw new ServletException("Properties 로드 중 문제가 발생했습니다.", e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
    String email = req.getParameter("address");
    String sender = (String) props.get("gmail.sender");
    String appPassword = (String) props.get("gmail.password");
    String title = (String) props.get("gmail.title");
    String content = String.format("<h3>팜스토리 인증 코드: %d</h3>", code);

    Session mailSession = Session.getInstance(gmailConf, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(sender, appPassword);
      }
    });

    Message message = new MimeMessage(mailSession);

    try {
      message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
      message.setSubject(title);
      message.setContent(content, "text/html;charset=utf-8");
      Transport.send(message);
      ResponseBodyWriter.write(true, "", HttpServletResponse.SC_OK, resp);
      String msg = String.format("%s로 인증코드를 전송하였음", email);
      LOGGER.debug(msg);
    } catch (MessagingException e) {
      ResponseBodyWriter.write(false, "인증번호 전송 실패", HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          resp);
    }
    HttpSession session = req.getSession();
    session.setAttribute(AUTH_CODE_ATTR_NAME, code);
    session.setMaxInactiveInterval(3 * 60); // 세션 만료 3분
    resp.flushBuffer();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    JsonObject json = JsonParser.parseReader(req.getReader()).getAsJsonObject();
    String userAuthCode = json.get("code").getAsString();

    HttpSession session = req.getSession();
    String sessAuthCode = Integer.toString((int) session.getAttribute("emailAuthCode"));

    if (userAuthCode.endsWith(sessAuthCode)) {
      ResponseBodyWriter.write(true, "", HttpServletResponse.SC_OK, resp);
      session.removeAttribute(AUTH_CODE_ATTR_NAME);
      session.setAttribute("emailAuthed", true);
    } else {
      ResponseBodyWriter.write(false, "올바르지 않은 인증코드", HttpServletResponse.SC_BAD_REQUEST, resp);
    }

    resp.flushBuffer();
  }
}