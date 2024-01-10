package staff;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DuelExp;
import bean.Match;
import bean.Staff;
import dao.DuelDAO;
import dao.MatchDAO;

@WebServlet("/staff/MatchInformation")
public class MatchInformation extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
			int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
			String eventDateStr = request.getParameter("eventDate");
			Date nowDate = null;

			List<Match> matchList = new ArrayList();
			List<List<DuelExp>> duelList = new ArrayList<List<DuelExp>>();
			MatchDAO matchDAO = new MatchDAO();
			DuelDAO duelDAO = new DuelDAO();

			try {
				if(eventDateStr != null){
					Date eventDate = Date.valueOf(eventDateStr);
					matchDAO.deleteMatch(eventDate);
				}

				matchList = matchDAO.searchMatchTournament(tournamentId);

				for(Match m: matchList){
					List<DuelExp> array = new ArrayList<>();
					array.add(duelDAO.getDuelDetail(m.getDuel1()));
					array.add(duelDAO.getDuelDetail(m.getDuel2()));
					array.add(duelDAO.getDuelDetail(m.getDuel3()));
					array.add(duelDAO.getDuelDetail(m.getDuel4()));
					duelList.add(array);
				}
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        nowDate = Date.valueOf(sdf.format(timestamp));
			} catch (Exception e) {
				e.printStackTrace();
			}

			session.setAttribute("dispSelectTour",tournamentId );
			request.setAttribute("duelList",duelList);
			request.setAttribute("matchList",matchList);
			request.setAttribute("nowDate", nowDate);
	        request.getRequestDispatcher("/staff/matchInformation.jsp").forward(request, response);
	    }
	}
}