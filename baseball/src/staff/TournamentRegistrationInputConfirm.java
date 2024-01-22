package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;

@WebServlet("/staff/TournamentRegistrationInputConfirm")
public class TournamentRegistrationInputConfirm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	request.setCharacterEncoding("UTF-8");

    	String year = request.getParameter("year");
        String ordinalNum = request.getParameter("ordinalNum");
    	String season = request.getParameter("season");
        String name = request.getParameter("name");

        // 取得したパラメータをリクエスト属性にセット
        request.setAttribute("year", year);
        request.setAttribute("ordinalNum", ordinalNum);
        request.setAttribute("season", season);
        request.setAttribute("name", name);


//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
    		request.getRequestDispatcher("/staff/tournamentRegistrationInputConfirm.jsp").forward(request, response);
    	}
    }
}
