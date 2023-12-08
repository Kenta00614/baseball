package staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DuelExp;
import bean.Match;
import dao.DuelDAO;
import dao.MatchDAO;

@WebServlet("/staff/MatchInformation")
public class MatchInformation extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));

		List<Match> matchList = new ArrayList();
		List<List<DuelExp>> duelList = new ArrayList<List<DuelExp>>();
		MatchDAO matchDAO = new MatchDAO();
		DuelDAO duelDAO = new DuelDAO();

		try {

			matchList = matchDAO.searchMatchTournament(tournamentId);

			for(Match m: matchList){
				List<DuelExp> array = new ArrayList<>();
				array.add(duelDAO.getDuelDetail(m.getDuel1()));
				array.add(duelDAO.getDuelDetail(m.getDuel2()));
				array.add(duelDAO.getDuelDetail(m.getDuel3()));
				array.add(duelDAO.getDuelDetail(m.getDuel4()));
				duelList.add(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute("dispSelectTour",tournamentId );
		request.setAttribute("duelList",duelList);
		request.setAttribute("matchList",matchList);
        request.getRequestDispatcher("/staff/matchInformation.jsp").forward(request, response);
    }
}
