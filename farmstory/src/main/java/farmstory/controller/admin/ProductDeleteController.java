package farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farmstory.dao.ProductDAO;
import farmstory.dto.ProductDTO;
import farmstory.service.CountableDefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/product-delete.do")
public class ProductDeleteController extends HttpServlet {

	private static final long serialVersionUID = 161266416260600026L;
	private static final Logger logger = LoggerFactory.getLogger(ProductDeleteController.class.getName());

	private CountableDefaultService<ProductDTO> service;

	@Override
	public void init() throws ServletException {
		try {
			ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
			ProductDAO dao = new ProductDAO(helper);
			this.service = new CountableDefaultService<>(dao);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String[] productIds = req.getParameterValues("productIds"); // 체크박스에서 선택된 주문 IDs

			if (productIds != null) {
				for (String productId : productIds) {
					int productID = Integer.parseInt(productId); // 문자열을 정수로 변환
					ProductDTO dto = new ProductDTO();
					dto.setId(productID);

					// 서비스 레이어에서 삭제 처리
					service.delete(dto);
				}
			}

			// 삭제 후, 목록 페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/admin/product-list.do");

		} catch (Exception e) {
			logger.error("선택 삭제 중 오류 발생", e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류 발생");
		}
	}
}