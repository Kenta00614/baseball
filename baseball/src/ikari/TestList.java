package ikari;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DuelExp;
import dao.DuelDAO;

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
			DuelDAO D = new DuelDAO();

			List<DuelExp> list=D.getDuelDetail(1);

			for(DuelExp t : list){
				out.println(t.getSchoolB1().getName());
				out.println("   ");
				out.println(t.getSchoolB2().getName());
				out.println("   ");
				out.println(t.getStatus());
			}

		}catch(Exception e){
			e.printStackTrace(out);
		}

		out.println("</body>");
		out.println("</html>");

	}
}
