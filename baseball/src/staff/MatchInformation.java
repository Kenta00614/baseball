package staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Match;
import dao.MatchDAO;

@WebServlet("/staff/MatchInformation")
public class MatchInformation extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));

		List<Match> matchList = new ArrayList();
		MatchDAO matchDAO = new MatchDAO();
		try {
			matchList = matchDAO.searchMatchTournament(tournamentId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("matchList",matchList);
        request.getRequestDispatcher("/staff/matchInformation.jsp").forward(request, response);
    }
}
