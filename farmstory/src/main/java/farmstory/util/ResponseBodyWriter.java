package farmstory.util;

import java.io.IOException;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletResponse;

public class ResponseBodyWriter {
  public static void write(boolean isSuccess, String message, int status, HttpServletResponse res)
      throws IOException {
    JsonObject body = new JsonObject();
    body.addProperty("isSuccess", isSuccess);
    if (!isSuccess) {
      body.addProperty("message", message);
    }
    res.setStatus(status);
    res.setContentType("application/json;charset=utf8");
    res.getWriter().println(body);
  }

  private ResponseBodyWriter() {
    // Empty constructor
  }
}
