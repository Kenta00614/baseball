package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import bean.Tournament;
import dao.TournamentDAO;

@WebServlet("/staff/TournamentUpdateCompletion")
public class TournamentUpdateCompletion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	    	String year = request.getParameter("year");
	        String ordinalNum = request.getParameter("ordinalNum");
	    	String season = request.getParameter("season");
	        String name = request.getParameter("name");
	        String tournamentId = request.getParameter("tournamentId");

	        TournamentDAO tournamnetDAO = new TournamentDAO();
	        Tournament tournament = new Tournament();


	        try {
//	        	大会情報
	        	tournament.setYear(Integer.parseInt(year));
	        	tournament.setOrdinalNum(Integer.parseInt(ordinalNum));
	        	tournament.setSeason(season);
	        	tournament.setName(name);
	        	tournament.setTournamentId(Integer.parseInt(tournamentId));

				int result = tournamnetDAO.changeTournament(tournament);
				if(result > 0){
					request.getRequestDispatcher("/staff/tournamentUpdateCompletion.jsp").forward(request, response);
					return;
				}else {
	                // 登録失敗
	                response.sendRedirect("/baseball/staff/error.jsp"); // エラー画面へリダイレクト
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
}
