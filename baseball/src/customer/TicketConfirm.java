package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer/TicketConfirm")
public class TicketConfirm extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] tickets = request.getParameterValues("tickets");


		for(String s: tickets){
			System.out.println(s);
		}
		request.getRequestDispatcher("/customer/ticketConfirm.jsp").forward(request, response);
	}
}
