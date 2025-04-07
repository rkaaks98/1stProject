package farmstory.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.dao.ProductDAO;
import farmstory.dao.ProductImageDAO;
import farmstory.dto.CompanyDTO;
import farmstory.dto.ProductDTO;
import farmstory.dto.ProductImageDTO;
import farmstory.exception.DataAccessException;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/admin/product-enroll.do")
@MultipartConfig
public class Product_enrollController extends HttpServlet {
  private static final long serialVersionUID = 587656470097925202L;
  private static final Logger logger =
      LoggerFactory.getLogger(Product_enrollController.class.getName());
  private ProductDAO productDAO;
  private ProductImageDAO productImageDAO;

  @Override
  public void init() throws ServletException {
    try {
      ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
      productDAO = new ProductDAO(helper);
      productImageDAO = new ProductImageDAO(helper);
    } catch (Exception e) {
      // logger 사용 시 이제 정상적으로 인스턴스 메서드를 호출할 수 있습니다.
      logger.error(e.getMessage(), e); // logger 인스턴스를 통해 error 메서드를 호출
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("pageName", "product-enroll");
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product-enroll.jsp");
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String name = req.getParameter("prodName");
      String category = req.getParameter("cateNo");
      int price = Integer.parseInt(req.getParameter("prodPrice"));
      int point = price / 100;
      int discountRate = Integer.parseInt(req.getParameter("prodDiscount"));
      int deliveryFee = Integer.parseInt(req.getParameter("delivery"));
      int stock = Integer.parseInt(req.getParameter("prodStock"));

      CompanyDTO company = new CompanyDTO();
      company.setCompanyName("테스트 회사");
      company.setManagerName("최지현");
      company.setContact("010-9999-9999");
      company.setAddress("행복광역시 행운구 천국동 101-11");

      String uploadDir = getServletContext().getRealPath("/uploads");
      File uploadDirectory = new File(uploadDir);
      if (!uploadDirectory.exists()) {
        uploadDirectory.mkdir();
      }

      String thumbnailLocation = saveImage(req.getPart("multImage1"), uploadDir);
      String infoLocation = saveImage(req.getPart("multImage2"), uploadDir);
      String detailLocation = saveImage(req.getPart("multImage3"), uploadDir);

      // 상품 이미지 DTO 생성 및 저장
      ProductImageDTO imageDTO = new ProductImageDTO();
      if (thumbnailLocation != null) {
        imageDTO.setThumbnailLocation(thumbnailLocation);
      }
      if (infoLocation != null) {
        imageDTO.setInfoLocation(infoLocation);
      }
      if (detailLocation != null) {
        imageDTO.setDetailLocation(detailLocation);
      }
      // 이미지 DTO가 유효하면 DB에 저장
      if (imageDTO.getThumbnailLocation() != null || imageDTO.getInfoLocation() != null
          || imageDTO.getDetailLocation() != null) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setImage(imageDTO);
        productDTO.setCompany(company);
        productDTO.setName(name);
        productDTO.setCategory(category);
        productDTO.setPrice(price);
        productDTO.setPoint(point);
        productDTO.setDiscountRate(discountRate);
        productDTO.setDeliveryFee(deliveryFee);
        productDTO.setStock(stock);

        productDAO.insert(productDTO);

        int productId = productDTO.getId();
        logger.info("상품 등록 성공: ID = {}", productId);
      }

      resp.sendRedirect("/farmstory/admin/product-list.do");


    } catch (DataAccessException e) {
      logger.error("상품 등록 오류", e);
      req.setAttribute("error", "상품 등록에 실패했습니다. 다시 시도해주세요.");
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product-enroll.jsp");
      dispatcher.forward(req, resp);
    } catch (Exception e) {
      logger.error("예기치 못한 오류", e);
      req.setAttribute("error", "알 수 없는 오류가 발생했습니다.");
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product-enroll.jsp");
      dispatcher.forward(req, resp);
    }
  }

  private String saveImage(Part part, String uploadDir) throws IOException {
    if (part == null || part.getSize() == 0) {
      return null; // 이미지가 없으면 null 반환
    }
    String fileName = UUID.randomUUID().toString(); // 파일 이름을 UUID로 생성하여 충돌 방지
    String fileExtension = getFileExtension(part.getSubmittedFileName());
    String filePath = uploadDir + File.separator + fileName + fileExtension;

    part.write(filePath);

    return "/uploads/" + fileName + fileExtension; // 저장된 파일 경로 반환
  }

  private String getFileExtension(String fileName) {
    if (fileName != null && fileName.lastIndexOf('.') > 0) {
      return fileName.substring(fileName.lastIndexOf('.'));
    }
    return "";
  }
}
