package farmstory.controller.product;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.CountableDAO;
import farmstory.DataAccessObject;
import farmstory.dao.ProductDAO;
import farmstory.dao.ProductImageDAO;
import farmstory.dto.ProductDTO;
import farmstory.dto.ProductImageDTO;
import farmstory.exception.DataAccessException;
import farmstory.service.CountableDefaultService;
import farmstory.service.CountableService;
import farmstory.service.DefaultService;
import farmstory.service.Service;
import farmstory.util.ConnectionHelper;
import farmstory.util.ResponseBodyWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product.do")
public class ProductController extends HttpServlet {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class.getName());

  private CountableService<ProductDTO> prodService = null;
  private Service<ProductImageDTO> imageService = null;

  @Override
  public void init() throws ServletException {
    ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
    CountableDAO<ProductDTO> dao = new ProductDAO(helper);
    DataAccessObject<ProductImageDTO> imgDao = new ProductImageDAO(helper);
    this.prodService = new CountableDefaultService<>(dao);
    this.imageService = new DefaultService<>(imgDao);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String productId = req.getParameter("pid");

    try {
      if (productId == null) { // pid 쿼리 스트링이 없는 경우, 상품 리스트 출력
        LOGGER.debug("상품 리스트를 출력합니다...");
        List<ProductDTO> products = prodService.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/shopping/list.jsp").forward(req, resp);
      } else { // pid 쿼리 스트링이 있는 경우, pid에 해당하는 상품의 상세 페이지 출력
        ProductDTO target = new ProductDTO();
        target.setId(Integer.parseInt(productId));

        ProductDTO product = prodService.get(target);
        if (product.getName() != null) {// 조회 결과가 있는 경우
          req.setAttribute("product", product);
          req.getRequestDispatcher("/WEB-INF/shopping/detail.jsp").forward(req, resp);
        } else { // 조회 결과가 없는 경우
          ResponseBodyWriter.write(false, "요청한 상품을 찾을 수 없습니다", HttpServletResponse.SC_NOT_FOUND,
              resp);
        }
      }
    } catch (DataAccessException | NumberFormatException e) {
      LOGGER.error(e.getMessage(), e);
      ResponseBodyWriter.write(false, "Internal Server Error",
          HttpServletResponse.SC_INTERNAL_SERVER_ERROR, resp);
    }
  }
}
