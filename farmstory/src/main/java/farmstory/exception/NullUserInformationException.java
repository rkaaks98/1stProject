package farmstory.exception;

import java.util.UUID;

public class NullUserInformationException extends RuntimeException {
  private static final long serialVersionUID = UUID.randomUUID().version();

  public NullUserInformationException(String message) {
    super(message);
  }
}
