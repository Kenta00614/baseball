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
import bean.Tournament;
import dao.SchoolDAO;
import dao.TournamentDAO;

@WebServlet("/staff/TournamentUpdateInput")
public class TournamentUpdateInput extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TournamentDAO tournamentDAO = new TournamentDAO();
        SchoolDAO schoolDAO = new SchoolDAO();

        try {
            List<Tournament> tournamentList = tournamentDAO.getTournamentDetail();
            Tournament tournament = tournamentList.get(tournamentList.size() - 1);
            session.setAttribute("tournament", tournament);

            List<School> schoolList = schoolDAO.searchSchool(tournament.getTournamentId());
            School school = new School();
            school.setSchoolId(0);
            school.setName("なし");
            schoolList.add(school);

            request.setAttribute("schoolList", schoolList);
            request.getRequestDispatcher("/staff/tournamentUpdateInput.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
