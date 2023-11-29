package customer;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TicketsDAO;

@WebServlet("/customer/TicketShare")
public class TicketShare extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String ticketsId = request.getParameter("ticketsId");
		int ordinalNum = Integer.parseInt(request.getParameter("ordinalNum"));
		String tournamentName = request.getParameter("tournamentName");
		String dateStr = request.getParameter("dateStr");
		String eventDayOfWeek = request.getParameter("eventDayOfWeek");
		String typeStr = request.getParameter("typeStr");
		String child = request.getParameter("child");
		int price = Integer.parseInt(request.getParameter("price"));
		String step = request.getParameter("step");
		String number = request.getParameter("number");

		UUID uuid = null;
		String url = null;

		TicketsDAO ticketsDAO = new TicketsDAO();

		try {
//			チケットのステータス変更
			uuid = ticketsDAO.ticketsShare(ticketsId);

//			サーバーのurl情報取得
			String scheme = request.getScheme();
			String serverName = request.getServerName();
			int serverPort = request.getServerPort();

//			url作成
			url =scheme+"://"+serverName+":"+serverPort+"/baseball/customer/TicketQr?id="+uuid;
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("ticketsId",ticketsId);
    	request.setAttribute("ordinalNum", ordinalNum);
    	request.setAttribute("tournamentName", tournamentName);
    	request.setAttribute("dateStr", dateStr);
    	request.setAttribute("eventDayOfWeek", eventDayOfWeek);
    	request.setAttribute("typeStr", typeStr);
    	request.setAttribute("child", child);
    	request.setAttribute("price", price);
    	request.setAttribute("step", step);
    	request.setAttribute("number", number);
    	request.setAttribute("url",url);
    	request.getRequestDispatcher("/customer/ticketShare.jsp").forward(request, response);
    }
}
