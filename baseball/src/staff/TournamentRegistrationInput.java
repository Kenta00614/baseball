package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tournament;
import dao.TournamentDAO;

@WebServlet("/staff/TournamentRegistrationInput")

public class TournamentRegistrationInput extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");

        // リクエストパラメータの取得
        int year = Integer.parseInt(request.getParameter("year"));
        int ordinalNum = Integer.parseInt(request.getParameter("ordinalNum"));
        String season = request.getParameter("season");
        String name = request.getParameter("name");

        // Tournament オブジェクトの作成
        Tournament tournament = new Tournament();
        tournament.setYear(year);
        tournament.setOrdinalNum(ordinalNum);
        tournament.setSeason(season);
        tournament.setName(name);

        // DAOを使用してデータベースに大会情報を追加
        TournamentDAO dao = new TournamentDAO();
        try {
            int result = dao.insertTournament(tournament);
            if (result > 0) {
                // 登録成功
                response.sendRedirect("/baseball/staff/tournamentRegistrationCompletion.jsp"); // 成功画面へリダイレクト
            } else {
                // 登録失敗
                response.sendRedirect("/baseball/staff/error.jsp"); // エラー画面へリダイレクト
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
