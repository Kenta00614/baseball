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
import bean.DuelExp;
import bean.Match;
import bean.School;
import bean.Staff;
import bean.Tournament;
import common.Constants;
import dao.DuelDAO;
import dao.MatchDAO;
import dao.SchoolDAO;
import dao.TournamentDAO;

@WebServlet("/staff/MatchUpdateInput")
public class MatchUpdateInput extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	    	Date eventDate =  Date.valueOf(request.getParameter("date"));
	    	int tournamentId = (Integer)session.getAttribute("dispSelectTour");

	//    	トーナメントのリスト
	    	Tournament tournament = new Tournament();
	//    	高校名のリスト
	    	List<School> schoolList = new ArrayList();
	//    	試合日情報
	    	List<Match> matchList = new ArrayList();
	//    	試合情報
	    	List<Duel> duelList = new ArrayList();
	    	List<String> duelStatus = new ArrayList();


	    	TournamentDAO tournamentDAO = new TournamentDAO();
	    	SchoolDAO schoolDAO = new SchoolDAO();
	    	MatchDAO matchDAO = new MatchDAO();
	    	DuelDAO duelDAO = new DuelDAO();


	    	try {
	//    		大会情報取得
				tournament = tournamentDAO.getTournamentInfo(tournamentId);

	//			高校情報取得
				schoolList = schoolDAO.searchSchool(tournamentId);
	//			なしの値
				School school=new School();
				school.setSchoolId(0);
				school.setName("なし");
				schoolList.add(school);

	//			試合日情報の取得
				matchList = matchDAO.searchMatchDetail(eventDate);
				DuelExp duelNoData = new DuelExp();
				duelNoData.setSchoolId1(0);
				duelNoData.setSchoolId2(0);

				duelList.add(duelDAO.getDuelDetail(matchList.get(0).getDuel1()));
				if(matchList.get(0).getDuel2() > 0){
					duelList.add(duelDAO.getDuelDetail(matchList.get(0).getDuel2()));
				}else{
					duelList.add(duelNoData);
				}
		    	if(matchList.get(0).getDuel3() > 0){
					duelList.add(duelDAO.getDuelDetail(matchList.get(0).getDuel3()));
		    	}else{
					duelList.add(duelNoData);
				}
		    	if(matchList.get(0).getDuel4() > 0 ){
					duelList.add(duelDAO.getDuelDetail(matchList.get(0).getDuel4()));
				}else{
					duelList.add(duelNoData);
				}

	//			statusのリスト作成
				duelStatus.add(Constants.DUEL_STATUS.get("1"));
				duelStatus.add(Constants.DUEL_STATUS.get("2"));
				duelStatus.add(Constants.DUEL_STATUS.get("3"));
				duelStatus.add(Constants.DUEL_STATUS.get("4"));

			} catch (Exception e) {
				e.printStackTrace();
			}

	    	request.setAttribute("duelStatus", duelStatus);
	    	request.setAttribute("duelList", duelList);
	    	request.setAttribute("matchList",matchList );
	    	request.setAttribute("tournament",tournament );
	    	request.setAttribute("schoolList",schoolList );
	        request.getRequestDispatcher("/staff/matchUpdateInput.jsp").forward(request, response);
	    }
    }
}
