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

@WebServlet("/staff/StaffDeleteCheck")
public class StaffDeleteCheck extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String[] list = request.getParameterValues("selectedIDs");

    	StaffDAO DAO=new StaffDAO();

    	HttpSession session=request.getSession();
//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	    	try {
				List<Staff> staffList = DAO.selectStaffs(list);
				request.setAttribute("staffList", staffList);
				session.setAttribute("list",list);
			    request.getRequestDispatcher("/staff/staffDeleteCheck.jsp").forward(request, response);

			} catch (Exception e) {
				String id=staffData.getStaffId();

		    	try {
					List<Staff> staffList= DAO.selectStaffWithoutMe(id);

					request.setAttribute("noSelectFlg","0");
					request.setAttribute("list",staffList);
					request.getRequestDispatcher("/staff/staffDelete.jsp").forward(request, response);
				} catch (Exception f) {
					e.printStackTrace();
				}
			}
    	}
    }
}
