package staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TicketsDAO;

@WebServlet("/staff/RefundComplete")
public class RefundComplete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session=request.getSession();
    	String ticketId = (String) session.getAttribute("ticketId");

    	TicketsDAO DAO=new TicketsDAO();
		List<Integer> price=new ArrayList<>();




    	try{
    		int num=DAO.changePaid(ticketId);

    		request.setAttribute(ticketId, "ticketId");
            request.getRequestDispatcher("/staff/refundComplete.jsp").forward(request, response);

    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }
}
