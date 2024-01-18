package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	            String[] schoolName = new String[49];
	            for (int i = 1; i <= 49; i++) {
	                schoolName[i - 1] = request.getParameter("schoolName" + i);
	            }
	            // 高校名をデータベースに登録
	           dao.insertSchool(schoolName, tournamentId);

                // 登録成功
                response.sendRedirect("tournamentRegistrationCompletion.jsp");
	        } catch (Exception e) {
	        	response.sendRedirect("error.jsp");
	            throw new ServletException(e);
	        }
    	}
    }
}