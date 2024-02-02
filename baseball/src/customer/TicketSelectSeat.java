package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Match;
import bean.Seat;
import bean.Spectator;
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
		HttpSession session=request.getSession();

		List<Tournament> list=new ArrayList<>();
		List<Match> match1=new ArrayList<>();
		Tournament lastTour=null;

		if(session.getAttribute("match") !=null){
			session.removeAttribute("match");
		}

		try {
//			大会情報取得
			TournamentDAO tourDao=new TournamentDAO();
			list=tourDao.getTournamentDetail();
//			最後の大会情報
			for(Tournament tour1: list){
				lastTour=tour1;
			}

//			同じ大会の試合日情報を取得
			MatchDAO matDao=new MatchDAO();
			match1=matDao.searchMatchTournament(lastTour.getTournamentId());

			session.setAttribute("tour", lastTour);
			request.setAttribute("match",match1);
			request.setAttribute("canselPurchase","2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/customer/ticketPurchase.jsp").forward(request, response);
        return;
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		値取得
		HttpSession session=request.getSession();

// 		セッションからspectatoridを取得
    	List<Spectator> spectatorIds =  (List<Spectator>)session.getAttribute("spectatorIds");
    	String countStr = String.valueOf(session.getAttribute("count"));
    	int count = 0;
    	if(countStr != null){
    		count = Integer.parseInt(countStr);
    	}
    	Tournament tour = (Tournament)session.getAttribute("tour");
    	String seat = (String)session.getAttribute("seat");
    	Match match = (Match)session.getAttribute("match");

//    	セッション切れのとき購入画面へ
    	if (spectatorIds == null || countStr == null || seat == null || match == null || tour == null) {
    		List<Tournament> list=new ArrayList<>();
    		List<Match> match1=new ArrayList<>();
    		Tournament lastTour=null;

    		if(session.getAttribute("match") !=null){
    			session.removeAttribute("match");
    		}

    		try {
//    			大会情報取得
    			TournamentDAO tourDao=new TournamentDAO();
    			list=tourDao.getTournamentDetail();
//    			最後の大会情報
    			for(Tournament tour1: list){
    				lastTour=tour1;
    			}

//    			同じ大会の試合日情報を取得
    			MatchDAO matDao=new MatchDAO();
    			match1=matDao.searchMatchTournament(lastTour.getTournamentId());

    			session.setAttribute("tour", lastTour);
    			request.setAttribute("match",match1);
    			request.setAttribute("canselPurchase","1");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		request.getRequestDispatcher("/customer/ticketPurchase.jsp").forward(request, response);
            return;
    	}else{
	    	String block = request.getParameter("block");

			List<Tickets> blockRemain=new ArrayList<>();
			List<String> blockList=new ArrayList<>();

			TicketsDAO ticketDAO=new TicketsDAO();
			SeatDAO seatDAO=new SeatDAO();

			try {
	//			ブロックの残チケット取得
				blockRemain=ticketDAO.getBlockSurplus(block, match.getMatchId());

	//			残数が購入枚数より少ないとき
				if(blockRemain.size()<count){
	//				ブロックの取得
					blockList = seatDAO.getBlock(seat);

	//				前ページに戻るとき必要な情報
					request.setAttribute("blocks", blockList);
					request.setAttribute("remain", blockRemain.size());
					request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);
					return;
				}

				List<TicketsAndSeat> ticketAndSeat=new ArrayList<>();
	//			チケット表示に必要な情報取得
				ticketAndSeat=ticketDAO.selectTickets(match.getEventDate(), block);
				HashMap map = seatDAO.getSeat(block);
				Seat[][] seats=(Seat[][])map.get("seats");

	//			ブロックの販売中チケット情報
				request.setAttribute("tickets", ticketAndSeat);
	//			選択されたブロック
				session.setAttribute("block", block);
	//			ブロックの席情報
				session.setAttribute("seatsList", seats);
				//描画
				request.setAttribute("startNum",(int)map.get("startNum"));
				request.setAttribute("endNum",(int)map.get("endNum"));
				request.setAttribute("step",(int)map.get("step"));
		        request.getRequestDispatcher("/customer/ticketSelectSeat.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
}