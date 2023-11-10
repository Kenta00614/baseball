package ikari;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TournamentDAO;

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

		/*	Match mm=new Match();
			mm.setMatchId(2);
			mm.setTournamentId(1);
			mm.setEventDate(Date.valueOf("2023-10-24"));
			mm.setSaleStartAt(Date.valueOf("2023-10-25"));
			mm.setDuel1(8);
			mm.setDuel2(7);
			mm.setDuel3(6);
			mm.setDuel4(5);
		*/

			TournamentDAO MD=new TournamentDAO();



			int num=MD.getTournamentId(2030, "夏");

			out.println(num);

		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
