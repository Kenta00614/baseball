package staff;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StaffDAO;

@WebServlet("/staff/StaffRegisterComplete")
public class StaffRegisterComplete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String id=request.getParameter("id");
    	String name=request.getParameter("name");
    	String birth=request.getParameter("BIRTH");
    	String position=request.getParameter("position");

    	Date Birth = Date.valueOf(birth);

    	StaffDAO DAO = new StaffDAO();

    	try {
			int RegNum = DAO.addNewStaff(id, name, Birth, position);

			if(RegNum == 1){
				request.getRequestDispatcher("/staff/staffRegisterComplete.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher("/staff/staffRegister.jsp").forward(request, response);
		}
    }
}
