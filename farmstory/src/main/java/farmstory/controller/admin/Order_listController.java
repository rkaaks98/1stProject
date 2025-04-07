package farmstory.controller.admin;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.dao.OrderDAO;
import farmstory.dto.OrderDTO;
import farmstory.service.CountableDefaultService;
import farmstory.service.CountableService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/order-list.do")
public class Order_listController extends HttpServlet {
  private static final long serialVersionUID = 161266416260600026L;
  private static final Logger logger =
      LoggerFactory.getLogger(Order_listController.class.getName());
  private CountableService<OrderDTO> service;

  @Override
  public void init() throws ServletException {
    try {
      ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
      OrderDAO dao = new OrderDAO(helper);
      this.service = new CountableDefaultService<>(dao);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    try {
      // 전체 주문 목록 가져오기 (전체 데이터)
      List<OrderDTO> orders = service.getAll();

      // 페이지 번호 파라미터 가져오기, 기본값은 1
      String pg = req.getParameter("pg");
      int currentPage = (pg != null) ? Integer.parseInt(pg) : 1;

      int pageSize = 10; // 한 페이지에 보여줄 데이터 수
      int total = orders.size(); // 전체 데이터 개수
      int lastPageNum = (int) Math.ceil((double) total / pageSize); // 마지막 페이지 번호 계산

      // 현재 페이지에 해당하는 데이터만 추출
      int startIndex = (currentPage - 1) * pageSize;
      int endIndex = Math.min(startIndex + pageSize, total); // 페이지 마지막 인덱스

      // 페이지에 맞는 데이터만 가져오기 (subList)
      List<OrderDTO> currentPageOrders = orders.subList(startIndex, endIndex);

      // 페이지네이션 처리
      req.setAttribute("articles", currentPageOrders);
      req.setAttribute("currentPage", currentPage);
      req.setAttribute("lastPageNum", lastPageNum);
      req.setAttribute("pageName", "order-list");

      // 페이지 범위 계산 (예: 1, 2, 3, 4, 5 페이지 범위)
      int startPage = ((currentPage - 1) / 5) * 5 + 1;
      int endPage = Math.min(startPage + 4, lastPageNum);
      req.setAttribute("startPage", startPage);
      req.setAttribute("endPage", endPage);

      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/order-list.jsp");
      dispatcher.forward(req, resp);
    } catch (Exception e) {
      logger.error("게시글 목록 조회 중 오류 발생", e);
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류 발생");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {}
}
