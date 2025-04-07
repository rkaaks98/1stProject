package farmstory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.DataAccessObject;
import farmstory.dto.CommentDTO;
import farmstory.exception.DataAccessException;
import farmstory.util.ConnectionHelper;


public class CommentDAO implements DataAccessObject<CommentDTO>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleDAO.class.getName());
	private final ConnectionHelper helper;

	public CommentDAO(ConnectionHelper helper) {
	    this.helper = helper;
	  }

	@Override
	public void insert(CommentDTO dto) throws DataAccessException {	
		
	}
	
	public int insertAndKey(CommentDTO dto) {
		int generateKey = 0;
		String sql = "insert into `comment` set "
					+ "`article_id`=?, "
					+ "`author`=?, "
					+ "`content`=?, "
					+ "`register_date`=NOW()";
		
		try {
			Connection conn = helper.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			psmt.setInt(1, dto.getArticleId());
			psmt.setString(2, dto.getAuthor());
			psmt.setString(3, dto.getContent());
			psmt.executeUpdate();
			
			ResultSet rs = psmt.getGeneratedKeys();
			if(rs.next()) {
				generateKey = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			conn.close();
					
			
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
		}		
		
		return generateKey;
	}

	@Override
	public CommentDTO select(CommentDTO dto) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CommentDTO selectComment(int id) {
		CommentDTO dto = null;
		String sql = "select * from `comment` where `article_id`=?";
		
		try {
			Connection conn = helper.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CommentDTO();
				dto.setId(rs.getInt(1));
				dto.setArticleId(rs.getInt(2));
				dto.setAuthor(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegister_date(rs.getString(5));
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return dto;
		
	}

	@Override
	public List<CommentDTO> selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CommentDTO> selectAllComment(int articleId){
		String sql = "select * from `comment` where `article_id`=? order by `id` asc;";
		
		List<CommentDTO> comments = new ArrayList<CommentDTO>();
		try {
			Connection conn = helper.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, articleId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setId(rs.getInt(1));
				dto.setArticleId(rs.getInt(2));
				dto.setAuthor(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegister_date(rs.getString(5));
				
				comments.add(dto);
				
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		
		return comments;
	}

	@Override
	public void update(CommentDTO dto) throws DataAccessException {}

	@Override
	public void delete(CommentDTO dto) throws DataAccessException {}

	
}
