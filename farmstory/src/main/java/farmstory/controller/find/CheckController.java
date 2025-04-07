package farmstory.controller.find;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/find/check.do")
public class CheckController extends HttpServlet {

    private static final long serialVersionUID = 143651892754L;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

        String inputCode = req.getParameter("code");
        HttpSession session = req.getSession();
        String savedCode = (String) session.getAttribute("authCode");
        
        logger.debug("입력된 인증번호: " + inputCode);
        logger.debug("세션 ID: " + session.getId());
        logger.debug("세션 저장된 인증번호: " + savedCode);
        
        JsonObject json = new JsonObject();

        if (savedCode == null) {
            json.addProperty("status", "error");
            json.addProperty("message", "인증번호 요청이 만료되었습니다.");
        } else if (savedCode.equals(inputCode)) {
            json.addProperty("status", "success");
            json.addProperty("message", "인증 성공!");
            session.removeAttribute("authCode"); // 인증 성공 후 세션에서 삭제
        } else {
            json.addProperty("status", "error");
            json.addProperty("message", "인증번호가 일치하지 않습니다.");
        }

        // JSON 응답 반환
        try (PrintWriter out = resp.getWriter()) {
            out.write(json.toString());
        }
    }
}
    