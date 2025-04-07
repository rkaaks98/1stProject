package farmstory.controller.article;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.ArticleDAO;
import farmstory.dto.ArticleDTO;
import farmstory.exception.DataAccessException;
import farmstory.service.CountableDefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteSchool")
public class DeleteControllerSchool extends HttpServlet{
	private static final long serialVersionUID = -8195123807449469536L;
	private static final Logger logger = LoggerFactory.getLogger(DeleteControllerSchool.class.getName());
	
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
		
		if (id == null || id.isEmpty()) {
	        logger.warn("No article ID provided for deletion.");
	        resp.sendRedirect(req.getContextPath() + "/listSchool");
	        return;
	    }

		try {
			
			ArticleDTO dto = new ArticleDTO();
			dto.setId(id);
			
			service.delete(dto);
			
			logger.debug("deleteArticle : " + id);
			
			resp.sendRedirect(req.getContextPath() + "/listSchool");
			
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
        
        
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	

}
