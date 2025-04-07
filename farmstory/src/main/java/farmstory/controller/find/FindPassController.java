package farmstory.controller.find;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.UserDAO;
import farmstory.dto.UserDTO;
import farmstory.service.UserService;
import farmstory.util.ConnectionHelper;
import farmstory.util.ResponseBodyWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/find/findPass.do")
public class FindPassController extends HttpServlet{

	private static final long serialVersionUID = 124541214445412446L;
	
	private UserDAO dao;
	private UserService service;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		this.dao = new UserDAO(new ConnectionHelper("jdbc/farmstory"));
	    this.service = new UserService(dao);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/find/find-pass.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트로부터 전달받은 데이터 (이름, 이메일)
        String id = req.getParameter("id");
        String email = req.getParameter("email");
        
        // 응답을 JSON 형태로 설정
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        try {
            UserDTO user = service.findPass(id, email);
            if (user != null) {
                // 6자리 랜덤 인증번호 생성 (예: 100000 ~ 999999)
                String authCode = String.valueOf((int)(Math.random() * 900000) + 100000);
                
                // 실제 서비스에서는 이메일 발송 로직을 추가하여 사용자에게 인증번호를 보내야 합니다.
                // 예제에서는 데모용으로 인증번호를 그대로 반환합니다.
                out.write("{\"success\": true, \"authCode\": \"" + authCode + "\"}");
            } else {
                out.write("{\"success\": false}");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"success\": false}");
        } finally {
            out.close();
        }
    }
}