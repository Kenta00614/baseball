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

@WebServlet("/staff/HighschoolUpdate")
public class HighschoolUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SchoolDAO dao = new SchoolDAO();

        try {
            int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
            List<School> schools = dao.searchSchool(tournamentId);
            String[] schoolNames = new String[schools.size()];

            for (int i = 0; i < schools.size(); i++) {
                School school = schools.get(i);
                String updatedName = request.getParameter("schoolName" + school.getSchoolId());
                schoolNames[i] = updatedName;
            }

            dao.updateSchool(schoolNames, tournamentId);

            response.sendRedirect("HighschoolRegistrationDisplay?tournamentId=" + tournamentId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp");
        }
    }
}
