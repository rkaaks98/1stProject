package farmstory.util;

import com.google.gson.JsonObject;
import farmstory.exception.InvalidUserInformationException;
import farmstory.exception.NullUserInformationException;

public class InputValidator {
  public static final String ID_REGEX = "^[a-z]+[a-z0-9]{4,19}$";
  public static final String PASSWORD_REGEX =
      "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\\\(\\\\)\\-_=+]).{5,16}$";
  public static final String NAME_REGEX = "^[가-힣]{2,10}$";
  public static final String NICKNAME_REGEX = "^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$";
  public static final String EMAIL_REGEX =
      "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
  public static final String PHONE_NUM_REGEX = "^01(?:0|1|[6-9])-(?:\\d{4})-\\d{4}$";
  public static final String ZIP_REGEX = "(\\d{3}-\\d{3}|\\d{5})";
  public static final String ADDR_REGEX = "^[가-힣a-zA-Z0-9\\s]*$";
  private final JsonObject obj;

  public InputValidator(JsonObject obj) {
    this.obj = obj;
  }

  public void validate(String key, String regex)
      throws InvalidUserInformationException, NullUserInformationException {
    String value = obj.asMap().get(key).getAsString();
    if (value == null) {
      String msg = String.format("\"%s\"의 값은 NULL이 될 수 없습니다.", key);
      throw new NullUserInformationException(msg);
    }
    if (!value.matches(regex)) {
      String message = String.format("유효하지 않은 \"%s\" 데이터 \"%s\"입니다.", key, value);
      throw new InvalidUserInformationException(message);
    }
  }

  public static String getRegex(String key) {
    switch (key) {
      case "id": {
        return ID_REGEX;
      }
      case "password", "passwordConfirm":
        return PASSWORD_REGEX;
      case "name": {
        return NAME_REGEX;
      }
      case "nickname": {
        return NICKNAME_REGEX;
      }
      case "email": {
        return EMAIL_REGEX;
      }
      case "phoneNum": {
        return PHONE_NUM_REGEX;
      }
      case "zip": {
        return ZIP_REGEX;
      }
      case "address", "addressDetail": {
        return ADDR_REGEX;
      }
      default: {
        String msg = String.format("알 수 없는 JSON 키 '%s'를 발견하였습니다.", key);
        throw new InvalidUserInformationException(msg);
      }
    }
  }
}
