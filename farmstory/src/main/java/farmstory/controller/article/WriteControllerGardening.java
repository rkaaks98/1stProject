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

@WebServlet("/writeGardening")
public class WriteControllerGardening extends HttpServlet {
  private static final long serialVersionUID = -8195103407449469536L;
  private static final Logger logger =
      LoggerFactory.getLogger(WriteControllerGardening.class.getName());

  private DefaultService<ArticleDTO> service;
  private DefaultService<ArticleDTO> fileService;

  @Override
  public void init() throws ServletException {
    try {
      ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
      ArticleDAO dao = new ArticleDAO(helper);
      //ArticleDAO fileDAO = new ArticleDAO(helper);
      
      this.service = new DefaultService<>(dao);
      //this.fileService = new DefaultService<>(fileDAO);

    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/story/gardeningWrite.jsp");
    dispatcher.forward(req, resp);
  }



  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String title = req.getParameter("title");
      String content = req.getParameter("content");
      String author = req.getParameter("author");
      
      logger.info("Received data: title={}, content={}, author={}", title, content, author);
      
      //List<ArticleDTO> files = fileService.getAll();

      ArticleDTO dto = new ArticleDTO();
      dto.setTitle(title);
      dto.setContent(content);
      //dto.setFile(files.size());
      dto.setAuthor(author);
      service.create(dto);
      
      logger.debug(dto.toString());
      
      
      
      /*
      int fileId = dto.getId();      
      for (ArticleDTO fileDTO : files) {
        fileDTO.setFileid(fileId);
        fileService.create(fileDTO);
      }
      */

      resp.sendRedirect(req.getContextPath() + "/listGardening");

    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }
    



  }



}
