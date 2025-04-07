package farmstory.controller.term;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.DataAccessObject;
import farmstory.dao.TermDAO;
import farmstory.dto.TermDTO;
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

@WebServlet("/terms")
public class TermsController extends HttpServlet {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER = LoggerFactory.getLogger(TermsController.class.getName());

  private Service<TermDTO> service;

  @Override
  public void init() throws ServletException {
    ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
    DataAccessObject<TermDTO> dao = new TermDAO(helper);
    service = new DefaultService<>(dao);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    Object attr = session.getAttribute("agreed");
    if (attr != null) { // session attribute null 체크
      boolean didAgree = (boolean) attr; // sessoin attribute가 null이 아닌 경우 boolean으로 캐스팅
      if (didAgree) { // 유저가 약관에 동의한 채 다시 접근하는 경우
        resp.sendRedirect("/farmstory/signup"); // 회원가입 페이지로 리디렉션
        return;
      }
    }

    try {
      List<TermDTO> terms = service.getAll();
      req.setAttribute("terms", terms);
      RequestDispatcher dp = req.getRequestDispatcher("/WEB-INF/user/terms.jsp");
      dp.forward(req, resp);
    } catch (DataAccessException | ServletException | IOException e) {
      ResponseBodyWriter.write(false, "Internal Server Error",
          HttpServletResponse.SC_INTERNAL_SERVER_ERROR, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    boolean isAgreed = Boolean.parseBoolean((String) req.getParameter("agreed"));
    if (isAgreed) {
      req.getSession().setAttribute("agreed", true);
      req.getSession().setMaxInactiveInterval(10 * 60); // 10분 후 세션 만료
      LOGGER.debug("사용자가 약관에 동의하였습니다. 회원가입 페이지로 리다이렉트...");
      resp.sendRedirect("/farmstory/signup");
    } else {
      LOGGER.debug("비정상적인 접근. 사용자가 약관에 동의하지 않았습니다.");
      ResponseBodyWriter.write(false, "비정상적인 접근. 이용약관에 동의하지 않음", HttpServletResponse.SC_BAD_REQUEST,
          resp);
    }
  }
}
