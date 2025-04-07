package farmstory.controller.find;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import farmstory.dao.UserDAO;
import farmstory.dto.UserDTO;
import farmstory.exception.DataAccessException;
import farmstory.service.UserService;
import farmstory.util.ConnectionHelper;
import farmstory.util.ResponseBodyWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/find/findIdResult.do")
public class FindIdResultController extends HttpServlet {

  private static final long serialVersionUID = 124541279112446L;
  private UserDAO dao;
  private UserService service;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void init(ServletConfig config) throws ServletException {
    this.dao = new UserDAO(new ConnectionHelper("jdbc/farmstory"));
    this.service = new UserService(dao);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	  
	  RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/find/find-id-result.jsp");
		dispatcher.forward(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	  resp.setContentType("application/json;charset=UTF-8");

      String email = req.getParameter("email");
      logger.debug("요청된 이메일: " + email);
      
      UserDTO user = dao.selectResult(email);
      
      JsonObject json = new JsonObject();

      if (user != null) {
          json.addProperty("status", "success");
          json.addProperty("name", user.getName());
          json.addProperty("id", user.getId());
          json.addProperty("email", user.getEmail());
          json.addProperty("register_date", user.getRegisterDate().toString());
          
          logger.debug("응답 JSON: " + json.toString());
      } else {
          json.addProperty("status", "error");
          json.addProperty("message", "해당 이메일의 사용자가 없습니다.");
      }

      resp.getWriter().write(json.toString());
	  
  }

}
