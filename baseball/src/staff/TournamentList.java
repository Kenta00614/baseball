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

@WebServlet("/staff/TournamentList")
public class TournamentList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        TournamentDAO dao = new TournamentDAO();
        String name = request.getParameter("name");
        try {
            // データベースから大会情報のリストを取得
            List<Tournament> tournamentList = dao.getTournamentDetail();
            // リクエスト属性に大会情報リストをセット
            request.setAttribute("tournamentList", tournamentList);
            request.setAttribute("name", name);
            // JSPにフォワード
            request.getRequestDispatcher("/staff/tournamentList.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
