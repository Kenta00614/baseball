package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer/TicketQr")
public class TicketQr extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
//    	チケットID
    	String ticketsId = request.getParameter("ticketsId");
//    	第何回
    	int ordinalNum = Integer.parseInt(request.getParameter("ordinalNum"));
//    	大会名
    	String tournamentName = request.getParameter("tournamentName");
//    	日にち
    	String dateStr = request.getParameter("dateStr");
//    	曜日
    	String eventDayOfWeek = request.getParameter("eventDayOfWeek");

    	request.setAttribute("ticketsId", ticketsId);
    	request.setAttribute("ordinalNum", ordinalNum);
    	request.setAttribute("tournamentName", tournamentName);
    	request.setAttribute("dateStr", dateStr);
    	request.setAttribute("eventDayOfWeek", eventDayOfWeek);
        request.getRequestDispatcher("/customer/ticketQr.jsp").forward(request, response);
    }
}
