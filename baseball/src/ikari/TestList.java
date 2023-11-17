package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TicketsExp;
import dao.TicketsDAO;

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
			TicketsDAO D = new TicketsDAO();

			Date today= new Date();

			java.sql.Date date = new java.sql.Date(today.getTime());

			List<TicketsExp> list=D.viewTickets(0, date);

			for(TicketsExp t : list){
				out.println(t.getTicketsId());
				out.println("   ");
				out.println(t.getStatus());
				out.println("   ");
				out.println(t.getIsShared());
				out.println("   ");
				out.println(t.isChild());
				out.println("   ");
				out.println(t.getEventDate());
				out.println("   ");
				out.println(t.getSpectatorId());
				out.println("   ");
				out.println(t.getSeatId());
				out.println("   ");
				out.println(t.getType());
				out.println("   ");
				out.println(t.getStep());
				out.println("   ");
				out.println(t.getNumber());
				out.println("   ");
				out.println(t.getGate());
				out.println("   ");
				out.println(t.getPassage());
				out.println("   ");
				out.println(t.getBlock());
				out.println("   ");
				out.println(t.getOrdinalNum());
				out.println("   ");
				out.println(t.getTournamentName());
			}


		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
