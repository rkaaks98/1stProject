package farmstory;

import java.util.List;
import farmstory.dto.OrderDTO;
import farmstory.dto.UserDTO;
import farmstory.exception.DataAccessException;
import farmstory.util.ConnectionHelper;

/**
 * INSERT, SELECT, SELECT *, UPDATE, DELETE를 수행하는 모든 DAO는 본 인터페이스를 구현해야만 한다. 예를 들어, UserDAO의 경우,
 * UserDTO를 반환하므로 아래와 같이 선언한다. <br>
 * <code>
 *  DataAccessObject<UserDTO> dao = new UserDAO({@link ConnectionHelper});
 * </code> <br>
 * 이후 DAO를 사용할 때에는 아래와 같이 사용한다. <br>
 * <code>
 * List<UserDTO> users = dao.selectAll();
 * </code>
 * 
 * @param <T> {@link #insert}, {@link #select(DataTransferObject)}, {@link #selectAll()}
 *        {@link #update(DataTransferObject)}, {@link delete} 메서드의 반환 타입.
 */
public interface DataAccessObject<T extends DataTransferObject> {
  /**
   * 새로운 DTO 객체를 데이터베이스에 저장한다.
   * 
   * @param dto DataTransferObject를 구현하는 DTO.
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  void insert(T dto) throws DataAccessException;


  /**
   * 특정 조건을 만족하는 데이터를 가져온다.
   * 
   * @param dto {@link DataTransferObject}를 구현하는 DTO.
   * @return 제네릭 타입에 명시된 DTO를 반환
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  T select(T dto) throws DataAccessException;

  /**
   * 테이블의 모든 데이터를 가져온다.
   * 
   * @return 모든 데이터의 DTO 리스트
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  List<T> selectAll() throws DataAccessException;

  /**
   * 특정 조건을 만족하는 데이터를 수정한다.
   * 
   * @param dto {@link DataTransferObject}를 구현하는 DTO
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  void update(T dto) throws DataAccessException;

  /**
   * 특정 조건을 만족하는 데이터를 삭제한다.
   * 
   * @param dto {@link DataTransferObject}를 구현하는 DTO
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  void delete(T dto) throws DataAccessException;
}
