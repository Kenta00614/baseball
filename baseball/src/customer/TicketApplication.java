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
import bean.Tournament;
import common.Constants;
import dao.MatchDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketApplication")
public class TicketApplication extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Match match = (Match) session.getAttribute("match");
		Tournament tour = (Tournament) session.getAttribute("tour");
		List<String> seatType = new ArrayList<>();

		String[] seatOrder = {"0B","0F","0T","0R","0L"};

        for (String key : seatOrder) {
            String value = Constants.SEAT_TYPE.get(key);
            seatType.add(value);
        }

        if (match == null) {
            match = new Match();

//            値取得
    		String matchIdStr = request.getParameter("matchId");

//    		セッション切れかつ戻るボタン押下されたとき
    		if(matchIdStr == null){
    			List<Tournament> list=new ArrayList<>();
    			Tournament lastTour=null;
    			List<Match> match1=new ArrayList<>();
    			try {
//    				大会情報取得
    				TournamentDAO tourDao=new TournamentDAO();
    				list=tourDao.getTournamentDetail();
//    				最後の大会情報
    				for(Tournament tour1: list){
    					lastTour=tour1;
    				}

//    				同じ大会の試合日情報を取得
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

    		int matchId = Integer.parseInt(matchIdStr);

//    			開催日取得
    		 MatchDAO matchDAO=new MatchDAO();
    		 try {
    			match=matchDAO.getMatchInfo(matchId);

    			if(tour == null){
    				List<Tournament> list=new ArrayList<>();
//    				大会情報取得
    				TournamentDAO tourDao=new TournamentDAO();
    				list=tourDao.getTournamentDetail();
//    				最後の大会情報
    				for(Tournament tournament: list){
    					tour=tournament;
    				}
    				session.setAttribute("tour", tour);
    			}

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
			session.setAttribute("match", match);
        }
        request.setAttribute("remaining", -1);
        request.setAttribute("seatType",seatType );
        request.setAttribute("seatOrder", seatOrder);
		request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
    }
}