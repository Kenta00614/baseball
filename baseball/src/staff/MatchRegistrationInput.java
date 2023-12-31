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

import bean.School;
import bean.Tournament;
import dao.SchoolDAO;
import dao.TournamentDAO;

@WebServlet("/staff/MatchRegistrationInput")
public class MatchRegistrationInput extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
//    	トーナメントのリスト
    	List<Tournament> tournamentList = new ArrayList();
    	Tournament tournament = new Tournament();
//    	高校名のリスト
    	List<School> schoolList = new ArrayList();

    	TournamentDAO tournamentDAO = new TournamentDAO();
    	SchoolDAO schoolDAO = new SchoolDAO();

    	try {
//    		大会情報取得
			tournamentList = tournamentDAO.getTournamentDetail();
//			大会情報セット
			tournament.setTournamentId(tournamentList.get(tournamentList.size()-1).getTournamentId());
			tournament.setYear(tournamentList.get(tournamentList.size()-1).getYear());
			tournament.setOrdinalNum(tournamentList.get(tournamentList.size()-1).getOrdinalNum());
			tournament.setName(tournamentList.get(tournamentList.size()-1).getName());
			tournament.setSeason(tournamentList.get(tournamentList.size()-1).getSeason());

//			高校情報取得
			schoolList = schoolDAO.searchSchool(tournament.getTournamentId());
//			なしの値
			School school=new School();
			school.setSchoolId(0);
			school.setName("なし");
			schoolList.add(school);

		} catch (Exception e) {
			e.printStackTrace();
		}

    	session.setAttribute("tournament",tournament );
    	request.setAttribute("schoolList",schoolList );
        request.getRequestDispatcher("/staff/matchRegistrationInput.jsp").forward(request, response);
    }
}
