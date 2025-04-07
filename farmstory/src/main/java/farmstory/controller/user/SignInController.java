package farmstory.controller.user;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import farmstory.DataAccessObject;
import farmstory.dao.UserDAO;
import farmstory.dto.UserDTO;
import farmstory.exception.DataAccessException;
import farmstory.service.DefaultService;
import farmstory.service.Service;
import farmstory.util.ConnectionHelper;
import farmstory.util.ResponseBodyWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signin")
public class SignInController extends HttpServlet {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER = LoggerFactory.getLogger(SignInController.class.getName());

  private Service<UserDTO> service;

  private String toHexString(byte[] buffer) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < buffer.length; i++) {
      byte b = buffer[i];
      // 8 비트 길이를 32비트로 확장, MSB = 0, Signed -> Unsigned 변환 과정에서의 arithmetic right shift에 영향받지 않음
      String hexString = Integer.toHexString(0xff & b);
      if (hexString.length() == 1) { // e.g 00000000 00000000 00000000 00000001 = 0x0"1"
        builder.append('0');
      }
      builder.append(hexString);
    }
    return builder.toString();
  }

  @Override
  public void init() throws ServletException {
    ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
    DataAccessObject<UserDTO> dao = new UserDAO(helper);
    service = new DefaultService<>(dao);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/login.jsp");
    dispatcher.forward(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    JsonObject json = JsonParser.parseReader(req.getReader()).getAsJsonObject();

    String id = json.get("id").getAsString();
    String password = json.get("password").getAsString();

    UserDTO dto = new UserDTO();
    dto.setId(id);
    dto.setPassword(password);


    try {
      UserDTO result = service.get(dto);

      if (result.getId() == null) {
        ResponseBodyWriter.write(false, "존재하지 않는 계정입니다.", HttpServletResponse.SC_NOT_FOUND, resp);
        return;
      }

      byte[] hashedpw =
          MessageDigest.getInstance("SHA256").digest(password.getBytes(StandardCharsets.UTF_8));
      String hashedString = toHexString(hashedpw);

      if (!result.getPassword().equals(hashedString)) {// 비밀번호가 일치하지 않는 경우
        ResponseBodyWriter.write(false, "비밀번호가 일치하지 않습니다.", HttpServletResponse.SC_BAD_REQUEST,
            resp);
      } else {
        HttpSession session = req.getSession();
        session.setAttribute("sessUser", result);
        session.setMaxInactiveInterval(60 * 10 * 6); // 1시간 동안 세션 유지. 이후 세션 만료(다시 로그인해야함)
        resp.sendRedirect("/farmstory/index.do");
      }

    } catch (DataAccessException | NoSuchAlgorithmException e) {
      LOGGER.error("사용자 로그인 요청을 처리하던 중 예외가 발생하였습니다.", e);
      ResponseBodyWriter.write(false, "Internal Server Error",
          HttpServletResponse.SC_INTERNAL_SERVER_ERROR, resp);
    }
  }
}
