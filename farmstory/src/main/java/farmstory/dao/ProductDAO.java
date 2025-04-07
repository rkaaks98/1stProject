package farmstory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import farmstory.CountableDAO;
import farmstory.dto.CompanyDTO;
import farmstory.dto.ProductDTO;
import farmstory.dto.ProductImageDTO;
import farmstory.exception.DataAccessException;
import farmstory.util.ConnectionHelper;
import farmstory.util.Query;

public class ProductDAO implements CountableDAO<ProductDTO> {
  private final ConnectionHelper helper;

  public ProductDAO(ConnectionHelper helper) {
    this.helper = helper;
  }

  private ProductDTO createProduct(ResultSet rs) throws SQLException {
    ProductDTO product = new ProductDTO();
    ProductImageDTO image = new ProductImageDTO();
    CompanyDTO company = new CompanyDTO();
    product.setId(rs.getInt("id"));
    company.setId(rs.getInt("company_id"));
    company.setManagerName(rs.getString("manager_name"));
    company.setContact(rs.getString("contact"));
    company.setAddress(rs.getString("addr"));
    product.setCompany(company);
    product.setName(rs.getString("name"));
    product.setCategory(rs.getString("category"));
    product.setPrice(rs.getInt("price"));
    product.setPoint(rs.getInt("point"));
    product.setDiscountRate(rs.getInt("discount_rate"));
    product.setDeliveryFee(rs.getInt("delivery_fee"));
    product.setStock(rs.getInt("stock"));
    image.setId(rs.getInt("image_id"));
    image.setThumbnailLocation(rs.getString("thumbnail_location"));
    image.setInfoLocation(rs.getString("info_location"));
    image.setDetailLocation(rs.getString("detail_location"));
    product.setImage(image);
    product.setRegisterDate(rs.getString("register_date"));
    return product;
  }

  @Override
  public void insert(ProductDTO dto) throws DataAccessException {
    try (Connection conn = helper.getConnection();) {
      // conn.setAutoCommit(false);
      CompanyDTO company = dto.getCompany();
      ProductImageDTO image = dto.getImage();

      PreparedStatement companyPsmt =
          conn.prepareStatement(Query.INSERT_COMPANY, Statement.RETURN_GENERATED_KEYS);
      companyPsmt.setString(1, company.getCompanyName());
      companyPsmt.setString(2, company.getManagerName());
      companyPsmt.setString(3, company.getContact());
      companyPsmt.setString(4, company.getAddress());
      companyPsmt.executeUpdate();
      ResultSet companyRs = companyPsmt.getGeneratedKeys();
      int compId = 0;
      if (companyRs.next()) {
        compId = companyRs.getInt(1);
      }

      PreparedStatement imagePsmt =
          conn.prepareStatement(Query.INSERT_PROD_IMAGE, Statement.RETURN_GENERATED_KEYS);
      imagePsmt.setString(1, image.getThumbnailLocation());
      imagePsmt.setString(2, image.getInfoLocation());
      imagePsmt.setString(3, image.getDetailLocation());
      imagePsmt.executeUpdate();
      ResultSet imageRs = imagePsmt.getGeneratedKeys();
      int imageId = 0;
      if (imageRs.next()) {
        imageId = imageRs.getInt(1);
      }

      PreparedStatement productPsmt = conn.prepareStatement(Query.INSERT_PRODUCT);
      productPsmt.setInt(1, compId);
      productPsmt.setString(2, dto.getName());
      productPsmt.setString(3, dto.getCategory());
      productPsmt.setInt(4, dto.getPrice());
      productPsmt.setInt(5, dto.getPoint());
      productPsmt.setFloat(6, dto.getDiscountRate());
      productPsmt.setInt(7, dto.getDeliveryFee());
      productPsmt.setInt(8, dto.getStock());
      productPsmt.setInt(9, imageId);
      productPsmt.executeUpdate();

      // conn.commit();

      companyPsmt.close();
      productPsmt.close();
      imagePsmt.close();

    } catch (NamingException | SQLException e) {
      throw new DataAccessException("상품을 등록하는 도중 예외가 발생했습니다.", e);
    }
  }

