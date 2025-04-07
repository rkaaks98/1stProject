package farmstory.controller.main;

import java.io.IOException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/greeting.do", "/map.do"})
public class StaticPageController extends HttpServlet {
  private static final long serialVersionUID = UUID.randomUUID().version();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int rootPathLen = "/farmstory".length();
    String uri = req.getRequestURI();

    // /farmstory/greeting.do -> greeting.do
    String subURI = uri.substring(rootPathLen, uri.length());

    int doExtIdx = subURI.indexOf(".");
    String pageName = subURI.substring(0, doExtIdx);

    String pagePath = String.format("/WEB-INF/intro%s.jsp", pageName);

    req.getRequestDispatcher(pagePath).forward(req, resp);
  }
}
