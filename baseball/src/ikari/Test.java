package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Provisional;
import dao.ProvisionalDAO;

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

			Provisional p=new Provisional();
			ProvisionalDAO MD=new ProvisionalDAO();

			String uuid = "22ca95f0-d0ba-4e33-bd1d-46913e903c5c";
			UUID newUuid=UUID.fromString(uuid);

			p=MD.getIdAndNewMail(newUuid);

			int Id=p.getSpectatorId();
			String mail=p.getMail();

			System.out.println(Id);
			System.out.println(mail);

		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
