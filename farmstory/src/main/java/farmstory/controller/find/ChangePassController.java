package farmstory.controller.find;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import farmstory.dao.UserDAO;
import farmstory.service.UserService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/find/changePass.do")
public class ChangePassController extends HttpServlet{

	private static final long serialVersionUID = 124541214445412446L;
	private UserDAO dao;
	private UserService service;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.dao = new UserDAO(new ConnectionHelper("jdbc/farmstory"));
	    this.service = new UserService(dao);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/find/change-pass.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
            //변수명 통일: `userId`, `newPassword` 사용
            String userId = req.getParameter("userId");
            String newPassword = req.getParameter("newPassword");

            logger.debug("Received userId: {}", userId);
            logger.debug("Received newPassword: {}", newPassword);

            JsonObject json = new JsonObject();

            //비밀번호 검증
            if (userId == null || newPassword == null || newPassword.trim().isEmpty()) {
                json.addProperty("status", "error");
                json.addProperty("message", "비밀번호를 입력해주세요.");
                resp.getWriter().write(json.toString());
                return;
            }

            //비밀번호 변경 실행
            boolean isUpdated = dao.updatePass(userId, newPassword);

            if (isUpdated) {
                json.addProperty("status", "success");
                json.addProperty("message", "비밀번호가 성공적으로 변경되었습니다.");
            } else {
                json.addProperty("status", "error");
                json.addProperty("message", "비밀번호 변경에 실패하였습니다.");
            }

            PrintWriter out = resp.getWriter();
            out.write(json.toString());
            out.flush();
        } catch (Exception e) {
            logger.error("비밀번호 변경 중 오류 발생", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}