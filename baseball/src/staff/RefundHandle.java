package staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import dao.TicketsDAO;

@WebServlet("/staff/RefundHandle")
public class RefundHandle extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();

//    	ログインしているか
    	Staff staffData = (Staff) session.getAttribute("staff");
    	if(staffData == null){
    		request.setAttribute("sessionOut","1");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    		return;
    	}else{
	    	String ticketId = request.getParameter("ticketnumber");

	    	TicketsDAO DAO=new TicketsDAO();

	    	try {
				String state = DAO.getStatus(ticketId);

				request.setAttribute("ticketId", ticketId);
				request.setAttribute("state", state);

				session.setAttribute("ticketId",ticketId);

		        request.getRequestDispatcher("/staff/refundHandle.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
}
