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

import bean.Point;
import bean.Spectator;
import bean.TicketsAndSeat;
import dao.PointDAO;
import dao.PurchaseDAO;
import dao.TicketsDAO;

@WebServlet("/customer/TicketComplete")
public class TicketComplete extends HttpServlet {

    @SuppressWarnings({ "unchecked", "unused" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session=request.getSession();

//		チケット購入のID
		String[] selTickets = (String[]) session.getAttribute("selTickets");
//		大人か子供か
		String[] selChils = (String[])session.getAttribute("selChils");
//		ログイン情報
		List<Spectator> spectator = (List<Spectator>)session.getAttribute("spectatorIds");
//		使ったポイント
		String point = request.getParameter("usePointNum");
		int usePoint=0;
		if(!point.equals("0")){
			usePoint = Integer.parseInt("-"+point);
		}

		List<TicketsAndSeat> selTicketsData=new ArrayList<>();
		int  purchaseId;
		int updateNum=0;
		int pointNum = 0;

//		DAO
		TicketsDAO ticketDAO=new TicketsDAO();
		PurchaseDAO purchaseDAO=new PurchaseDAO();
		PointDAO pointDAO=new PointDAO();



		try {
//			購入情報登録
			purchaseId = purchaseDAO.insertPurchase(spectator.get(0).getSpectatorId());
//			チケットのステータス変更
//			(子供、大人のステータスも変更したい)
			for(String s: selTickets){
				updateNum += ticketDAO.purchaseTickets(s,purchaseId);
			}
//			ポイント情報登録
			if(updateNum == selTickets.length){
				Point pointData=new Point();
				pointData.setPurchaseId(purchaseId);
				pointData.setFluctuation(usePoint);
				pointData.setSpectatorId(spectator.get(0).getSpectatorId());
				pointNum = pointDAO.insertUsePoint(pointData);
			}
			if(pointNum > 0){
//				購入するチケットの情報取得
				selTicketsData = ticketDAO.getSelectTickets(selTickets);
			}

//			セッションの削除
			session.removeAttribute("selTickets");
			session.removeAttribute("selChils");
			session.removeAttribute("seat");
			session.removeAttribute("block");
			session.removeAttribute("count");
			session.removeAttribute("match");
		} catch (Exception e) {
			e.printStackTrace();
		}

//		チケットの情報
		request.setAttribute("selTicketsData", selTicketsData);
        request.getRequestDispatcher("/customer/ticketComplete.jsp").forward(request, response);
    }
}
