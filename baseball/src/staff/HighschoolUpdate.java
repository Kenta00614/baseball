package staff;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Staff;
import dao.SchoolDAO;

@WebServlet("/staff/HighschoolUpdate")
public class HighschoolUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    	HttpSession session=request.getSession();


//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
    		String tournamentId = request.getParameter("tournamentId");
    		request.setAttribute("tournamentId", tournamentId);
    		SchoolDAO dao = new SchoolDAO();
		    try {
		        List<School> schools = dao.searchSchool(Integer.parseInt(tournamentId));
		        if (!schools.isEmpty()) {
		            request.setAttribute("schools", schools);
		            request.getRequestDispatcher("/staff/highschoolUpdate.jsp").forward(request, response);
		        } else {
		            request.getRequestDispatcher("/staff/highschoolRegistration.jsp").forward(request, response);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.sendRedirect("error.jsp"); // エラーページへの遷移
		    }
    	}
    }
}
