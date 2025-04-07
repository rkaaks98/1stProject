package farmstory.controller.article;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.ArticleDAO;
import farmstory.dto.ArticleDTO;
import farmstory.exception.DataAccessException;
import farmstory.service.DefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/writeStory")
public class WriteControllerStory extends HttpServlet{
	private static final long serialVersionUID = -8195103407449469536L;
	private static final Logger logger = LoggerFactory.getLogger(WriteControllerStory.class.getName());
	
	private DefaultService<ArticleDTO> service;
	
	@Override
	public void init() throws ServletException {
		try {
			ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
			ArticleDAO dao = new ArticleDAO(helper);
			this.service = new DefaultService<>(dao);
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/story/storyWrite.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		try {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String author = req.getParameter("author");
			
			ArticleDTO dto = new ArticleDTO();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setAuthor(author);
			dto.setViewNumber(0);
			
			service.create(dto);
			
			resp.sendRedirect(req.getContextPath() + "/listStory");
			
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		
		
		
		
	}
	

}
