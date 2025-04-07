package farmstory.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import farmstory.DataTransferObject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * {@link WebFilter} 어노테이션의 urlPatterns는 로그인한 사용자만이 접속할 수 있는 페이지들이며, 로그인한 사용자는 urlPatterns에 없는 페이지에는
 * 접근할 수 없다.
 */
@WebFilter(urlPatterns = {"/order/*", "/wishlist/*", "/user/*", "/article/write", "/article/delete",
    "/article/modify", "/comment/write", "/comment/delete", "/comment/modify"})
public class AuthorizedPageRouter extends HttpFilter {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER =
      LoggerFactory.getLogger(AuthorizedPageRouter.class.getName());

  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    // 요청한 사용자가 로그인 상태인지 확인
    HttpSession session = request.getSession();
    DataTransferObject user = (DataTransferObject) session.getAttribute("sessUser");
    if (user == null) { // 로그인 하지 않은 사용자가 요청한 경우
      String msg =
          String.format("유효하지 않은 접근 (%s ==> %s)", request.getRemoteAddr(), request.getRequestURL());
      LOGGER.warn(msg);
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
    } else { // 로그인한 사용자가 요청한 경우

      String uri = request.getRequestURI();

      // @WebFilter 어노테이션의 urlPatterns 변수 가져오기
      String[] urlPatterns = this.getClass().getAnnotation(WebFilter.class).urlPatterns();

      // TODO Possibly vulnerable?
      // 로그인한 사용자가 @WebServlet의 urlPattenrs에 있는 페이지가 아닌 다른 페이지로 접근하려는 경우
      if (Arrays.asList(urlPatterns).contains(uri)) {// 로그인한 사용자가 요청한 페이지로의 접근이 유효한 경우
        String msg = String.format("사용자가 요청한 페이지(%s)로의 접근이 유효함. %s로 라우팅...",
            request.getRequestURL(), request.getRequestURL());
        LOGGER.info(msg);
        chain.doFilter(request, response);
      } else { // 유효하지 않은 경우
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
      }
    }
  }
}
