package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProvisionalDAO;
import dao.SpectatorDAO;

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

			ProvisionalDAO MD=new ProvisionalDAO();
			SpectatorDAO SD=new SpectatorDAO();


			UUID uuid=MD.insertIdAndUuid("bbbbb@i.softbank.jp");

			int num=SD.updatePassword(uuid, "testsimasita");

		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
