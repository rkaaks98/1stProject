package farmstory.controller.community;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class faqController
 */
@WebServlet("/iam-cooker.do")
public class iam_cookerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public iam_cookerController() {

    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Viewport
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/community/iam-cooker.jsp");
		dispatcher.forward(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
