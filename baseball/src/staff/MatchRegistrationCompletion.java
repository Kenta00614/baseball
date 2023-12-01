package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Duel;
import bean.Match;
import dao.DuelDAO;
import dao.MatchDAO;

@WebServlet("/staff/MatchRegistrationCompletion")
public class MatchRegistrationCompletion extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int duel1School = Integer.parseInt(request.getParameter("duel1School1"));
    	int duel1School2 = Integer.parseInt(request.getParameter("duel1School2"));
    	int duel1Round = Integer.parseInt(request.getParameter("duel1Round"));
    	int duel2School = Integer.parseInt(request.getParameter("duel2School1"));
    	int duel2School2 = Integer.parseInt(request.getParameter("duel2School2"));
    	int duel2Round = Integer.parseInt(request.getParameter("duel2Round"));
    	int duel3School = Integer.parseInt(request.getParameter("duel3School1"));
    	int duel3School2 = Integer.parseInt(request.getParameter("duel3School2"));
    	int duel3Round = Integer.parseInt(request.getParameter("duel3Round"));
    	int duel4School = Integer.parseInt(request.getParameter("duel3School1"));
    	int duel4School2 = Integer.parseInt(request.getParameter("duel3School2"));
    	int duel4Round = Integer.parseInt(request.getParameter("duel3Round"));

//    	試合日情報
    	Match match = new Match();
    	MatchDAO matchDAO = new MatchDAO();
    	DuelDAO duelDAO = new DuelDAO();

//    	第一試合
    	Duel duel1 = new Duel();

    	try {
    		duelDAO.insertDuel(duel1);
			matchDAO.insertMatch(match,duel1);
		} catch (Exception e) {
			e.printStackTrace();
		}
        request.getRequestDispatcher("/staff/matchRegistrationCompletion.jsp").forward(request, response);
    }
}
