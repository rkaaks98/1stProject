package farmstory.service;

import farmstory.CountableDAO;
import farmstory.DataTransferObject;
import farmstory.exception.DataAccessException;

/**
 * {@link CountableDAO}를 사용하는 service 클래스를 위한 인터페이스.
 * 
 * MVC 패턴에서 service 레이어는 DAO의 직접적인 사용을 방지하기 위한 것이므로 인터페이스의 메서드는 {@link CountableDAO}와 본질적으로 같다.
 * 
 * @param <T> {@link #insert}, {@link #select(DataTransferObject)}, {@link #selectAll()}
 *        {@link #update(DataTransferObject)}, {@link #delete(DataTransferObject)} 메서드의 반환 타입 또는
 *        파라미터 타입.
 */
public interface CountableService<T extends DataTransferObject> extends Service<T> {
  /**
   * 
   * @return MySQL의 COUNT() 함수의 결과값
   */
  int count() throws DataAccessException;
}
