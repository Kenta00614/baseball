package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;

@WebServlet("/staff/StaffRegister")
public class StaffRegister extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
    		request.getRequestDispatcher("/staff/staffRegister.jsp").forward(request, response);
    	}
    }
}
