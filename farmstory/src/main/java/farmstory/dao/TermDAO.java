package farmstory.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import farmstory.DataAccessObject;
import farmstory.dto.TermDTO;
import farmstory.exception.DataAccessException;
import farmstory.util.ConnectionHelper;
import farmstory.util.Query;

public class TermDAO implements DataAccessObject<TermDTO> {
  private final ConnectionHelper helper;

  public TermDAO(ConnectionHelper helper) {
    this.helper = helper;
  }

  @Override
  public void insert(TermDTO dto) throws DataAccessException {
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.INSERT_TERM)) {
      psmt.setInt(1, dto.getId());
      psmt.setString(2, dto.getTitle());
      String content = dto.getContent();
      psmt.setString(3, content);

      psmt.executeUpdate();

    } catch (NamingException | SQLException | IOException e) {
      String msg = String.format("약관 데이터 삽입 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }
  }

  @Override
  public TermDTO select(TermDTO dto) throws DataAccessException {
    TermDTO term = new TermDTO();
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.SELECT_TERM)) {
      psmt.setInt(1, dto.getId());

      ResultSet rs = psmt.executeQuery();
      if (rs.next()) {
        term.setId(rs.getInt(1));
        term.setTitle(rs.getString(2));
        String content = rs.getString(3);

        term.setContent(content);
      }
      rs.close();
    } catch (NamingException | SQLException e) {
      String msg = String.format("약관 데이터 조회 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }
    return term;
  }

  @Override
  public List<TermDTO> selectAll() throws DataAccessException {
    List<TermDTO> terms = new ArrayList<>();

    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.SELECT_ALL_TERMS)) {
      ResultSet rs = psmt.executeQuery();

      while (rs.next()) {
        TermDTO term = new TermDTO();
        term.setId(rs.getInt(1));
        term.setTitle(rs.getString(2));
        String content = rs.getString(3);
        term.setContent(content);

        terms.add(term);
      }

    } catch (NamingException | SQLException e) {
      String msg =
          String.format("모든 약관 데이터 조회 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }

    return terms;
  }

  @Override
  public void update(TermDTO dto) throws DataAccessException {
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.UPDATE_TERM)) {
      psmt.setInt(1, dto.getId());
      psmt.setString(2, dto.getTitle());

      String content = dto.getContent();
      psmt.setString(3, content);

    } catch (NamingException | SQLException | IOException e) {
      String msg = String.format("약관 데이터 수정 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }

  }

  @Override
  public void delete(TermDTO dto) throws DataAccessException {
    try (Connection conn = helper.getConnection();
        PreparedStatement psmt = conn.prepareStatement(Query.DELETE_TERM)) {
      psmt.setInt(1, dto.getId());

      psmt.executeUpdate();
    } catch (NamingException | SQLException e) {
      String msg = String.format("약관 데이터 삭제 중 예외가 발생하였습니다.%n%s%n%s", e.getCause(), e.getMessage());
      throw new DataAccessException(msg, e);
    }
  }
}
