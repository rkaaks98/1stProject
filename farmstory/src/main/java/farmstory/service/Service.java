package farmstory.service;

import java.util.List;
import farmstory.DataAccessObject;
import farmstory.DataTransferObject;
import farmstory.exception.DataAccessException;

/**
 * 추상 클래스. 싱글톤 패턴을 사용하지 않고, 내부적으로 사용되는 DAO를 생성자를 통해 인자로 전달 받는다. 구체적인 클래스 또는 인터페이스의 구현체를 type으로서 사용하는
 * 것보다 추상클래스 또는 인터페이스를 type으로 사용하는 것이 좋다. <br>
 * 그 이유는, 구현체의 코드가 변경되어도 인터페이스나 추상 클래스에 명시되어 있는 메서드를 그대로 써도 되기 때문이다.
 * 
 * MVC 패턴에서 service 레이어는 DAO의 직접적인 사용을 방지하기 위한 것이므로 인터페이스의 메서드는 {@link DataTransferObject}와 본질적으로
 * 같다.
 * 
 * 
 * @param <T> {@link DataTransferObject}를 상속하는 DTO. {@link #create(DataTransferObject)},
 *        {@link #get(DataTransferObject)}, {@link #getAll()}, {@link #update(DataTransferObject)},
 *        {@link #delete(DataTransferObject)} 메서드의 반환 타입 또는 파라미터 타입.
 */
public interface Service<T extends DataTransferObject> {
  /**
   * {@link farmstory.DataAccessObject#insert(DataTransferObject)} 메서드를 호출한다.
   */
  void create(T dto) throws DataAccessException;

  /**
   * {@link DataAccessObject#select(DataTransferObject)}를 호출하고 호출 결과를 반환한다.
   * 
   * @param dto {@link DataTransferObject}를 구현하는 DTO.
   * @return 제네릭 타입에 명시된 DTO를 반환
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  T get(T dto) throws DataAccessException;

  /**
   * {@link DataAccessObject#selectAll()}를 호출하고 호출 결과를 반환한다.
   * 
   * @return 모든 데이터의 DTO 리스트
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  List<T> getAll() throws DataAccessException;

  /**
   * {@link DataAccessObject#update(DataTransferObject)}를 호출한다.
   * 
   * @param dto {@link DataTransferObject}를 구현하는 DTO
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  void update(T dto) throws DataAccessException;

  /**
   * {@link DataAccessObject#delete(DataTransferObject)}를 호출한다.
   * 
   * @param dto {@link DataTransferObject}를 구현하는 DTO
   * @throws DataAccessException DB 작업 중 예외가 발생
   */
  void delete(T dto) throws DataAccessException;
}
