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

import bean.Tickets;
import common.Constants;
import dao.TicketsDAO;

@WebServlet("/staff/RefundComplete")
public class RefundComplete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session=request.getSession();
    	String ticketId = (String) session.getAttribute("ticketId");

    	List<Tickets> list=new ArrayList<>();
    	TicketsDAO TD=new TicketsDAO();

    	try{
    		list = TD.getTicketsInfo(ticketId);
			List<Integer> price=new ArrayList<>();

    		for(Tickets t : list){

    			String seatId = t.getSeatId();
    			String seatType = seatId.substring(0,2);


    			boolean isChild = t.isChild();
    		    if (isChild && (seatType.equals("0F") || seatType.equals("0T") || seatType.equals("0R") || seatType.equals("0L"))) {
    		        if (seatType.equals("0F") || seatType.equals("0T")) {
    		            price.add(1200);
    		        } else {
    		            price.add(200);
    		        }
    		    } else {
    	            price.add(Constants.SEAT_PRICE.get(seatType));
    		    }

    		}

    		int num = TD.changePaid(ticketId);

    		request.setAttribute("price", price);
    		request.setAttribute("list", list);
            request.getRequestDispatcher("/staff/refundComplete.jsp").forward(request, response);

    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }
}
