package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		Match match = (Match) session.getAttribute("match");
		List<String> seatType = new ArrayList<>();

		String[] seatOrder = {"0B","0F","0T","0R","0L"};

        for (String key : seatOrder) {
            String value = Constants.SEAT_TYPE.get(key);
            seatType.add(value);
        }

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
			session.setAttribute("match", match);
        }
        request.setAttribute("remaining", -1);
        request.setAttribute("seatType",seatType );
        request.setAttribute("seatOrder", seatOrder);
		request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
    }
}