package staff;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Duel;
import bean.Match;
import bean.School;
import bean.Tournament;
import dao.DuelDAO;
import dao.MatchDAO;
import dao.SchoolDAO;

@WebServlet("/staff/MatchRegistrationCompletion")
public class MatchRegistrationCompletion extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
//		開催日取得
		Tournament tournament = (Tournament)session.getAttribute("tournament");
		String eventDateMonth = request.getParameter("eventDateMonth");
		String eventDateDate = request.getParameter("eventDateDate");
//		販売開始日取得
		String saleAtMonth = request.getParameter("saleAtMonth");
		String saleAtDate = request.getParameter("saleAtDate");
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

//    	試合日情報
    	Match match = new Match();
    	MatchDAO matchDAO = new MatchDAO();
    	DuelDAO duelDAO = new DuelDAO();

//    	試合1
    	Duel duel1 = new Duel();
    	duel1.setSchool1(duel1School);
    	duel1.setSchool2(duel1School2);
    	duel1.setRound(duel1Round);
//    	試合２
    	Duel duel2 = new Duel();
    	duel2.setSchool1(duel2School);
    	duel2.setSchool2(duel2School2);
    	duel2.setRound(duel2Round);
//    	試合３
    	Duel duel3 = new Duel();
    	duel3.setSchool1(duel3School);
    	duel3.setSchool2(duel3School2);
    	duel3.setRound(duel3Round);
//    	試合４
    	Duel duel4 = new Duel();
    	duel4.setSchool1(duel4School);
    	duel4.setSchool2(duel4School2);
    	duel4.setRound(duel4Round);

//		試合日情報
    	match.setEventDate(Date.valueOf(tournament.getYear()+"-"+eventDateMonth+"-"+eventDateDate));
    	match.setSaleStartAt(Date.valueOf(tournament.getYear()+"-"+saleAtMonth+"-"+saleAtDate));
    	match.setTournamentId(tournament.getTournamentId());

    	try {
//    		duelが登録できたらduelIdをセットそれ以外は0をセット
    		match.setDuel1(duelDAO.insertDuel(duel1));
    		match.setDuel2(duelDAO.insertDuel(duel2));
    		match.setDuel3(duelDAO.insertDuel(duel3));
    		match.setDuel4(duelDAO.insertDuel(duel4));

//    		試合日情報登録
			int num = matchDAO.insertMatch(match);
//			登録できなかった時
			if(num == 0){
//		    	高校名のリスト
		    	List<School> schoolList = new ArrayList();
		    	SchoolDAO schoolDAO = new SchoolDAO();
//				高校情報取得
				schoolList = schoolDAO.searchSchool(tournament.getTournamentId());
//				なしの値
				School school=new School();
				school.setSchoolId(0);
				school.setName("なし");
				schoolList.add(school);

				request.setAttribute("insertNum", num);
				request.setAttribute("schoolList",schoolList );
				request.getRequestDispatcher("/staff/matchRegistrationInput.jsp").forward(request, response);
				return;
			}
			request.setAttribute("tournamentId", tournament.getTournamentId());
			session.removeAttribute("tournament");
		} catch (Exception e) {
			e.printStackTrace();
		}
        request.getRequestDispatcher("/staff/matchRegistrationCompletion.jsp").forward(request, response);
    }
}
