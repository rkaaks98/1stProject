package farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.UserDAO;
import farmstory.dto.OrderDTO;
import farmstory.dto.UserDTO;
import farmstory.service.CountableDefaultService;
import farmstory.service.CountableService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/user-list.do")
public class User_listController extends HttpServlet {

    private static final long serialVersionUID = -4296729693079948400L;
    private static final Logger logger = LoggerFactory.getLogger(User_listController.class.getName());

	private CountableService<UserDTO> service;

    @Override
    public void init() throws ServletException {
        try {
            ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
            UserDAO dao = new UserDAO(helper);
            // CountableDefaultService에 UserDTO 타입을 명확히 지정
            this.service = new CountableDefaultService<UserDTO>(dao);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // `getAll()` 메서드로 사용자 리스트 가져오기
            List<UserDTO> users = service.getAll(); // 또는 DAO에서 `selectAll()`을 호출
            
            // articles 속성으로 사용자 리스트 설정
            req.setAttribute("articles", users);

            // 페이징 처리 코드 추가
            String pg = req.getParameter("pg");
            int currentPage = (pg != null) ? Integer.parseInt(pg) : 1;
            int pageSize = 10;
            int total = users.size();
            int lastPageNum = (int) Math.ceil((double) total / pageSize);
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, total);
            
            List<UserDTO> currentPageUsers = users.subList(startIndex, endIndex);
            req.setAttribute("articles", currentPageUsers);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("lastPageNum", lastPageNum);
            
            // 페이지 번호 범위 설정
            int startPage = ((currentPage - 1) / 5) * 5 + 1;
            int endPage = Math.min(startPage + 4, lastPageNum);
            req.setAttribute("startPage", startPage);
            req.setAttribute("endPage", endPage);
            
            req.setAttribute("pageName", "user-list");

            // JSP로 전달
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user-list.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            logger.error("게시글 목록 조회 중 오류 발생", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류 발생");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
