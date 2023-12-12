package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tournament;
import dao.TournamentDAO;

@WebServlet("/staff/TournamentUpdate")
public class TournamentUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");

        // フォームから送信されたデータを受け取る
        int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
        int year = Integer.parseInt(request.getParameter("year"));
        int ordinalNum = Integer.parseInt(request.getParameter("ordinalNum"));
        String name = request.getParameter("name");
        String season = request.getParameter("season");

        TournamentDAO dao = new TournamentDAO();
        Tournament tournament = new Tournament();
        tournament.setTournamentId(tournamentId);
        tournament.setYear(year);
        tournament.setOrdinalNum(ordinalNum);
        tournament.setName(name);
        tournament.setSeason(season);

        try {
            dao.changeTournament(tournament);
            // 更新が完了したら、適切なページにリダイレクトする
            response.sendRedirect("somePage.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
