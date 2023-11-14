package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Match;
import bean.Tournament;
import common.Constants;
import dao.MatchDAO;
import dao.TicketsDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketSelectAll")
public class TicketSelectAll extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int count = Integer.parseInt(request.getParameter("count"));
    	String seat = request.getParameter("seat");
    	int matchId = Integer.parseInt(request.getParameter("matchId"));

    	int remaining=0;
    	Match match=null;
		Tournament tour=null;

    	TicketsDAO ticketDAO=new TicketsDAO();
   	 	MatchDAO matchDAO=new MatchDAO();
   	 	TournamentDAO tourDAO=new TournamentDAO();
    	try {
//    		チケット残数
			remaining=ticketDAO.getTypeSurplus(seat);
			match=matchDAO.getMatchInfo(matchId);
			tour=tourDAO.getTournamentInfo(match.getTournamentId());

//			希望枚数よりチケットが少ない場合前の画面に戻る
			if(remaining<count){
				request.setAttribute("seatType",Constants.SEAT_TYPE );
				request.setAttribute("remaining", remaining);
				request.setAttribute("match", match);
				request.setAttribute("tour",tour);
				request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
			}else{

				request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
