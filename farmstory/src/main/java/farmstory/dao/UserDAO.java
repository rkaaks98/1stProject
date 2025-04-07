package farmstory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.CountableDAO;
import farmstory.dto.UserDTO;
import farmstory.exception.DataAccessException;
import farmstory.util.ConnectionHelper;
import farmstory.util.Query;

public class UserDAO implements CountableDAO<UserDTO> {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class.getName());
  private final ConnectionHelper helper;

  public UserDAO(ConnectionHelper helper) {
    this.helper = helper;
  }


  @Override
  public void insert(UserDTO dto) throws DataAccessException {
    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(Query.INSERT_USER);
      psmt.setString(1, dto.getId());
      psmt.setString(2, dto.getPassword());
      psmt.setString(3, dto.getName());
      psmt.setString(4, dto.getNickname());
      psmt.setString(5, dto.getEmail());
      psmt.setString(6, dto.getPhoneNum());
      psmt.setString(7, dto.getZip());
      psmt.setString(8, dto.getAddress());
      psmt.setString(9, dto.getAddressDetail());

      psmt.executeUpdate();
      conn.close();
      psmt.close();
    } catch (NamingException | SQLException e) {
      String msg = String.format("사용자 데이터 삽입 중 예외가 발생하였습니다%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }
  }

  @Override
  public UserDTO select(UserDTO dto) throws DataAccessException {
    UserDTO user = new UserDTO();
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.SELECT_USER)) {
      psmt.setString(1, dto.getId());
      ResultSet rs = psmt.executeQuery();
      if (rs.next()) {
        user.setId(rs.getString(1));
        user.setPassword(rs.getString(2));
        user.setName(rs.getString(3));
        user.setNickname(rs.getString(4));
        user.setPoint(rs.getInt(5));
        user.setLevel(rs.getInt(6));
        user.setEmail(rs.getString(7));
        user.setPhoneNum(rs.getString(8));
        user.setZip(rs.getString(9));
        user.setAddress(rs.getString(10));
        user.setAddressDetail(rs.getString(11));
        user.setRegisterDate(rs.getString(12));
        user.setLeaveDate(rs.getString(13));
      }
      rs.close();
    } catch (NamingException | SQLException e) {
      String msg = String.format("사용자 데이터 조회 중 예외가 발생하였습니다%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }
    return user;
  }

  @Override
  public List<UserDTO> selectAll() throws DataAccessException {
    List<UserDTO> users = new ArrayList<>();
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.SELECT_ALL_USER)) {

      ResultSet rs = psmt.executeQuery();

      while (rs.next()) {
        UserDTO user = new UserDTO();
        user.setId(rs.getString("id"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setNickname(rs.getString("nickname"));
        user.setPoint(rs.getInt("point"));
        user.setLevel(rs.getInt("level"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNum(rs.getString("phone_num"));
        user.setZip(rs.getString("zip"));
        user.setAddress(rs.getString("address"));
        user.setRegisterDate(rs.getString("register_date"));
        user.setLeaveDate(rs.getString("leave_date"));
        users.add(user);
      }
    } catch (NamingException | SQLException e) {
      String msg =
          String.format("모든 사용자 데이터 조회 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }

    return users;
  }
  
  public UserDTO selectUserById(String id) {
	  String sql = "select  from user where id=?";
	  UserDTO user = null;
	  try {
		  Connection conn = helper.getConnection();
		  PreparedStatement psmt = conn.prepareStatement(sql);
		  psmt.setString(1, id);
		  ResultSet rs = psmt.executeQuery();
		  if (rs.next()) {
			  	user = new UserDTO();
		        user.setId(rs.getString("id"));
		        user.setPassword(rs.getString("password"));
		        user.setName(rs.getString("name"));
		        user.setNickname(rs.getString("nickname"));
		        user.setPoint(rs.getInt("point"));
		        user.setLevel(rs.getInt("level"));
		        user.setEmail(rs.getString("email"));
		        user.setPhoneNum(rs.getString("phone_num"));
		        user.setZip(rs.getString("zip"));
		        user.setAddress(rs.getString("address"));
		        user.setRegisterDate(rs.getString("register_date"));
		        user.setLeaveDate(rs.getString("leave_date"));
		}
		
	} catch (Exception e) {
		LOGGER.error(e.getMessage());
	}
	  return user;
  }
  public boolean modifyUser(UserDTO user) {
	  
	  String sql = "UPDATE user SET name=?, nickname=?, email=?, phone_num=?, address=?, addressDetail=? WHERE id=?";
	  
	  try {
		  Connection conn = helper.getConnection();
		  PreparedStatement psmt = conn.prepareStatement(sql);
		  psmt.setString(1, user.getName());
		  psmt.setString(2, user.getNickname());
		  psmt.setString(3, user.getEmail());
		  psmt.setString(4, user.getPhoneNum());
		  psmt.setString(5, user.getAddress());
		  psmt.setString(6, user.getAddressDetail());
		  psmt.setString(7, user.getId());
		  return psmt.executeUpdate() > 0;
		
	} catch (Exception e) {
		LOGGER.error(e.getMessage());
	}
	  
	return false;
	  
  }

 public UserDTO selectResult(String email) {
    UserDTO user = null;
    String sql = "SELECT `name`, `id`, `email`, `register_date` FROM `user` where `email` = ?";

    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(sql)) {

    	psmt.setString(1, email);
        try (ResultSet rs = psmt.executeQuery()) {
            if (rs.next()) {
                user = new UserDTO();
                user.setName(rs.getString("name"));
                user.setId(rs.getString("id"));
                user.setEmail(rs.getString("email"));
                user.setRegisterDate(rs.getString("register_date"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return user;
  }

  @Override
  public void update(UserDTO dto) throws DataAccessException {
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.UPDATE_USER)) {
      psmt.setString(1, dto.getPassword());
      psmt.setString(2, dto.getName());
      psmt.setString(3, dto.getNickname());
      psmt.setInt(4, dto.getPoint());
      psmt.setInt(5, dto.getLevel());
      psmt.setString(6, dto.getEmail());
      psmt.setString(7, dto.getPhoneNum());
      psmt.setString(8, dto.getZip());
      psmt.setString(9, dto.getAddress());
      psmt.setString(10, dto.getAddressDetail());
      psmt.setString(11, dto.getId());

      psmt.executeUpdate();
    } catch (NamingException | SQLException e) {
      String msg = String.format("사용자 데이터 수정 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }
  }

  public UserDTO findUser(String name, String email) {
	  String sql = "SELECT id, name, email FROM users WHERE name = ? AND email = ?";
	  try {
		  Connection conn = helper.getConnection();
		  PreparedStatement psmt = conn.prepareStatement(sql);
		  psmt.setString(1, name);
		  psmt.setString(2, email);
		  ResultSet rs = psmt.executeQuery();
		  if (rs.next()) {
			UserDTO user = new UserDTO();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			return user;
		}
	} catch (Exception e) {
		java.util.logging.Logger.getLogger(e.getMessage());
	}
	return null;
  }

  public UserDTO findPass(String id, String email) {
    
    String sql = "SELECT pass, id, email FROM user WHERE `id` = ? AND `email` = ?";

    try {
		  Connection conn = helper.getConnection();
		  PreparedStatement psmt = conn.prepareStatement(sql);
		  psmt.setString(1, id);
		  psmt.setString(2, email);
		  ResultSet rs = psmt.executeQuery();
		  if (rs.next()) {
			UserDTO user = new UserDTO();
			user.setId(rs.getString("pass"));
			user.setName(rs.getString("id"));
			user.setEmail(rs.getString("email"));
			return user;
		}
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }
  public boolean updatePass(String id, String newPassword){
	  
	  if (id == null || newPassword == null || newPassword.trim().isEmpty()) {
          return false; // 입력값이 잘못된 경우 변경 불가
      }
	  
	  	String sql = "update user set password = SHA2(?, 256) where id = ?";
	  	
	  	try {
	  		Connection conn = helper.getConnection();
	  		PreparedStatement psmt = conn.prepareStatement(sql);
	  		
	  		psmt.setString(1, newPassword);
	  		psmt.setString(2, id);
	  		
	 
	  		return psmt.executeUpdate() > 0;
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	    return false;
	  }

  public boolean isUserValid(String id, String email) {

    String sql = "select count(*) from `user` where `id`=? and `email`=?";

    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(sql);
      ResultSet rs = psmt.executeQuery();

      psmt.setString(1, id);
      psmt.setString(2, email);

      if (rs.next()) {
        return rs.getInt(1) > 0;
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
    return false;
  }

  @Override
  public void delete(UserDTO dto) throws DataAccessException {
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.DELETE_USER)) {
      psmt.setString(1, dto.getId());
      psmt.executeUpdate();
    } catch (NamingException | SQLException e) {
      String msg = String.format("사용자 데이터 삭제 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }
  }
  public boolean deleteUser(String id) {
	  String sql = "DELETE FROM user WHERE id=?";
	  
	  try {
		  Connection conn = helper.getConnection();
		  PreparedStatement psmt = conn.prepareStatement(sql);
		  psmt.setString(1, id);
		  return psmt.executeUpdate() > 0;
	} catch (Exception e) {
		LOGGER.error(e.getMessage());
	}
	return false;
  }

  @Override
  public int count() throws DataAccessException, IllegalArgumentException {
    int count = 0;
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.COUNT_USER)) {
      ResultSet rs = psmt.executeQuery();
      if (rs.next()) {
        count = rs.getInt(1);
      }
    } catch (NamingException | SQLException e) {
      String msg = String.format("사용자 데이터 삭제 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }
    return count;
  }

  public int count(String colName, String condition) throws DataAccessException {
    int count = 0;
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.COUNT_USER_WITH_COLNAME)) {
      psmt.setString(1, colName);
      psmt.setString(2, condition);

      ResultSet rs = psmt.executeQuery();
      if (rs.next()) {
        count = rs.getInt(1);
      }
      rs.close();
    } catch (NamingException | SQLException e) {
      throw new DataAccessException("COUNT 쿼리를 실행하는 도중 예외가 발생하였습니다.", e);
    }
    return count;
  }
}

