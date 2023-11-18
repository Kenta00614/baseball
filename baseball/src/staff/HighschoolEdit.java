package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import dao.SchoolDAO;

@WebServlet("/staff/HighschoolEdit")
public class HighschoolEdit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int schoolId= Integer.parseInt(request.getParameter("schoolId"));
        SchoolDAO dao = new SchoolDAO();

        try {
            School school = dao.searchSchool(schoolName, tournamentId);
            request.setAttribute("school", school);
            request.getRequestDispatcher("/staff/highschoolEdit.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
