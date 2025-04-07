package farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.OrderDAO;
import farmstory.dto.OrderDTO;
import farmstory.service.CountableDefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/order-delete.do")
public class OrderDeleteController extends HttpServlet {

    private static final long serialVersionUID = 161266416260600026L;
    private static final Logger logger = LoggerFactory.getLogger(OrderDeleteController.class.getName());

    private CountableDefaultService<OrderDTO> service;

    @Override
    public void init() throws ServletException {
        try {
            ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
            OrderDAO dao = new OrderDAO(helper);
            this.service = new CountableDefaultService<>(dao);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] orderIds = req.getParameterValues("orderIds"); // 체크박스에서 선택된 주문 IDs
            
            if (orderIds != null) {
                for (String orderId : orderIds) {
                    int orderID = Integer.parseInt(orderId); // 문자열을 정수로 변환
                    OrderDTO dto = new OrderDTO();
                    dto.setId(orderID);

                    // 서비스 레이어에서 삭제 처리
                    service.delete(dto);
                }
            }

            // 삭제 후, 목록 페이지로 리다이렉트
            resp.sendRedirect(req.getContextPath() + "/admin/order-list.do");

        } catch (Exception e) {
            logger.error("선택 삭제 중 오류 발생", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류 발생");
        }
    }
}
