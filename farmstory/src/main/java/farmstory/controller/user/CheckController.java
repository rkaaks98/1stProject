package farmstory.controller.user;

import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.dao.UserDAO;
import farmstory.exception.DataAccessException;
import farmstory.service.UserService;
import farmstory.util.ConnectionHelper;
import farmstory.util.ResponseBodyWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/check")
public class CheckController extends HttpServlet {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER = LoggerFactory.getLogger(CheckController.class.getName());
  private UserService service;

  @Override
  public void init() throws ServletException {
    ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
    UserDAO dao = new UserDAO(helper);
    this.service = new UserService(dao);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String type = req.getParameter("type"); // 중복검사를 하고자 하는 데이터의 유형;
    String value = req.getParameter("value"); // 중복검사를 하고자 하는 실제 데이터

    String message = String.format("%s에 대한 중복 검사 진행...", req.getQueryString());
    LOGGER.debug(message);

    if (type == null || value == null) { // type 또는 value가 null인 경우
      ResponseBodyWriter.write(false, "유효하지 않은 타입\ntype={\"id\", \"nickname\"}",
          HttpServletResponse.SC_BAD_REQUEST, resp);
      resp.flushBuffer();
      return;
    }

    try {
      int count = service.count(type, value);
      if (count > 0) {
        String msg = String.format("중복되는 데이터: %s", type);
        ResponseBodyWriter.write(false, msg, HttpServletResponse.SC_CONFLICT, resp);
      } else {
        ResponseBodyWriter.write(true, "", HttpServletResponse.SC_OK, resp);
        // TODO: Store check status at HTTP session.
        HttpSession session = req.getSession();
        String attrName = String.format("%sChecked", type);
        session.setAttribute(attrName, true);
        String msg = String.format("%s=%s에 대한 중복 검사를 성공적으로 완료하였습니다.", type, value);
        LOGGER.debug(msg);
      }
    } catch (DataAccessException e) {
      String msg = String.format("%s%n%s", e.getCause().toString(), e.getMessage().toString());
      LOGGER.debug(msg);
      ResponseBodyWriter.write(false, "Internal server error",
          HttpServletResponse.SC_INTERNAL_SERVER_ERROR, resp);
    }
    resp.flushBuffer();
  }
}
