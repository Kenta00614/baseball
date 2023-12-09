package staff;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tournament;
import dao.TournamentDAO;

@WebServlet("/staff/TournamentListChangeDisplay")
public class TournamentListChangeDisplay extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        TournamentDAO dao = new TournamentDAO();
        try {
            // データベースから大会情報のリストを取得
            List<Tournament> tournamentList = dao.getTournamentDetail();
            // リクエスト属性に大会情報リストをセット
            request.setAttribute("tournamentList", tournamentList);
            // JSPにフォワード
            request.getRequestDispatcher("/staff/tournamentListChange.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
