package customer;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Match;
import bean.Tournament;
import dao.MatchDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketPurchase")
public class TicketPurchase extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		List<Tournament> list=new ArrayList<>();
		List<Match> pullMatch=new ArrayList<>();
		List<Match> match=new ArrayList<>();
		Tournament lastTour=null;

		if(session.getAttribute("match") !=null){
			session.removeAttribute("match");
		}
		try {
//			大会情報取得
			TournamentDAO tourDao=new TournamentDAO();
			list=tourDao.getTournamentDetail();
//			最後の大会情報
			for(Tournament tour: list){
				lastTour=tour;
			}

//			同じ大会の試合日情報を取得
			MatchDAO matDao=new MatchDAO();
			pullMatch=matDao.searchMatchTournament(lastTour.getTournamentId());
			List<Date> sortDate=new ArrayList<>();
//			日付のソート
			for(Match m: pullMatch){
				sortDate.add(m.getEventDate());
			}
//			日付ソート完了
			Collections.sort(sortDate);
//			matchのソート
			for(int i=0; i<sortDate.size(); i++){
				for(Match p: pullMatch){
					if(sortDate.get(i).compareTo(p.getEventDate())==0){
						match.add(p);
					}
				}
			}

			session.setAttribute("tour", lastTour);
			request.setAttribute("match",match);
		} catch (Exception e) {
			e.printStackTrace();
		}
        request.getRequestDispatcher("/customer/ticketPurchase.jsp").forward(request, response);
    }
}