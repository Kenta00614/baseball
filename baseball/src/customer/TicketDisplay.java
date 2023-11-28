package customer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Spectator;
import bean.TicketsExp;
import dao.TicketsDAO;

@WebServlet("/customer/TicketDisplay")
public class TicketDisplay extends HttpServlet {

    @SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	List<Spectator> spectator = (List<Spectator>)session.getAttribute("spectatorIds");
    	List<TicketsExp> tickets = new ArrayList<>();

//    	観戦客IDがある
    	if(spectator != null){
//    		ログイン情報

        	TicketsDAO ticketsDAO = new TicketsDAO();

        	Date date = new Date();
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        	java.sql.Date today = java.sql.Date.valueOf(df.format(date));
        	try {
        		tickets = ticketsDAO.viewTickets(spectator.get(0).getSpectatorId(),today);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	request.setAttribute("tickets", tickets);
        request.getRequestDispatcher("/customer/ticketDisplay.jsp").forward(request, response);
    }
}
