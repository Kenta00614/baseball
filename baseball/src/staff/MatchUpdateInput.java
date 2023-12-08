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

import bean.School;
import bean.Tournament;
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
    	Date eventDate =  Date.valueOf(request.getParameter("date"));

//    	トーナメントのリスト
    	Tournament tournament = new Tournament();
//    	高校名のリスト
    	List<School> schoolList = new ArrayList();

    	TournamentDAO tournamentDAO = new TournamentDAO();
    	SchoolDAO schoolDAO = new SchoolDAO();

    	try {
//    		大会情報取得
			tournament = tournamentDAO.getTournamentInfo((Integer)session.getAttribute("dispSelectTour"));

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

    	request.setAttribute("tournament",tournament );
    	request.setAttribute("schoolList",schoolList );
        request.getRequestDispatcher("/staff/matchUpdateInput.jsp").forward(request, response);
    }
}
