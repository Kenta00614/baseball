package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer/TicketSelectAll")
public class TicketSelectAll extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int count = Integer.parseInt(request.getParameter("count"));
    	String seat = request.getParameter("seat");


        request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);
    }
}
