package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Duel;
import bean.Staff;
import dao.DuelDAO;
import dao.MatchDAO;

@WebServlet("/staff/MatchUpdateCompletion")
public class MatchUpdateCompletion extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	//		トーナメントID取得
			int tournamentId = (int) session.getAttribute("dispSelectTour");
	//		試合の情報取得
			int duel1School = Integer.parseInt(request.getParameter("duel1School1"));
	    	int duel1School2 = Integer.parseInt(request.getParameter("duel1School2"));
	    	String duel1Round = request.getParameter("duel1Round");
	    	int duel2School = Integer.parseInt(request.getParameter("duel2School1"));
	    	int duel2School2 = Integer.parseInt(request.getParameter("duel2School2"));
	    	String duel2Round = request.getParameter("duel2Round");
	    	int duel3School = Integer.parseInt(request.getParameter("duel3School1"));
	    	int duel3School2 = Integer.parseInt(request.getParameter("duel3School2"));
	    	String duel3Round = request.getParameter("duel3Round");
	    	int duel4School = Integer.parseInt(request.getParameter("duel4School1"));
	    	int duel4School2 = Integer.parseInt(request.getParameter("duel4School2"));
	    	String duel4Round = request.getParameter("duel4Round");
	    	int duelId1 = Integer.parseInt(request.getParameter("duel1"));
	    	int duelId2 = Integer.parseInt(request.getParameter("duel2"));
	    	int duelId3 = Integer.parseInt(request.getParameter("duel3"));
	    	int duelId4 = Integer.parseInt(request.getParameter("duel4"));
	    	String status1 = request.getParameter("status1");
	    	String status2 = request.getParameter("status2");
	    	String status3 = request.getParameter("status3");
	    	String status4 = request.getParameter("status4");
	    	int matchId = Integer.parseInt(request.getParameter("matchId"));

	    	int newDuelId = 0;
	    	DuelDAO duelDAO = new DuelDAO();
	    	MatchDAO matchDAO = new MatchDAO();

	//    	試合1
	    	Duel duel = new Duel();
	    	duel.setSchool1(duel1School);
	    	duel.setSchool2(duel1School2);
	    	duel.setRound(duel1Round);
	    	duel.setStatus(status1);
	    	duel.setRound(duel1Round);
	    	duel.setDuelId(duelId1);

	    	try {
	    		if(duelId1 > 0){
			    	int num = duelDAO.changeDuel(duel);
			    	if(num == 0){
			    		matchDAO.changeMatch(num, matchId, 1);
			    	}
				}else{
					newDuelId = duelDAO.insertDuel(duel);
					matchDAO.changeMatch(newDuelId, matchId, 1);
					newDuelId = 0;
				}


	//			試合2
				duel.setSchool1(duel2School);
		    	duel.setSchool2(duel2School2);
		    	duel.setRound(duel2Round);
		    	duel.setStatus(status2);
		    	duel.setRound(duel2Round);
		    	duel.setDuelId(duelId2);
				if(duelId2 > 0){
			    	int num = duelDAO.changeDuel(duel);
			    	if(num == 0){
			    		matchDAO.changeMatch(num, matchId, 2);
			    	}
				}else{
					newDuelId = duelDAO.insertDuel(duel);
					matchDAO.changeMatch(newDuelId, matchId, 2);
					newDuelId = 0;
				}

	//			試合3
				duel.setSchool1(duel3School);
		    	duel.setSchool2(duel3School2);
		    	duel.setRound(duel3Round);
		    	duel.setStatus(status3);
		    	duel.setRound(duel3Round);
		    	duel.setDuelId(duelId3);
				if(duelId3 > 0){
					int num = duelDAO.changeDuel(duel);
					if(num == 0){
			    		matchDAO.changeMatch(num, matchId, 3);
			    	}
				}else{
					newDuelId = duelDAO.insertDuel(duel);
					matchDAO.changeMatch(newDuelId, matchId, 3);
					newDuelId = 0;
				}

	//			試合4
				duel.setSchool1(duel4School);
		    	duel.setSchool2(duel4School2);
		    	duel.setRound(duel4Round);
		    	duel.setStatus(status4);
		    	duel.setRound(duel4Round);
		    	duel.setDuelId(duelId4);
				if(duelId4 > 0){
					int num = duelDAO.changeDuel(duel);
					if(num == 0){
			    		matchDAO.changeMatch(num, matchId, 4);
			    	}
				}else{
					newDuelId = duelDAO.insertDuel(duel);
					matchDAO.changeMatch(newDuelId, matchId, 4);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

	    	request.setAttribute("tournamentId", tournamentId);
	        request.getRequestDispatcher("/staff/matchUpdateCompletion.jsp").forward(request, response);
	    }
	}
}
