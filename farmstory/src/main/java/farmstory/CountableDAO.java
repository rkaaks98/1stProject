package farmstory;

import farmstory.exception.DataAccessException;

/**
 * MySQL의 COUNT() 함수를 사용해야 하는 DAO는 본 인터페이스를 구현한다. 예를 들어, UserDAO는 특정 아이디를 사용하는 사용자가 몇명인지 확인하기 위해
 * {@link #count()} 메서드를 가져야 하므로, 아래와 같이 UserDAO를 선언한다. <br>
 * 
 * <pre>
 * {@code 
 * CountableDAO<UserDTO> dao = new UserDAO(new {@link ConnectionHelper};
 * int count = dao.count();
 * List<UserDTO> users = dao.selectAll();
 * }
 * </pre>
 * 
 * @param <T> {@link #insert}, {@link #select(DataTransferObject)}, {@link #selectAll()}
 *        {@link #update(DataTransferObject)}, {@link #delete(DataTransferObject)} 메서드의 반환 타입 또는
 *        파라미터 타입.
 * 
 */
public interface CountableDAO<T extends DataTransferObject> extends DataAccessObject<T> {
  /**
   * MySQL의 COUNT() 함수
   * 
   * 예를 들어 아래의 쿼리의 실행값이 1이라면 본 메서드는 1을 반환한다. <br>
   * <code>
   * SELECT COUNT(`id`) FROM `user` WHERE `id`="abc123";
   * </code>
   * 
   * @return MySQL의 COUNT() 함수의 결과값
   * @param colName 세고자 하는 컬럼의 이름. 위 예시의 `id`에 해당하는 값.
   * @param condition WHERE 절에 사용될 값. 위 예시의 "abc123"에 해당하는 값.
   * @return
   * @throws DataAccessException DB 작업 중 예외가 발생
   * @throws IllegalArgumentException colName에 명시된 이름의 컬럼을 찾을 수 없을 때 발생하는 예외
   */
  int count() throws DataAccessException, IllegalArgumentException;
}
