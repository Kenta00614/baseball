package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/staff/StaffDeleteCheck")
public class StaffDeleteCheck extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String list = request.getParameter("ID");
    	System.out.println(list);

        request.getRequestDispatcher("/staff/staffDeleteCheck.jsp").forward(request, response);
    }
}
