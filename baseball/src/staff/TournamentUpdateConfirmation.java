package staff;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import dao.TournamentDAO;

@WebServlet("/staff/TournamentUpdateConfirmation")
public class TournamentUpdateConfirmation extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	request.setCharacterEncoding("UTF-8");

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	    	String year = request.getParameter("year");
	        String ordinalNum = request.getParameter("ordinalNum");
	    	String season = request.getParameter("season");
	        String name = request.getParameter("name");
	        String tournamentId = request.getParameter("tournamentId");

	        TournamentDAO tournamentDAO = new TournamentDAO();
	        Date date =new Date();
	        Calendar calendar=Calendar.getInstance();
	        calendar.setTime(date);
	        if(Integer.parseInt(year) < calendar.get(Calendar.YEAR)){
		    	request.setAttribute("sameTour","2");
		    	request.getRequestDispatcher("/staff/tournamentUpdateInput.jsp").forward(request, response);
		    	return;
	        }

			try {
				int searchSameTour = tournamentDAO.getTournamentId(Integer.parseInt(year),season);
				if(searchSameTour==0){
	//		    	すでに同じ年と季節の大会情報がある
			    	request.setAttribute("sameTour","1");
			    	request.getRequestDispatcher("/staff/tournamentUpdateInput.jsp").forward(request, response);
			    	return;
				}else{
			        // 取得したパラメータをリクエスト属性にセット
			        request.setAttribute("year", year);
			        request.setAttribute("ordinalNum", ordinalNum);
			        request.setAttribute("season", season);
			        request.setAttribute("name", name);
			        request.setAttribute("tournamentId", tournamentId);
		    		request.getRequestDispatcher("/staff/tournamentUpdateConfirmation.jsp").forward(request, response);
	    		}
			}catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
}
