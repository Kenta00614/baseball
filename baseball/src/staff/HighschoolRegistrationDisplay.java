package staff;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import dao.SchoolDAO;

@WebServlet("/staff/HighschoolRegistrationDisplay")
public class HighschoolRegistrationDisplay extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String tournamentId = request.getParameter("tournamentId");
	    request.setAttribute("tournamentId", tournamentId);

	    SchoolDAO dao = new SchoolDAO();
	    try {
	        List<School> schools = dao.searchSchool(Integer.parseInt(tournamentId));
	        if (!schools.isEmpty()) {
	            request.setAttribute("schools", schools);
	            request.getRequestDispatcher("/staff/highschoolDisplay.jsp").forward(request, response);
	        } else {
	            request.getRequestDispatcher("/staff/highschoolRegistration.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("errorPage.jsp"); // エラーページへの遷移
	    }
	}
}