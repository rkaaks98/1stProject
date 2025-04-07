package farmstory.controller.my;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.dao.ProductDAO;
import farmstory.dto.ProductDTO;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/my/orderList.do")
public class OrderListController extends HttpServlet {

  private static final long serialVersionUID = 12454121545446L;
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  ProductDAO dao;

  @Override
  public void init() throws ServletException {
    this.dao = new ProductDAO(null);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    HttpSession session = req.getSession();
    String userId = (String) session.getAttribute("userId");

    if (userId == null) {
	      resp.sendRedirect("/farmstory/signin"); // 로그인 안 된 경우 로그인 페이지로 리다이렉트
	      return;
	    }

    // 유저가 구매한 상품 목록 조회
    List<ProductDTO> selectAllOrder = dao.selectAllOrder(userId);
    req.setAttribute("selectAllOrder", selectAllOrder);

    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/my/order-list.jsp");
    dispatcher.forward(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

  }

}
