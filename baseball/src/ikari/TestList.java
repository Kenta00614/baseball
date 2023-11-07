package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tournament;
import dao.TournamentDAO;

@WebServlet("/testList")
public class TestList extends HttpServlet{

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
			TournamentDAO D = new TournamentDAO();

			List<Tournament> list=D.getTournamentDetail();

			for(Tournament t : list){
				out.println(t.getTournamentId());
				out.println("   ");
				out.println(t.getYear());
				out.println("   ");
				out.println(t.getOrdinalNum());
				out.println("   ");
				out.println(t.getName());
			}

		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
