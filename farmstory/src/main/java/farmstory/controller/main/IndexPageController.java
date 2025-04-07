package farmstory.controller.main;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.CountableDAO;
import farmstory.dao.ArticleDAO;
import farmstory.dao.ProductDAO;
import farmstory.dto.ArticleDTO;
import farmstory.dto.ProductDTO;
import farmstory.exception.DataAccessException;
import farmstory.service.CountableDefaultService;
import farmstory.service.CountableService;
import farmstory.util.ConnectionHelper;
import farmstory.util.ResponseBodyWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 날짜 : 2025 이름 : 홍길동 내용 :
 * 
 */

@WebServlet("/index.do")
public class IndexPageController extends HttpServlet {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER = LoggerFactory.getLogger(IndexPageController.class.getName());

  private CountableService<ProductDTO> prodService;
  private CountableService<ArticleDTO> artService;

  @Override
  public void init() throws ServletException {
    ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
    CountableDAO<ProductDTO> prodDao = new ProductDAO(helper);
    CountableDAO<ArticleDTO> artDao = new ArticleDAO(helper);
    prodService = new CountableDefaultService<>(prodDao);
    artService = new CountableDefaultService<>(artDao);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      // ATTENTION Insert dummy data into product and article tables, or null pointer exception will
      // be thrown
      List<ProductDTO> products = prodService.getAll();
      List<ArticleDTO> articles = artService.getAll();
      req.setAttribute("products", products);
      req.setAttribute("articles", articles);
      req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    } catch (DataAccessException | ServletException | IOException e) {
      LOGGER.error(e.getMessage(), e);
      ResponseBodyWriter.write(false, "Internal server error",
          HttpServletResponse.SC_INTERNAL_SERVER_ERROR, resp);
    }
  }
}
