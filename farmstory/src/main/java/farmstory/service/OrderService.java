package farmstory.service;

import farmstory.dao.OrderDAO;
import farmstory.dto.OrderDTO;

public class OrderService extends CountableDefaultService<OrderDTO> {

  private OrderDAO orderDAO;

  public OrderService(OrderDAO dao) {
    super(dao);
    this.orderDAO = dao;
  }

  // public List<OrderDTO> getAll(int offset, int limit) {
  // return orderDAO.selectAll(offset, limit);
  // }

  // public void placeOrder(String userId) {
  // orderDAO.placeOrder(userId);
  // }
}
