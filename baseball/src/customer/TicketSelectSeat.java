package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tickets;
import bean.Tournament;
import dao.TicketsDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketSelectSeat")
public class TicketSelectSeat extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		値取得
    	int count = Integer.parseInt(request.getParameter("count"));
    	String seat = request.getParameter("seat");
    	int matchId = Integer.parseInt(request.getParameter("matchId"));
    	String block = request.getParameter("block");

		List<Tickets> blockRemain=new ArrayList<>();
		Tournament tour=null;

		TicketsDAO ticketDAO=new TicketsDAO();
		TournamentDAO tourDAO=new TournamentDAO();

		try {
//			ブロックの残チケット
			blockRemain=ticketDAO.getBlockSurplus(block, matchId);
			tour=tourDAO.getTournamentInfo(matchId);

			request.setAttribute("block", block);
			request.setAttribute("matchId", matchId);
			request.setAttribute("seat", seat);
			request.setAttribute("tour",tour);
			request.setAttribute("count", count);
			if(blockRemain.size()<count){
				request.setAttribute("remain", blockRemain.size());
				request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);
			}else{
				request.setAttribute("blockRemain", blockRemain);
		        request.getRequestDispatcher("/customer/ticketSelectSeat.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}



    }
}
