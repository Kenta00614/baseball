package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DuelExp;
import bean.Match;
import bean.Tournament;
import dao.DuelDAO;
import dao.MatchDAO;
import dao.TournamentDAO;

@WebServlet("/customer/Main")
public class Main extends HttpServlet {

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Match> matchList = new ArrayList();
    	List<Tournament> tournamentList = new ArrayList();
		List<List<DuelExp>> duelList = new ArrayList<List<DuelExp>>();

		MatchDAO matchDAO = new MatchDAO();
		DuelDAO duelDAO = new DuelDAO();
		TournamentDAO tournamentDAO = new TournamentDAO();

		try {
//			tournamntの情報取得
			tournamentList = tournamentDAO.getTournamentDetail();
//			最後に登録されているトーナメントのmatch情報を取得
			matchList = matchDAO.searchMatchTournament(tournamentList.get(tournamentList.size()-1).getTournamentId());

//			duelの情報をListに詰める
			for(Match match: matchList){
				List<DuelExp> array = new ArrayList<>();
				array.add(duelDAO.getDuelDetail(match.getDuel1()));
				array.add(duelDAO.getDuelDetail(match.getDuel2()));
				array.add(duelDAO.getDuelDetail(match.getDuel3()));
				array.add(duelDAO.getDuelDetail(match.getDuel4()));
				duelList.add(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("tournament", tournamentList.get(tournamentList.size()-1));
		request.setAttribute("duelList",duelList);
		request.setAttribute("matchList",matchList);
        request.getRequestDispatcher("/customer/main.jsp").forward(request, response);
    }
}
