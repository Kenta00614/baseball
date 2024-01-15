package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import dao.StaffDAO;

@WebServlet("/staff/StaffPass")
public class StaffPass extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
    	HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{

	        StaffDAO staffDAO = new StaffDAO();

	        try {
	            int result = staffDAO.updateStaff(id, password);
	            if (result > 0) {
	                // パスワード更新成功
	                response.sendRedirect("/baseball/staff/staffPassComplete.jsp");
	            } else {
	                // パスワード更新失敗
	                response.sendRedirect("/baseball/staff/staffPassFailed.jsp");
	            }
	        } catch (Exception e) {
	            throw new ServletException(e);
	        }
    	}
    }
}
