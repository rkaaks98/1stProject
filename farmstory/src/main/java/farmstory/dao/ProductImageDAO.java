package farmstory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.CountableDAO;
import farmstory.controller.admin.Product_enrollController;
import farmstory.dto.ProductImageDTO;
import farmstory.exception.DataAccessException;
import farmstory.util.ConnectionHelper;

public class ProductImageDAO implements CountableDAO<ProductImageDTO> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductDAO.class.getName());
  private final ConnectionHelper helper;
  private static final Logger logger =
      LoggerFactory.getLogger(Product_enrollController.class.getName());

  public ProductImageDAO(ConnectionHelper helper) {
    this.helper = helper;;
  }

  @Override
  public void insert(ProductImageDTO dto) throws DataAccessException {
    String sql =
        "INSERT INTO product_image (product_id, thumbnail_location, info_location, detail_location) VALUES (?, ?, ?, ?)";

    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(sql);
      psmt.setInt(1, dto.getProductid());
      psmt.setString(2, dto.getThumbnailLocation());
      psmt.setString(3, dto.getInfoLocation());
      psmt.setString(4, dto.getInfoLocation());

      int rowsAffected = psmt.executeUpdate();

      if (rowsAffected > 0) {
        LOGGER.info("상품 이미지가 정상적으로 등록되었습니다.");
      } else {
        LOGGER.warn("상품 이미지 등록에 실패하였습니다.");
      }

    } catch (NamingException | SQLException e) {
      String msg = String.format("데이터베이스 작업 중 예외가 발생하였습니다: %s", e.getMessage());
      throw new DataAccessException(msg, e);
    }
  }

  @Override
  public ProductImageDTO select(ProductImageDTO dto) throws DataAccessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ProductImageDTO> selectAll() throws DataAccessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(ProductImageDTO dto) throws DataAccessException {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(ProductImageDTO dto) throws DataAccessException {
    // TODO Auto-generated method stub

  }

  @Override
  public int count() throws DataAccessException, IllegalArgumentException {
    // TODO Auto-generated method stub
    return 0;
  }

  public int insertProductImage(ProductImageDTO imageDTO) throws DataAccessException {
    // SQL 쿼리 정의
    String sql =
        "INSERT INTO product_image (product_id, thumbnail_location, info_location, detail_location) "
            + "VALUES (?, ?, ?, ?)";

    // 데이터베이스 연결 및 쿼리 실행
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(sql)) {

      // PreparedStatement에 값 설정
      psmt.setInt(1, imageDTO.getProductid()); // productId
      psmt.setString(2, imageDTO.getThumbnailLocation()); // thumbnailLocation
      psmt.setString(3, imageDTO.getInfoLocation()); // infoLocation
      psmt.setString(4, imageDTO.getDetailLocation()); // detailLocation

      // 쿼리 실행
      psmt.executeUpdate();

    } catch (NamingException | SQLException e) {
      String msg = String.format("데이터베이스 작업 중 예외가 발생하였습니다: %s", e.getMessage());
      throw new DataAccessException(msg, e);
    }
    return 0;
  }
}
