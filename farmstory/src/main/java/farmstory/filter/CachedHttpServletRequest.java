package farmstory.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {
  private final ByteArrayOutputStream cachedBody;

  public CachedHttpServletRequest(HttpServletRequest request) throws IOException {
    super(request);
    InputStream input = request.getInputStream();
    this.cachedBody = new ByteArrayOutputStream(input.available());
    input.transferTo(cachedBody);
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    InputStream stream = new ByteArrayInputStream(cachedBody.toByteArray());
    return (ServletInputStream) stream;
  }

  @Override
  public BufferedReader getReader() throws IOException {
    InputStream stream = new ByteArrayInputStream(cachedBody.toByteArray());
    InputStreamReader inReader = new InputStreamReader(stream);
    return new BufferedReader(inReader);
  }
}
