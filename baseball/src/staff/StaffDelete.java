package staff;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import dao.StaffDAO;

@WebServlet("/staff/StaffDelete")
public class StaffDelete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	StaffDAO DAO = new StaffDAO();

    	HttpSession session = request.getSession();
    	Staff staff=(Staff)session.getAttribute("staff");
//    	ログインしているか
    	if(staff == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	    	String id=staff.getStaffId();

	    	try {
				List<Staff> list= DAO.selectStaffWithoutMe(id);

				request.setAttribute("list",list);
				request.getRequestDispatcher("/staff/staffDelete.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
}
