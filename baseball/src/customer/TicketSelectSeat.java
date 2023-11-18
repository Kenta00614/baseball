package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Match;
import bean.Tickets;
import bean.TicketsAndSeat;
import bean.Tournament;
import dao.MatchDAO;
import dao.SeatDAO;
import dao.TicketsDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketSelectSeat")
public class TicketSelectSeat extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		値取得
    	int count = Integer.parseInt(request.getParameter("count"));
    	String seat = request.getParameter("seat");
    	int matchId = Integer.parseInt(request.getParameter("matchId"));
    	String block = request.getParameter("block");

		List<Tickets> blockRemain=new ArrayList<>();
		Tournament tour=null;
		Match match=null;
		List<String> blockList=new ArrayList<>();

		TicketsDAO ticketDAO=new TicketsDAO();
		TournamentDAO tourDAO=new TournamentDAO();
		MatchDAO matchDAO=new MatchDAO();
		SeatDAO seatDAO=new SeatDAO();

		try {
//			ブロックの残チケット取得
			blockRemain=ticketDAO.getBlockSurplus(block, matchId);
			match=matchDAO.getMatchInfo(matchId);
			tour=tourDAO.getTournamentInfo(match.getTournamentId());

//			返す値
			request.setAttribute("matchId", matchId);
			request.setAttribute("seat", seat);
			request.setAttribute("tour",tour);
			request.setAttribute("count", count);

//			残数が購入枚数より少ないとき
			if(blockRemain.size()<count){
//				ブロックの取得
				blockList = seatDAO.getBlock(seat);

//				前ページに戻るとき必要な情報
				request.setAttribute("block", blockList);
				request.setAttribute("remain", blockRemain.size());
				request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);
				return;
			}

			List<TicketsAndSeat> ticketAndSeat=new ArrayList<>();
//			チケット表示に必要な情報取得
			ticketAndSeat=ticketDAO.selectTickets(match.getEventDate(), block);

//			販売中のチケット送信
			request.setAttribute("tickets", ticketAndSeat);
			request.setAttribute("block", block);
			request.setAttribute("blockRemain", blockRemain);
	        request.getRequestDispatcher("/customer/ticketSelectSeat.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
