package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/staff/HighschoolRegistrationDisplay")
public class HighschoolRegistrationDisplay extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 大会IDをリクエスト属性にセット
        String tournamentId = request.getParameter("tournamentId");
        request.setAttribute("tournamentId", tournamentId);

        request.getRequestDispatcher("/staff/highschoolRegistration.jsp").forward(request, response);
    }
}
