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
import dao.MatchDAO;
import dao.TournamentDAO;

@WebServlet("/customer/TicketPurchase")
public class TicketPurchase extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		List<Tournament> list=new ArrayList<>();
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
			match=matDao.searchMatchTournament(lastTour.getTournamentId());

			session.setAttribute("tour", lastTour);
			request.setAttribute("match",match);
		} catch (Exception e) {
			e.printStackTrace();
		}
        request.getRequestDispatcher("/customer/ticketPurchase.jsp").forward(request, response);
    }
}