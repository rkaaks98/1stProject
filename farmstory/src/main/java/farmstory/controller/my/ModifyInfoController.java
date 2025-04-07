package farmstory.controller.my;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.UserDAO;
import farmstory.dto.UserDTO;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/my/modifyInfo.do")
public class ModifyInfoController extends HttpServlet{

	private static final long serialVersionUID = 124512451231246L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	UserDAO dao;
	
	@Override
	public void init() throws ServletException {
		this.dao = new UserDAO(null);
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");

		if (id == null) {
            resp.sendRedirect("/farmstory/signin"); // ✅ 로그인 안 되어 있으면 로그인 페이지로 리다이렉트
            return;
        }
		
		UserDTO user = dao.selectUserById(id);
		req.setAttribute("user", user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/my/modify-info.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");
		
        if (id == null) {
            resp.getWriter().write("{\"status\":\"error\", \"message\":\"로그인이 필요합니다.\"}");
            return;
        }
        
		String action = req.getParameter("action");
		
		if ("update".equals(action)) {
			UserDTO user = new UserDTO();
			user.setId(id);
			user.setName(req.getParameter("name"));
			user.setNickname(req.getParameter("nickname"));
			user.setEmail(req.getParameter("email"));
			user.setPhoneNum(req.getParameter("phoneNum"));
			user.setAddress(req.getParameter("address"));
			user.setAddressDetail(req.getParameter("addressDetail"));
			
			logger.debug("action : " + action);
			
			boolean isUpdated = dao.modifyUser(user);
			resp.getWriter().write("{\"status\":\"" + (isUpdated ? "success" : "error") + "\"}");
		}else if ("delete".equals(action)) {
            boolean isDeleted = dao.deleteUser(id);
            if (isDeleted) {
                session.invalidate(); //회원 탈퇴 후 세션 삭제
            }

            resp.getWriter().write("{\"status\":\"" + (isDeleted ? "success" : "error") + "\"}");
        }
    }
}
















