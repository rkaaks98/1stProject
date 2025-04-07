package farmstory.controller.article;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.ArticleDAO;
import farmstory.dto.ArticleDTO;
import farmstory.exception.DataAccessException;
import farmstory.service.CountableDefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modifySchool")
public class ModifyControllerSchool extends HttpServlet{
	private static final long serialVersionUID = -8195123407449469536L;
	private static final Logger logger = LoggerFactory.getLogger(ModifyControllerSchool.class.getName());
	
	private CountableDefaultService<ArticleDTO> service;
	
	@Override
	public void init() throws ServletException {
		try {
			ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
			ArticleDAO dao = new ArticleDAO(helper);
			this.service = new CountableDefaultService<>(dao);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");		
		System.out.println("ModifyController - 게시글번호 id : " + id);
		
        if (id == null || id.isEmpty()) {
        	logger.warn("No article found for id: " + id);
            resp.sendRedirect("/farmstory/story/listSchool"); // 게시글 목록으로 이동
            return;
        }
		
        
        
        
		try {
			
			ArticleDTO dto = new ArticleDTO();
			dto.setId(id);
			ArticleDTO articleDTO = service.get(dto);
			
			logger.debug("articleDTO : " + articleDTO);
			
			
			if (articleDTO == null) {
	            logger.error("Article not found for id: " + id);
	            resp.sendRedirect("/farmstory/story/listSchool"); // 글이 없는 경우 목록으로 리다이렉트
	            return;
	        }
			
	        req.setAttribute("articleDTO", articleDTO);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/story/schoolModify.jsp");
			dispatcher.forward(req, resp);
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String author = req.getParameter("author");
		
		if(id == null || id.isEmpty()) {
			logger.error("Article not found for id: " + id);
            resp.sendRedirect("/farmstory/story/listSchool"); // 글이 없는 경우 목록으로 리다이렉트
            return;
		}
		
		try {	
			ArticleDTO dto = new ArticleDTO();
			dto.setId(id);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setAuthor(author);
			dto.setViewNumber(0);
			
			service.update(dto);
			
			resp.sendRedirect(req.getContextPath() + "/viewSchool?id=" + id);
			
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/listSchool");
		}
		
		
	}
	

}
