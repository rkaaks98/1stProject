package farmstory.dao;

import java.util.List;
import farmstory.CountableDAO;
import farmstory.dto.EventDTO;
import farmstory.exception.DataAccessException;
import farmstory.util.ConnectionHelper;


public class EventDAO implements CountableDAO<EventDTO> {
  private ConnectionHelper helper;

  public EventDAO(ConnectionHelper helper) {
    this.helper = helper;
  }

  @Override
  public void insert(EventDTO dto) throws DataAccessException {
    // TODO Auto-generated method stub

  }

  @Override
  public EventDTO select(EventDTO dto) throws DataAccessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<EventDTO> selectAll() throws DataAccessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(EventDTO dto) throws DataAccessException {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(EventDTO dto) throws DataAccessException {
    // TODO Auto-generated method stub

  }

  @Override
  public int count() {
    // TODO Auto-generated method stub
    return 0;
  }
}
