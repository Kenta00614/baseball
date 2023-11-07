package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

		PrintWriter out=response.getWriter();


		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Servlet/JSP Sample Programs</title>");
		out.println("</head>");
		out.println("<body>");

		try{
			MatchDAO D = new MatchDAO();
			List<Match> list=D.getMatchInfo(1);

			for(Match d : list){
				out.println(d.getTournamentId());
				out.println(d.getEventDate());
			}
		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
