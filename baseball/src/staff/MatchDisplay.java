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

import bean.Staff;
import bean.Tournament;
import dao.TournamentDAO;

@WebServlet("/staff/MatchDisplay")
public class MatchDisplay extends HttpServlet {
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
	    	List<Tournament> tournamentList = new ArrayList();
			TournamentDAO dao = new TournamentDAO();
	        try {
	            // データベースから大会情報のリストを取得
	            tournamentList = dao.getTournamentDetail();

	            if(tournamentList.size() == 0){
	            	request.getRequestDispatcher("/staff/tournamentNone.jsp").forward(request, response);
	            	return;
	            }
	        }catch (Exception e) {
	        	throw new ServletException(e);
	        }
	        // リクエスト属性に大会情報リストをセット
	        request.setAttribute("tournamentList", tournamentList);
	        request.getRequestDispatcher("/staff/matchDisplay.jsp").forward(request, response);
	    }
    }
}
