package farmstory.controller.article;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.ArticleDAO;
import farmstory.dao.CommentDAO;
import farmstory.dto.ArticleDTO;
import farmstory.dto.CommentDTO;
import farmstory.exception.DataAccessException;
import farmstory.service.CountableDefaultService;
import farmstory.service.DefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewGardening")
public class ViewControllerGardening extends HttpServlet{
	private static final long serialVersionUID = -8195125407449469536L;
	private static final Logger logger = LoggerFactory.getLogger(ViewControllerGardening.class.getName());
	private CountableDefaultService<ArticleDTO> service;
	private CommentDAO commentDAO;
	
	@Override
	public void init() throws ServletException {
		try {
			ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
			ArticleDAO dao = new ArticleDAO(helper);
			commentDAO = new CommentDAO(helper);
			this.service = new CountableDefaultService<>(dao);
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		logger.debug("requestID: " + id);
		
		
		CommentDTO commentDTO = new CommentDTO();
		
				
		
		try {

			int articleId = Integer.parseInt(id);
			ArticleDTO dto = new ArticleDTO();
			dto.setId(id);
			
			commentDTO.setArticleId(articleId);
			logger.debug("requestArticleID1: " + articleId);


			
			ArticleDTO articleDTO = service.get(dto);
			
			req.setAttribute("articleDTO", articleDTO);
			
			List<CommentDTO> comments = commentDAO.selectAllComment(articleId);
			req.setAttribute("comments", comments);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/story/gardeningView.jsp");
			dispatcher.forward(req, resp);
			
			logger.debug("articleDTO : " + articleDTO);
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}