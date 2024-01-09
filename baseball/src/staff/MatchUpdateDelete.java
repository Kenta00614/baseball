package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;

@WebServlet("/staff/MatchUpdateDelete")
public class MatchUpdateDelete extends HttpServlet {
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
	    	String tournamentId = request.getParameter("tournamentId");
	    	String eventDate = request.getParameter("eventDate");

	    	request.setAttribute("tournamentId", tournamentId);
	    	request.setAttribute("eventDate", eventDate);
	        request.getRequestDispatcher("/staff/matchUpdateDelete.jsp").forward(request, response);
	    }
    }
}
