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
import dao.TournamentDAO;

@WebServlet("/customer/TicketApplication")
public class TicketApplication extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//ログインじゃなかったときの処理はログイン実装後に記述



		//		値取得
		int matchId = Integer.parseInt(request.getParameter("matchId"));

		Match match=null;
		Tournament tour=null;

//		開催日取得
		 MatchDAO matchDAO=new MatchDAO();
		 TournamentDAO tourDAO=new TournamentDAO();
		 try {
			match=matchDAO.getMatchInfo(matchId);
			tour=tourDAO.getTournamentInfo(match.getTournamentId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		 request.setAttribute("remaining", -1);
		 request.setAttribute("seatType",Constants.SEAT_TYPE );
		 request.setAttribute("tour", tour);
		 request.setAttribute("match", match);
		 request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
    }
}