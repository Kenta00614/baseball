package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProvisionalDAO;

@WebServlet("/ikari/testSpectator")
public class testSpectator extends HttpServlet{
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

			String mail="a@i.softbank.jp";
			String name="大原花子";
			String pass = "aaa";
			String tel = "08012345678";

			ProvisionalDAO dao=new ProvisionalDAO();
			UUID test=dao.insertProv(mail,name,pass,tel);

			System.out.println(test);





		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
