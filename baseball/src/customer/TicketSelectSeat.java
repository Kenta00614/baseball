package customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Match;
import bean.Seat;
import bean.Tickets;
import bean.TicketsAndSeat;
import dao.SeatDAO;
import dao.TicketsDAO;

@WebServlet("/customer/TicketSelectSeat")
public class TicketSelectSeat extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		値取得
		HttpSession session=request.getSession();
    	int count = (int)session.getAttribute("count");
    	String seat = (String)session.getAttribute("seat");
    	Match match = (Match)session.getAttribute("match");
    	String block = request.getParameter("block");

		List<Tickets> blockRemain=new ArrayList<>();
		List<String> blockList=new ArrayList<>();

		TicketsDAO ticketDAO=new TicketsDAO();
		SeatDAO seatDAO=new SeatDAO();

		try {
//			ブロックの残チケット取得
			blockRemain=ticketDAO.getBlockSurplus(block, match.getMatchId());

//			残数が購入枚数より少ないとき
			if(blockRemain.size()<count){
//				ブロックの取得
				blockList = seatDAO.getBlock(seat);

//				前ページに戻るとき必要な情報
				request.setAttribute("blocks", blockList);
				request.setAttribute("remain", blockRemain.size());
				request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);
				return;
			}

			List<TicketsAndSeat> ticketAndSeat=new ArrayList<>();
//			チケット表示に必要な情報取得
			ticketAndSeat=ticketDAO.selectTickets(match.getEventDate(), block);
			List<Seat> seats=seatDAO.getSeat(block);




//			ブロックの販売中チケット情報
			request.setAttribute("tickets", ticketAndSeat);
//			選択されたブロック
			session.setAttribute("block", block);
//			ブロックの席情報
			session.setAttribute("seats", seats);
	        request.getRequestDispatcher("/customer/ticketSelectSeat.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
