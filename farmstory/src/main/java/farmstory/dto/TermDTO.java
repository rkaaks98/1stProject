package farmstory.dto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import farmstory.DataTransferObject;

public class TermDTO implements DataTransferObject {
  private int id;
  private String title;
  private InputStream content;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() throws IOException {
    String contentString = new String(content.readAllBytes());
    this.content.close();
    return contentString;
  }

  public void setContent(String content) {
    this.content = new ByteArrayInputStream(content.getBytes());
  }

  @Override
  public String toString() {
    return "TermDTO [id=" + id + ", title=" + title + ", content=" + content + "]";
  }
}
