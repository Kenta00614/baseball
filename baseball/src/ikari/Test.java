package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Match;
import dao.MatchDAO;

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
			Match m=new Match();
			m.setTournamentId(2);
			m.setEventDate(Date.valueOf("2023-10-24"));
			m.setSaleStartAt(Date.valueOf("2023-10-25"));
			m.setDuel1(5);
			m.setDuel2(6);
			m.setDuel3(7);
			m.setDuel4(8);


			MatchDAO D = new MatchDAO();
		int num= D.insertMatch(m);

			out.println(num);

		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
