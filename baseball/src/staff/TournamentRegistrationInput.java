package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import bean.Tournament;
import dao.TournamentDAO;

@WebServlet("/staff/TournamentRegistrationInput")

public class TournamentRegistrationInput extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	    	request.setCharacterEncoding("UTF-8");

	        // リクエストパラメータの取得
	        String yearStr = request.getParameter("year");
	        String ordinalNumStr = request.getParameter("ordinalNum");
	        String season = request.getParameter("season");
	        String name = request.getParameter("name");

	        // バリデーション: "year"と"ordinalNum"が数字かどうかを確認
	        if (!isValidNumber(yearStr) || !isValidNumber(ordinalNumStr)) {
	            // 無効な入力の場合、カスタムエラーページにリダイレクト
	            response.sendRedirect("/baseball/staff/tournamentRegistrationError.jsp");
	            return;
	        }

	        // 確認した値をパース
	        int year = Integer.parseInt(yearStr);
	        int ordinalNum = Integer.parseInt(ordinalNumStr);

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

    // 入力文字列が数値かどうかをバリデーション
    private boolean isValidNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}