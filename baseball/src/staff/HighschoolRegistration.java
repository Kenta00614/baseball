package staff;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Staff;
import dao.SchoolDAO;

@WebServlet("/staff/HighschoolRegistration")
public class HighschoolRegistration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	        SchoolDAO dao = new SchoolDAO();
	        int tournamentId = Integer.parseInt(request.getParameter("tournamentId")); // 大会IDを取得

	        try {
	        	List<School> searchList = null;
	        	searchList = dao.searchSchool(tournamentId);
	        	if(searchList.size() != 52){

		            String[] schoolName = new String[52];
		            for (int i = 1; i <= 52; i++) {
		                schoolName[i - 1] = request.getParameter("schoolName" + i);
		            }
		            // 高校名をデータベースに登録
		           dao.insertSchool(schoolName, tournamentId);

				   List<School> schools = dao.searchSchool(tournamentId);
				   request.setAttribute("schools", schools);
	        	}else{
	        		request.setAttribute("schools", searchList);
	        	}
			   request.setAttribute("tournamentId", tournamentId);
                // 登録成功
			   request.getRequestDispatcher("/staff/highschoolDisplay.jsp").forward(request, response);;
	        } catch (Exception e) {
	        	response.sendRedirect("error.jsp");
	            throw new ServletException(e);
	        }
    	}
    }
}