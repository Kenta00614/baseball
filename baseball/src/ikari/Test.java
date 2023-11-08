package ikari;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Duel;
import dao.DuelDAO;

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
			/*
			 Match mm=new Match();
			mm.setTournamentId(2);
			mm.setEventDate(Date.valueOf("2023-10-25"));
			mm.setSaleStartAt(Date.valueOf("2023-10-26"));
			mm.setDuel1(9);
			mm.setDuel2(10);
			mm.setDuel3(11);
			mm.setDuel4(12);
			*/

			Duel m=new Duel();
			m.setDuelId(1);
			m.setSchool1(1);
			m.setSchool2(2);
			m.setStatus("2");
			m.setRound("2");


			DuelDAO D = new DuelDAO();
		int num= D.changeDuel(m);

			out.println(num);

		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
