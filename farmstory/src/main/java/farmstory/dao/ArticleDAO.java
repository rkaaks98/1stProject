package farmstory.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.CountableDAO;
import farmstory.dto.ArticleDTO;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class ArticleDAO implements CountableDAO<ArticleDTO> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ArticleDAO.class.getName());
  private final ConnectionHelper helper;

  public ArticleDAO(ConnectionHelper helper) {
    this.helper = helper;
  }
  

  @Override
  public void insert(ArticleDTO dto) {
	String sql = "insert into `article` set "
			+ "`title` = ?, "
    		+ "`content` = ?, "
    		+ "`author` = ?, "
    		+ "`user_id` = ?, "
    		+ "`register_date` = NOW()";

    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(sql);
      
      LOGGER.info("DB에 저장할 데이터: title={}, content={}, author={}", dto.getTitle(), dto.getContent(), dto.getAuthor());
      
      psmt.setString(1, dto.getTitle());
      psmt.setString(2, dto.getContent());
      psmt.setString(3, dto.getAuthor());
      psmt.setString(4, dto.getUserId());

      psmt.executeUpdate();
      
      //글번호
      ResultSet rs = psmt.getGeneratedKeys();
      if(rs.next()) {
    	 int id = rs.getInt(1);
      }
     
      rs.close();
      psmt.close();
      conn.close();

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
    
  }

  @Override
  public ArticleDTO select(ArticleDTO dto) {
    String sql = "select * from `article` where `id`=?";
    ArticleDTO articleDTO = null;

    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(sql);

      psmt.setInt(1, dto.getId());
      ResultSet rs = psmt.executeQuery();

      if (rs.next()) {
        articleDTO = new ArticleDTO();
        articleDTO.setId(rs.getInt("id"));
        articleDTO.setTitle(rs.getString("title"));
        articleDTO.setContent(rs.getString("content"));
        articleDTO.setAuthor(rs.getString("author"));
        articleDTO.setRegisterDate(rs.getString("registerDate"));
      }
      
      rs.close();
      psmt.close();
      conn.close();

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }

    return articleDTO;

  }
  
  public List<ArticleDTO> getPagedList(int currentPageNum, int pageSize) {
	List<ArticleDTO> articles = new ArrayList<>();
	int offset = (currentPageNum -1 ) * pageSize;
    String sql = "select `id`, `title`, `content`,`author`, `register_date` from `article` order by `id` desc limit ? offset ?";
 

    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(sql);
      psmt.setInt(1, pageSize);
      psmt.setInt(2, offset);

      ResultSet rs = psmt.executeQuery();

      while (rs.next()) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(rs.getInt("id"));
        articleDTO.setTitle(rs.getString("title"));
        articleDTO.setContent(rs.getString("content"));
        articleDTO.setAuthor(rs.getString("author"));
        articleDTO.setRegisterDate(rs.getString("register_date"));

        articles.add(articleDTO);
        
        
      }
      rs.close();
      psmt.close();
      conn.close();
      LOGGER.debug("게시글 수 : " + articles.size());
      
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }

    return articles;
  }

  @Override
  public List<ArticleDTO> selectAll() {
    String sql = "select `id`, `title`, `content`,`author`, `register_date` from `article` order by `id` desc";
    List<ArticleDTO> articles = new ArrayList<>();

    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(sql);

      ResultSet rs = psmt.executeQuery();

      while (rs.next()) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(rs.getInt("id"));
        articleDTO.setTitle(rs.getString("title"));
        articleDTO.setContent(rs.getString("content"));
        articleDTO.setAuthor(rs.getString("author"));
        articleDTO.setRegisterDate(rs.getString("register_date"));

        articles.add(articleDTO);
      }
      
      LOGGER.debug("게시글 수 : " + articles.size());
      
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }

    return articles;
  }

  public List<ArticleDTO> uploadFile(HttpServletRequest req) {
	 List<ArticleDTO> files = new ArrayList<ArticleDTO>();
	 
	 ServletContext ctx = req.getServletContext();
	 String uploadPath = ctx.getRealPath("/uploads");
	 
	 
	 File uploadDir = new File(uploadPath);
	 if(!uploadDir.exists()) {
		 uploadDir.mkdir();
	 }
	 
	 LOGGER.debug("uploadPath : " + uploadPath);
	 
	 try {
		 Collection<Part> parts = req.getParts();
		 
		 for(Part part : parts) {
			 LOGGER.debug(part.toString());
			 
			 String oName = part.getSubmittedFileName();
			 LOGGER.debug(oName);
			 
			 if(oName != null && !oName.isEmpty()) {
				 int idx = oName.lastIndexOf(".");
				 String ext = oName.substring(idx);
				 String sName = UUID.randomUUID().toString() + ext;
				 part.write(uploadPath + File.separator + sName);
				 
				 ArticleDTO dto = new ArticleDTO();
				 dto.setoName(oName);
				 dto.setsName(sName);
				 
				 files.add(dto);
			 }
		 }
		 
	 }catch (Exception e) {
		LOGGER.error(e.getMessage());
	}
	  
	 return files;
  }
  
  public void downloadFile(HttpServletRequest req, HttpServletResponse resp) {
		
		//공유참조된 파일 정보 객체 가져오기
		ArticleDTO fileDTO = (ArticleDTO) req.getAttribute("articleDTO");
		
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/uploads");
		File target = new File(path + File.separator + fileDTO.getsName());	//경로 + 구분자 + 파일명
		
		
		try{
			//response 파일 다운로드 헤더 정보
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDTO.getoName(), "utf-8"));
			resp.setHeader("Content-Transfer-Encoding", "binary");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "private");
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(target));
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
			
			//파일 전송
			bis.transferTo(bos);
			bos.flush();
			bos.close();
			bis.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

  @Override
  public void update(ArticleDTO dto) {
    String sql = "update `article` set `title`=?, `content`=? where `id`=?";
    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(sql);
      psmt.setString(1, dto.getTitle());
      psmt.setString(2, dto.getContent());
      psmt.setInt(3, dto.getId());
      psmt.executeUpdate();

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }

  }

  @Override
  public void delete(ArticleDTO dto) {
    String sql = "delete from `article` where `id`=?";

    try {
      Connection conn = helper.getConnection();
      PreparedStatement psmt = conn.prepareStatement(sql);

      psmt.setInt(1, dto.getId());
      psmt.executeUpdate();

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  public int count() {
	int count = 0;
    String sql = "select count(*) from `article`";
    
    try {
      Connection conn = helper.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
        count = rs.getInt(1);
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }

    return count;
  }
}
