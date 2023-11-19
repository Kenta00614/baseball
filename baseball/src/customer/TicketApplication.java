package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Match;
import common.Constants;
import dao.MatchDAO;

@WebServlet("/customer/TicketApplication")
public class TicketApplication extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		//ログインじゃなかったときの処理はログイン実装後に記述



		Match match = (Match) session.getAttribute("match");
        if (match == null) {
            match = new Match();

//            値取得
    		int matchId = Integer.parseInt(request.getParameter("matchId"));

//    			開催日取得
    		 MatchDAO matchDAO=new MatchDAO();
    		 try {
    			match=matchDAO.getMatchInfo(matchId);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }

		 request.setAttribute("remaining", -1);
		 request.setAttribute("seatType",Constants.SEAT_TYPE );
		 session.setAttribute("match", match);
		 request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
    }
}