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

@WebServlet("/staff/StaffDeleteComplete")
public class StaffDeleteComplete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session=request.getSession();
    	String[] staffList=(String[])session.getAttribute("list");

    	Staff staff=(Staff)session.getAttribute("staff");
//    	ログインしているか
    	if(staff == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	    	StaffDAO DAO=new StaffDAO();

	    	for(String id : staffList){
	    		try {
					DAO.deleteStaff(id);
				} catch (Exception e) {
					//	既に消されている場合はそのまま処理を続ける
				}
	    	}
	    	session.removeAttribute("list");
	        request.getRequestDispatcher("/staff/staffDeleteComplete.jsp").forward(request, response);
	    }
    }
}
