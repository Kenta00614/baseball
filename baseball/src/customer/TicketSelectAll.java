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

import bean.Match;
import bean.Tickets;
import common.Constants;
import dao.SeatDAO;
import dao.TicketsDAO;

@WebServlet("/customer/TicketSelectAll")
public class TicketSelectAll extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	値の取得
    	HttpSession session=request.getSession();
    	int count = Integer.parseInt(request.getParameter("count"));
    	String seat = request.getParameter("seat");
    	Match match=(Match)session.getAttribute("match");

    	int remaining=0;
		List<Tickets> tickets=new ArrayList<>();
		List<String> blocks=new ArrayList<>();

    	TicketsDAO ticketDAO=new TicketsDAO();
   	 	SeatDAO seatDAO=new SeatDAO();
    	try {
//    		座種のチケット残数
    		tickets = ticketDAO.getTypeSurplus(seat,match.getMatchId());
			remaining=tickets.size();

//			希望枚数よりチケットが少ない場合前の画面に戻る
			if(remaining<count){
				request.setAttribute("seatType",Constants.SEAT_TYPE );
				request.setAttribute("remaining", remaining);
				request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
				return;
			}

//			通常の時送る値
			blocks = seatDAO.getBlock(seat);
			request.setAttribute("blocks", blocks);
			request.setAttribute("remain", -1);
			session.setAttribute("seat", seat);
			session.setAttribute("count", count);
			request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
