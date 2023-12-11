package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/staff/TournamentListChangeDisplay")
public class TournamentListChangeDisplay extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストから名前と電話番号を取得
        String name = request.getParameter("name");


        // リクエストスコープに名前と電話番号を設定
        request.setAttribute("name", name);


        // JSPへフォワード
        request.getRequestDispatcher("/staff/tournamentListChange.jsp").forward(request, response);
    }
}
