package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TicketsAndSeat;
import dao.TicketsDAO;

@WebServlet("/customer/TicketConfirm")
public class TicketConfirm extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
//		値取得
		String selTicket = request.getParameter("tickets");
		String[] selTickets = selTicket.split(",");

//    	String seat = (String)session.getAttribute("seat");
//    	String seat = (String)session.getAttribute("tour");
//    	Match match = (Match)session.getAttribute("match");
		List<TicketsAndSeat> selTicketsData=new ArrayList<>();

		TicketsDAO ticketDAO=new TicketsDAO();
		try {
			selTicketsData = ticketDAO.getSelectTickets(selTickets);
		} catch (Exception e) {
			e.printStackTrace();
		}


		request.setAttribute("selTicketsData", selTicketsData);
		request.getRequestDispatcher("/customer/ticketConfirm.jsp").forward(request, response);
	}
}
