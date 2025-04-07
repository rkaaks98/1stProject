package farmstory.controller.admin;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.DataAccessObject;
import farmstory.dao.ProductDAO;
import farmstory.dto.ProductDTO;
import farmstory.service.DefaultService;
import farmstory.service.Service;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/product-list.do")
public class Product_listController extends HttpServlet {
  private static final long serialVersionUID = 1421234456356053821L;
  private static final Logger LOGGER =
      LoggerFactory.getLogger(Product_listController.class.getName());
  private Service<ProductDTO> service;

  @Override
  public void init() throws ServletException {
    ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
    DataAccessObject<ProductDTO> dao = new ProductDAO(helper);
    service = new DefaultService<>(dao);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      // 전체 상품 목록 가져오기 (전체 데이터)
      List<ProductDTO> products = service.getAll();

      // 페이지 번호 파라미터 가져오기, 기본값은 1
      String pg = req.getParameter("pg");
      int currentPage = (pg != null) ? Integer.parseInt(pg) : 1;

      int pageSize = 10; // 한 페이지에 보여줄 데이터 수
      int total = products.size(); // 전체 데이터 개수
      int lastPageNum = (int) Math.ceil((double) total / pageSize); // 마지막 페이지 번호 계산

      // 현재 페이지에 해당하는 데이터만 추출
      int startIndex = (currentPage - 1) * pageSize;
      int endIndex = Math.min(startIndex + pageSize, total); // 페이지 마지막 인덱스

      // 페이지에 맞는 데이터만 가져오기 (subList)
      List<ProductDTO> currentPageproducts = products.subList(startIndex, endIndex);

      // 페이지네이션 처리
      req.setAttribute("articles", currentPageproducts);
      req.setAttribute("currentPage", currentPage);
      req.setAttribute("lastPageNum", lastPageNum);

      req.setAttribute("pageName", "product-list");

      // 페이지 범위 계산 (예: 1, 2, 3, 4, 5 페이지 범위)
      int startPage = ((currentPage - 1) / 5) * 5 + 1;
      int endPage = Math.min(startPage + 4, lastPageNum);
      req.setAttribute("startPage", startPage);
      req.setAttribute("endPage", endPage);

      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product-list.jsp");
      dispatcher.forward(req, resp);
    } catch (Exception e) {
      LOGGER.error("게시글 목록 조회 중 오류 발생", e);
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류 발생");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {}
}
