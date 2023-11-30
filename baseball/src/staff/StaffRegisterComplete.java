package staff;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Staff;
import dao.StaffDAO;

@WebServlet("/staff/StaffRegisterComplete")
public class StaffRegisterComplete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String id=request.getParameter("id");
    	String name=request.getParameter("name");
    	String birth=request.getParameter("BIRTH");
    	String position=request.getParameter("position");

    	String year = birth.substring(0,4);
    	String month = birth.substring(4,6);
    	String day = birth.substring(6,8);

    	birth = year + "-" + month + "-" + day;

    	Date Birth = Date.valueOf(birth);

    	StaffDAO DAO = new StaffDAO();

    	try {
			int RegNum = DAO.addNewStaff(id, name, Birth, position);

			if(RegNum == 1){
				List<Staff> list =  DAO.selectStaff(id);

				request.setAttribute("list", list);
				request.getRequestDispatcher("/staff/staffRegisterComplete.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/staff/staffRegister.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}