  @Override
  public ProductDTO select(ProductDTO dto) throws DataAccessException {
    ProductDTO product = null;
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.SELECT_PRODUCT)) {
      psmt.setInt(1, dto.getId());
      ResultSet rs = psmt.executeQuery();
      if (rs.next()) {
        product = createProduct(rs);
      }
    } catch (NamingException | SQLException e) {
      throw new DataAccessException("상품을 조회하는 도중 예외가 발생했습니다.", e);
    }
    return product;
  }

  @Override
  public List<ProductDTO> selectAll() throws DataAccessException {
    List<ProductDTO> productList = new ArrayList<>();
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.SELECT_ALL_PRODUCT);
        ResultSet rs = psmt.executeQuery()) {
      while (rs.next()) {
        ProductDTO product = createProduct(rs);
        productList.add(product);
      }
    } catch (NamingException | SQLException e) {
      throw new DataAccessException("상품 목록을 조회하는 도중 예외가 발생했습니다.", e);
    }
    return productList;
  }

  @Override
  public void update(ProductDTO dto) throws DataAccessException {
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.UPDATE_PRODUCT)) {
      psmt.setInt(1, dto.getCompany().getId());
      psmt.setString(2, dto.getName());
      psmt.setString(3, dto.getCategory());
      psmt.setInt(4, dto.getPrice());
      psmt.setInt(5, dto.getPoint());
      psmt.setFloat(6, dto.getDiscountRate());
      psmt.setInt(7, dto.getDeliveryFee());
      psmt.setInt(8, dto.getStock());
      psmt.setInt(9, dto.getImage().getId());

      psmt.executeUpdate();
    } catch (NamingException | SQLException e) {
      throw new DataAccessException("상품 데이터를 수정하는 도중 예외가 발생했습니다.", e);
    }
  }

  @Override
  public void delete(ProductDTO dto) throws DataAccessException {
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.DELETE_PRODUCT)) {
      psmt.setInt(1, dto.getId());
    } catch (NamingException | SQLException e) {
      throw new DataAccessException("상품 데이터를 삭제하는 도중 예외가 발생했습니다.", e);
    }
  }

  @Override
  public int count() throws DataAccessException {
    int count = 0;
    try (Connection conn = helper.getConnection(); Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(Query.COUNT_PRODUCT);
      if (rs.next()) {
        count = rs.getInt(1);
      }
    } catch (NamingException | SQLException e) {
      throw new DataAccessException("상품 데이터 수량을 조회하는 도중 예외가 발생했습니다.", e);
    }
    return count;
  }
  
  public List<ProductDTO> selectAllOrder(String id){
	  List<ProductDTO> products = new ArrayList<ProductDTO>();
	  String sql = "SELECT p.id, p.name, p.price, p.image_id, p.category, o.amount, u.nickname ,o.placedDate " +
              "FROM product p " +
              "JOIN orders o ON p.id = o.productId " +
              "JOIN user u ON p.point = u.point " +
              "WHERE o.user_id = ? " +
              "ORDER BY o.placed_date DESC";
	  
	  try {
		  Connection conn = helper.getConnection();
		  PreparedStatement psmt = conn.prepareStatement(sql);
		  
		  psmt.setString(1, id);
		  ResultSet rs = psmt.executeQuery();
		  
		  while(rs.next()) {
			  ProductImageDTO image = new ProductImageDTO();
			  ProductDTO product = new ProductDTO();
			  product.setId(rs.getInt("id"));
			  product.setName(rs.getString("name"));
			  product.setPrice(rs.getInt("price"));
			  product.setImage(image);
			  product.setCategory(rs.getString("category"));
			  product.setAmount(rs.getInt("amount"));
			  product.setUserId(rs.getString("user_id"));
			  product.setPlacedDate(rs.getString("placed_date"));
		  }
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	return null;
	  
  }
}
