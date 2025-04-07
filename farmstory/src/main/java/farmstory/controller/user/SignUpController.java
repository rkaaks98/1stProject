package farmstory.controller.user;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonElement;
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

@WebServlet("/signup")
public class SignUpController extends HttpServlet {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class.getName());

  private Service<UserDTO> service;

  private UserDTO toUser(JsonObject obj) {
    Map<String, JsonElement> jsonMap = obj.asMap();
    String id = jsonMap.get("id").getAsString();
    String password = jsonMap.get("password").getAsString();
    String name = jsonMap.get("name").getAsString();
    String nickname = jsonMap.get("nickname").getAsString();
    String email = jsonMap.get("email").getAsString();
    String phoneNum = jsonMap.get("phoneNum").getAsString();
    String zip = jsonMap.get("zip").getAsString();
    String address = jsonMap.get("address").getAsString();
    String addressDetail = jsonMap.get("addressDetail").getAsString();

    UserDTO dto = new UserDTO();
    dto.setId(id);
    dto.setPassword(password);
    dto.setName(name);
    dto.setNickname(nickname);
    dto.setEmail(email);
    dto.setPhoneNum(phoneNum);
    dto.setZip(zip);
    dto.setAddress(address);
    dto.setAddressDetail(addressDetail);
    return dto;
  }

  @Override
  public void init() throws ServletException {
    DataAccessObject<UserDTO> dao = new UserDAO(new ConnectionHelper("jdbc/farmstory"));
    this.service = new DefaultService<>(dao);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    Object attr = session.getAttribute("agreed");
    if (attr == null) {
      resp.sendRedirect("/farmstory/terms");
    } else {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/register.jsp");
      dispatcher.forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    boolean isIdChecked = (boolean) session.getAttribute("idChecked");
    boolean isNickChecked = (boolean) session.getAttribute("nicknameChecked");
    boolean isEmailAuthed = (boolean) session.getAttribute("emailAuthed");

    if (!isIdChecked || !isNickChecked || !isEmailAuthed) {
      LOGGER.debug("인증받지 않은 사용자 감지");
      ResponseBodyWriter.write(false, "중복검사 또는 이메일 인증을 받지 않은 사용자",
          HttpServletResponse.SC_BAD_REQUEST, resp);
      resp.flushBuffer();
      return;
    } else {
      session.removeAttribute("idChecked");
      session.removeAttribute("nicknameChecked");
      session.removeAttribute("emailAuthed");
      JsonObject obj = JsonParser.parseReader(req.getReader()).getAsJsonObject();
      String pw = obj.get("password").getAsString();
      String pwConfirm = obj.get("passwordConfirm").getAsString();
      if (!pw.equals(pwConfirm)) {
        LOGGER.debug("일치하지 않는 비밀번호 감지");
        ResponseBodyWriter.write(false, "일치하지 않는 비밀번호입니다.", HttpServletResponse.SC_BAD_REQUEST,
            resp);
        resp.flushBuffer();
        return;
      }

      try {
        UserDTO dto = toUser(obj);
        service.create(dto);
        session.removeAttribute("idChecked");
        session.removeAttribute("nicknameChecked");
        session.removeAttribute("emailAuthed");
        session.removeAttribute("agreed");

        String msg = String.format("%s 유저를 성공적으로 생성하였습니다", dto.getId());
        LOGGER.debug(msg);
        resp.sendRedirect("/farmstory/signin");
      } catch (DataAccessException | IOException e) {
        String msg = String.format("%s%n%s", e.getMessage(), e.getCause().toString());
        LOGGER.debug(msg);
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }

    }

  }
}
