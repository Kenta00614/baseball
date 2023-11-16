package ikari;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StaffDAO;

@WebServlet("/test")
public class Test extends HttpServlet{

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws IOException{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out=response.getWriter();


		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Servlet/JSP Sample Programs</title>");
		out.println("</head>");
		out.println("<body>");

		try{

			StaffDAO D = new StaffDAO();
			String staffId="test";

			int num=D.deleteStaff(staffId);
			out.println(num);


		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
