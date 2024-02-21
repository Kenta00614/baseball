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
import bean.PurchaseExp;
import bean.Spectator;
import bean.Tickets;
import common.Constants;
import dao.PurchaseDAO;
import dao.SeatDAO;
import dao.TicketsDAO;

@WebServlet("/customer/TicketSelectAll")
public class TicketSelectAll extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    @SuppressWarnings( "unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      値の取得
        HttpSession session=request.getSession();
        int count = Integer.parseInt(request.getParameter("count"));
        String seat = request.getParameter("seat");
        Match match=(Match)session.getAttribute("match");

        // セッションからspectatoridを取得
        List<Spectator> spectatorIds =  (List<Spectator>)session.getAttribute("spectatorIds");

        int remaining=0;
        List<Tickets> tickets=new ArrayList<>();
        List<String> blocks=new ArrayList<>();

        TicketsDAO ticketDAO=new TicketsDAO();
        SeatDAO seatDAO=new SeatDAO();
        try {
//          座種のチケット残数
            tickets = ticketDAO.getTypeSurplus(seat,match.getMatchId());
            remaining=tickets.size();

////            希望枚数よりチケットが少ない場合前の画面に戻る
//          if(remaining<count){
//              List<String> seatType = new ArrayList<>();
//
//              String[] seatOrder = {"0B","0F","0T","0R","0L"};
//
//              for (String key : seatOrder) {
//                  String value = Constants.SEAT_TYPE.get(key);
//                  seatType.add(value);
//              }
//              request.setAttribute("seatOrder", seatOrder);
//              request.setAttribute("seatType",seatType );
//              request.setAttribute("remaining", remaining);
//              request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
//              return;
//          }

//          ログインしていないときログイン画面へ
            if (spectatorIds == null) {
                session.setAttribute("seat", seat);
                session.setAttribute("count", count);
                session.setAttribute("remaining", remaining);
                response.sendRedirect("login.jsp");
                return;
            }

//          開催日の購入チケット取得
            List<PurchaseExp> purchaseList=new ArrayList<>();
            PurchaseDAO purchaseDAO=new PurchaseDAO();
            purchaseList = purchaseDAO.getDatePurchase(spectatorIds.get(spectatorIds.size()-1).getSpectatorId(),match.getEventDate());

            if(count+purchaseList.size()>6 || remaining<count){
                int[] tickets1 = null;
                int allCount=0;
                try {
//                  座種のチケット残数
                	tickets1 = ticketDAO.getAllSurplus(match.getMatchId());
                	for(int ticket: tickets1){
                		allCount += ticket;
                	}
                } catch (Exception e) {
                    e.printStackTrace();
                }
    	        request.setAttribute("tickets", tickets1);
    	        if(allCount == 0){
    	        	request.setAttribute("sold", allCount);
    	        }

                List<String> seatType = new ArrayList<>();

                String[] seatOrder = {"0B","0F","0T","0R","0L"};

                for (String key : seatOrder) {
                    String value = Constants.SEAT_TYPE.get(key);
                    seatType.add(value);
                }
                request.setAttribute("seatOrder", seatOrder);
                request.setAttribute("seatType",seatType );
                if(remaining<count){
                    request.setAttribute("remaining", remaining);
                }
                if(count+purchaseList.size()>6){
                    request.setAttribute("countTic", 6-purchaseList.size());
                }
                request.getRequestDispatcher("/customer/ticketApplication.jsp").forward(request, response);
                return;
            }

//          通常の時送る値
            blocks = seatDAO.getBlock(seat);
            request.setAttribute("blocks", blocks);
            request.setAttribute("remain", -1);
            session.setAttribute("seat", seat);
            session.setAttribute("count", count);
            request.getRequestDispatcher("/customer/ticketSelectAll.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}