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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tournamentIdStr = request.getParameter("tournamentId");

        if (tournamentIdStr != null && !tournamentIdStr.isEmpty()) {
            int tournamentId = Integer.parseInt(tournamentIdStr);
            SchoolDAO dao = new SchoolDAO();

            try {
                List<School> schools = dao.searchSchool(tournamentId);
                if (schools != null && !schools.isEmpty()) {
                    // 高校名が登録されている場合
                    request.setAttribute("schools", schools);
                    request.getRequestDispatcher("/staff/highschoolDisplay.jsp").forward(request, response);
                } else {
                    // 登録されていない場合
                    request.setAttribute("tournamentId", tournamentId);
                    request.getRequestDispatcher("/staff/highschoolRegistration.jsp").forward(request, response);
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }
}
