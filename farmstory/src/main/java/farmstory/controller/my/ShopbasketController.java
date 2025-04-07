package farmstory.controller.my;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.CountableDAO;
import farmstory.controller.user.SignUpController;
import farmstory.dao.OrderDAO;
import farmstory.dto.OrderDTO;
import farmstory.service.CountableDefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/my/shopbasket.do")
public class ShopbasketController extends HttpServlet {

  private static final long serialVersionUID = 124541245425112446L;
  private static final Logger logger = LoggerFactory.getLogger(SignUpController.class.getName());

  

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    

    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/my/shopbasket.jsp");
    dispatcher.forward(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    

  }
}