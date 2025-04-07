package farmstory.controller.article;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.ArticleDAO;
import farmstory.dto.ArticleDTO;
import farmstory.dto.PageGroupDTO;
import farmstory.service.CountableDefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listSchool")
public class ListControllerSchool extends HttpServlet {

	private static final long serialVersionUID = -6857825635581561308L;
    private static final Logger logger = LoggerFactory.getLogger(ListControllerSchool.class.getName());

    private CountableDefaultService<ArticleDTO> service;
    ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
    ArticleDAO dao = new ArticleDAO(helper);

    @Override
    public void init() throws ServletException {
    	try {
            
            this.service = new CountableDefaultService<>(dao);
            
    	}catch (Exception e) {
			logger.error(e.getMessage());
		}
        
    }
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pg = req.getParameter("pg");

			int total = service.getAll().size(); // 전체 게시글 개수 가져오기
			int pageSize = 10; // 한 페이지당 보여줄 게시글 개수
			
			int currentPageNum = (pg != null) ? Integer.parseInt(pg) : 1;
			int lastPageNum = (int) Math.ceil((double) total / pageSize);
			int pageStartNum = total -(currentPageNum -1) * pageSize;
			
			List<ArticleDTO> articles = dao.getPagedList(currentPageNum, pageSize);
			
            ArticleDTO dto = new ArticleDTO();
            ArticleDTO currentPage = service.get(dto);
            
            
           
            logger.debug("current: " + currentPageNum + ", start: " + pageStartNum + ", last: " + lastPageNum);
            
            PageGroupDTO pageGroupDTO = getCurrentPageGroup(currentPageNum, lastPageNum);
          
            //List<ArticleDTO> articles = service.getAll();
            
            logger.debug("articles: " + articles);
            
            req.setAttribute("articles", articles);
    		req.setAttribute("currentPageNum", currentPage);
    		req.setAttribute("lastPageNum", lastPageNum);
    		req.setAttribute("pageGroupDTO", pageGroupDTO);
    		req.setAttribute("pageStartNum", pageStartNum);
    		
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/story/schoolList.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            logger.error("게시글 목록 조회 중 오류 발생", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류 발생");
        }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	public PageGroupDTO getCurrentPageGroup(int currentPageNum, int lastPageNum) {
        int groupSize = 10; // 한 그룹당 페이지 개수 (예: 1~5, 6~10)

        int currentGroup = (currentPageNum - 1) / groupSize +1 ; // 현재 페이지 그룹 번호
        int startPage = (currentGroup - 1) * groupSize + 1; // 해당 그룹의 첫 페이지
        int endPage = startPage + groupSize - 1; // 해당 그룹의 마지막 페이지

        // 마지막 페이지 번호보다 크면 마지막 페이지 번호로 제한
        if (endPage > lastPageNum) {
            endPage = lastPageNum;
        }

        return new PageGroupDTO(startPage, endPage, currentGroup);
    }
    

}