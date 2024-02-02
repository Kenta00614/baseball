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
import bean.Spectator;
import bean.TicketsAndSeat;
import bean.Tournament;
import common.Constants;
import dao.MatchDAO;
import dao.SpectatorDAO;
import dao.TicketsDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketConfirm")
public class TicketConfirm extends HttpServlet {
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String seat = (String)session.getAttribute("seat");
		Match match = (Match)session.getAttribute("match");
		Tournament tour = (Tournament)session.getAttribute("tour");

//		チケット購入のID
		String[] selTickets = request.getParameter("tickets").split(",");
//		大人か子供か
		String[] selChils = request.getParameter("child").split(",");
//		料金配列
		List<Integer> price=new ArrayList<>();
//		ログイン情報
		List<Spectator> specId = (List<Spectator>)session.getAttribute("spectatorIds");
		int point = 0;

//    	ログインしていないとき購入画面へ
    	if (specId == null || seat == null || match == null || tour == null) {
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
	//		座席と大人か子供別の料金判断
			for (String child : selChils) {
			    if (child.equals("子供") && (seat.equals("0F") || seat.equals("0T") || seat.equals("0R") || seat.equals("0L"))) {
			        if (seat.equals("0F") || seat.equals("0T")) {
			            price.add(1200);
			        } else {
			            price.add(200);
			        }
			    } else {
		            price.add(Constants.SEAT_PRICE.get(seat));
			    }
			}


			List<TicketsAndSeat> selTicketsData=new ArrayList<>();

			TicketsDAO ticketDAO=new TicketsDAO();
			SpectatorDAO spectatorDAO=new SpectatorDAO();
			try {
	//			購入するチケットの情報取得
				selTicketsData = ticketDAO.getSelectTickets(selTickets);
	//			ログインしている人のポイント情報取得
				point = spectatorDAO.getSpecPoint(specId.get(0).getSpectatorId());

			} catch (Exception e) {
				e.printStackTrace();
			}

	//		ログインしている人のpoint
			request.setAttribute("point", point);
	//		値段
			request.setAttribute("price", price);
	//		子供か大人
			session.setAttribute("selChils", selChils);
	//		チケットの情報
			request.setAttribute("selTicketsData", selTicketsData);
			session.setAttribute("selTickets", selTickets);
			request.getRequestDispatcher("/customer/ticketConfirm.jsp").forward(request, response);
    	}
    }
}