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
import bean.PurchaseExp;
import bean.Spectator;
import bean.Tickets;
import bean.Tournament;
import common.Constants;
import dao.MatchDAO;
import dao.PurchaseDAO;
import dao.SeatDAO;
import dao.TicketsDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketSelectAll")
public class TicketSelectAll extends HttpServlet {

    @SuppressWarnings( "unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	値の取得
    	HttpSession session=request.getSession();
    	String countStr = request.getParameter("count");
    	int count=0;
    	if(countStr != null){
    		count = Integer.parseInt(countStr);
    	}

    	String seat = request.getParameter("seat");
    	Match match=(Match)session.getAttribute("match");
		Tournament tour = (Tournament) session.getAttribute("tour");

		if(countStr == null || seat == null || match == null || tour == null){
			List<Tournament> list=new ArrayList<>();
			Tournament lastTour=null;
			List<Match> match1=new ArrayList<>();
			try {
//				大会情報取得
				TournamentDAO tourDao=new TournamentDAO();
				list=tourDao.getTournamentDetail();
//				最後の大会情報
				for(Tournament tour1: list){
					lastTour=tour1;
				}

//				同じ大会の試合日情報を取得
				MatchDAO matDao=new MatchDAO();
				match1=matDao.searchMatchTournament(lastTour.getTournamentId());

				session.setAttribute("tour", lastTour);
				request.setAttribute("match",match1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("canselPurchase",1);
	        request.getRequestDispatcher("/customer/ticketPurchase.jsp").forward(request, response);
	        return;
		}


    	// セッションからspectatoridを取得
    	List<Spectator> spectatorIds =  (List<Spectator>)session.getAttribute("spectatorIds");

    	int remaining=0;
		List<Tickets> tickets=new ArrayList<>();
		List<String> blocks=new ArrayList<>();

    	TicketsDAO ticketDAO=new TicketsDAO();
   	 	SeatDAO seatDAO=new SeatDAO();
    	try {
//    		座種のチケット残数
    		tickets = ticketDAO.getTypeSurplus(seat,match.getMatchId());
			remaining=tickets.size();



//	    	ログインしていないときログイン画面へ
	    	if (spectatorIds == null) {
	    		session.setAttribute("seat", seat);
				session.setAttribute("count", count);
				session.setAttribute("remaining", remaining);
	            response.sendRedirect("login.jsp");
	            return;
	    	}

//			開催日の購入チケット取得
	    	List<PurchaseExp> purchaseList=new ArrayList<>();
	    	PurchaseDAO purchaseDAO=new PurchaseDAO();
	    	purchaseList = purchaseDAO.getDatePurchase(spectatorIds.get(spectatorIds.size()-1).getSpectatorId(),match.getEventDate());

	    	if(count+purchaseList.size()>6 || remaining<count){

	    		List<String> seatType = new ArrayList<>();

				String[] seatOrder = {"0B","0F","0T","0R","0L"};

		        for (String key : seatOrder) {
		            String value = Constants.SEAT_TYPE.get(key);
		            seatType.add(value);
		        }
		        request.setAttribute("seatOrder", seatOrder);
				request.setAttribute("seatType",seatType );
				if(remaining<count){
					request.setAttribute("remaining", remaining);
				}
				if(count+purchaseList.size()>6){
					request.setAttribute("countTic", 6-purchaseList.size());
					if(remaining >= count){
						request.setAttribute("remaining", -1);
					}
				}
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
