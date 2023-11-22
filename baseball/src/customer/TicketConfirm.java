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
import common.Constants;
import dao.TicketsDAO;

@WebServlet("/customer/TicketConfirm")
public class TicketConfirm extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		String seat = (String)session.getAttribute("seat");

//		チケット購入のID
		String[] selTickets = request.getParameter("tickets").split(",");
//		大人か子供か
		String[] selChils = request.getParameter("child").split(",");
		List<Integer> price=new ArrayList<>();

		System.out.println(selChils[0]);
		for(String child: selChils){
			if(child.equals("子供") && (seat.equals("0F") || seat.equals("0T") || seat.equals("0R") || seat.equals("0L"))){
				if(seat.equals("0F") || seat.equals("0T")){
					price.add(1200);
					System.out.println(1200);
				}else{
					price.add(200);
					System.out.println(200);
				}
			}else{
				price.add(Constants.SEAT_PRICE.get(seat));
				System.out.println("大人の値段");
			}
		}

		List<TicketsAndSeat> selTicketsData=new ArrayList<>();

		TicketsDAO ticketDAO=new TicketsDAO();
		try {
			selTicketsData = ticketDAO.getSelectTickets(selTickets);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("price", price);
		request.setAttribute("selChils", selChils);
		request.setAttribute("selTicketsData", selTicketsData);
		request.getRequestDispatcher("/customer/ticketConfirm.jsp").forward(request, response);
	}
}
