package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PurchaseExp;
import dao.PurchaseDAO;

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
			PurchaseDAO D = new PurchaseDAO();

			//List<PurchaseExp> list=D.getPurchaseHistory(1);

		/*	for(PurchaseExp t : list){
				out.println(t.getPurchaseId());
				out.println("   ");
				out.println(t.getSeatType());
				out.println("   ");
				out.println(t.getTournamentName());
				out.println("   ");
				out.println(t.getEventDate());
				out.println("   ");
				out.println(t.getPurchaseAt());
				out.println("   ");
				out.println(t.getSaleAt());

			}
			*/

		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
