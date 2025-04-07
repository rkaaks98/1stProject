package farmstory.service;

import java.util.List;
import farmstory.DataAccessObject;
import farmstory.DataTransferObject;
import farmstory.exception.DataAccessException;

/**
 * {@link Service} 인터페이스를 구현하는 클래스. 아래와 같이 사용한다. <br>
 * 
 * <pre>
 * {@code
 * DataAccessObject<UserDTO> dao = new UserDAO(new {@link ConnectionHelper}());
 * Service<UserDTO> service = new DefaultService<>(dao);
 * List<UserDTO> users = service.getAll();
 * }
 * </pre>
 * 
 * 만약 {@link Service} 인터페이스에 정의되어 있는 메서드 외에 다른 메서드를 추가해야 한다면 아래와 같이 사용한다. <br>
 * 
 * <pre>
 * {@code
 * public class OrderService<T extends DataTransferObject> extends DefaultService<T> {
 *   public OrderService(DataAccessObject dao) {
 *     super(dao);
 *   }
 * 
 *   public void helloWorld() {
 *     System.out.println("Hello, world!");
 *   }
 * }
 * 
 * 
 * public class Main {
 *   public static void main(String[] args) {
 *     DataAccessObject<OrderDTO> dao = new OrderDAO(new ConnectionHelper());
 *     OrderService<OrderDTO> orderService = new OrderService<>(dao);
 *     orderService.helloWorld();
 *   }
 * }
 * }
 * </pre>
 * 
 * @param <T> {@link DataTransferObject}를 상속하는 DTO. {@link #create(DataTransferObject)},
 *        {@link #get(DataTransferObject)}, {@link #getAll()}, {@link #update(DataTransferObject)},
 *        {@link #delete(DataTransferObject)} 메서드의 반환 타입 또는 파라미터 타입.
 * 
 */
public class DefaultService<T extends DataTransferObject> implements Service<T> {
  /**
   * {@link DefaultService} 클래스가 내부적으로 사용할 DAO.
   */
  protected final DataAccessObject<T> dao;

  /**
   * 생성자. 싱글톤 패턴의 <code>getInstance()</code> 대신 {@link DataAccessObject}를 생성자 파라미터를 통해 전달받아 사용한다.
   * 
   * @param dao {@link DefaultService#dao}를 초기화 하기 위한 인자.
   */
  public DefaultService(DataAccessObject<T> dao) {
    this.dao = dao;
  }

  /**
   * {@link DataAccessObject#insert(DataTransferObject)} 메서드를 호출한다.
   */
  @Override
  public void create(T dto) throws DataAccessException {
    dao.insert(dto);
  }

  /**
   * {@link DataAccessObject#select(DataTransferObject)} 메서드를 호출하고 호출 결과를 반환한다.
   */
  @Override
  public T get(T dto) throws DataAccessException {
    return dao.select(dto);

  }

  /**
   * {@link DataAccessObject#selectAll()} 메서드를 호출하고 호출 결과를 반환한다.
   */
  @Override
  public List<T> getAll() throws DataAccessException {
    return dao.selectAll();
  }

  /**
   * {@link DataAccessObject#update(DataTransferObject)} 메서드를 호출한다.
   */
  @Override
  public void update(T dto) throws DataAccessException {
    dao.update(dto);
  }

  /**
   * {@link DataAccessObject#delete(DataTransferObject)} 메서드를 호출한다.
   */
  @Override
  public void delete(T dto) throws DataAccessException {
    dao.delete(dto);
  }
}
