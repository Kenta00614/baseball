package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StaffDAO;

@WebServlet("/staff/StaffDeleteCheck")
public class StaffDeleteCheck extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String[] list = request.getParameterValues("selectedIDs");
    	StaffDAO DAO = new StaffDAO();

    	try{
	    	for(int i =0;i < list.length;i++){
	    		DAO.deleteStaff(list[i]);
	    	}
    	}catch(Exception e){

    	}

        request.getRequestDispatcher("/staff/staffDeleteCheck.jsp").forward(request, response);
    }
}
