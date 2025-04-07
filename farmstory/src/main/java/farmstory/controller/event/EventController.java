package farmstory.controller.event;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import farmstory.dao.EventDAO;
import farmstory.dto.EventDTO;
import farmstory.service.CountableDefaultService;
import farmstory.service.DefaultService;
import farmstory.util.ConnectionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/events")
public class EventController extends HttpServlet {

	private static final long serialVersionUID = 1407461099015099674L;
	private static final Logger logger = LoggerFactory.getLogger(EventController.class.getName());
	private CountableDefaultService<EventDTO> service;
	
	@Override
	public void init() throws ServletException {
		try {
			ConnectionHelper helper = new ConnectionHelper("jdbc/farmstory");
			EventDAO dao = new EventDAO(helper);
			this.service = new CountableDefaultService<>(dao);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//List<EventDTO> events = service.getAllEvents();
			//String json = new Gson().toJson(events);
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			//resp.getWriter().write(json);
			
			
		}catch (Exception e) {
			e.getMessage();
		}
		
		 RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/event/event-calendar.jsp");
         dispatcher.forward(req, resp);
	}
	
	

}
