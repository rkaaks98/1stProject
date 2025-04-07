package farmstory.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import farmstory.exception.InvalidUserInformationException;
import farmstory.exception.NullUserInformationException;
import farmstory.util.InputValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 유저가 입력한 값들의 형식이 유효한지를 검사하는 필터. <br>
 * 유저가 입력한 값이 NULL인지 아닌지를 검사하지는 않으며, NULL 검사는 각 controller에서 수행한다. <br>
 * 
 */
@WebFilter(urlPatterns = {"/signup", "/check", "/signin"}) // TODO: Add /check URL.
public class UserInputValidator extends HttpFilter {
  private static final long serialVersionUID = UUID.randomUUID().version();
  private static final Logger LOGGER = LoggerFactory.getLogger(UserInputValidator.class.getName());

  private String stringify(BufferedReader reader) {
    return reader.lines().collect(Collectors.joining());
  }

  /**
   * JsonObject를 iterate 하면서 NULL 값을 가진 원소가 있는지를 확인하고 각 원소의 값이 유효한 값인지 확인한다.
   * 
   * @param obj 검증의 대상이 되는 JSON 객체.
   * @throws InvalidUserInformationException 특정 원소의 값이 유효하지 않은 경우 발생
   * @throws NullUserInformationException 특정 원소의 값이 NULL인 경우 발생
   */
  private void validate(JsonObject obj)
      throws InvalidUserInformationException, NullUserInformationException {
    InputValidator validator = new InputValidator(obj);
    Set<Map.Entry<String, JsonElement>> set = obj.entrySet();
    for (Map.Entry<String, JsonElement> entry : set) {
      String currentKey = entry.getKey();
      validator.validate(currentKey, InputValidator.getRegex(currentKey));
    }
  }

  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequestWrapper wrappedRequest = new CachedHttpServletRequest(request);
    if (wrappedRequest.getMethod().equalsIgnoreCase("post")) {// POST 메서드에 대해서만 필터링을 진행
      String jsonString = stringify(wrappedRequest.getReader());
      JsonObject obj = JsonParser.parseString(jsonString).getAsJsonObject();
      try {
        validate(obj);
        LOGGER.debug("Validation complete");
        chain.doFilter(wrappedRequest, response);
      } catch (InvalidUserInformationException | NullUserInformationException e) {
        String msg = String.format("유효하지 않은 사용자 정보가 감지되었습니다.%nMessage: %s%nStackTrace: %s",
            e.getMessage(), Arrays.toString(e.getStackTrace()));
        LOGGER.debug(e.getMessage());
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
      }
    } else {
      chain.doFilter(wrappedRequest, response);
    }
  }
}
