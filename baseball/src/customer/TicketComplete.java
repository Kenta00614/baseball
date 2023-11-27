package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import dao.DAO;
import dao.PointDAO;
import dao.PurchaseDAO;
import dao.TicketsDAO;

@WebServlet("/customer/TicketComplete")
public class TicketComplete extends HttpServlet {

    @SuppressWarnings("unchecked")
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

//		jspに渡す情報
		List<TicketsAndSeat> selTicketsData=new ArrayList<>();
		int  purchaseId;
		int updateNum=0;
		int pointNum = 0;
		boolean chilFlg = false;

//		DAO
		TicketsDAO ticketDAO=new TicketsDAO();
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		PointDAO pointDAO = new PointDAO();

		Connection con = null;
		try {
//			トランザクション
			DAO d = new DAO();
			con=d.getConnection();
			con.setAutoCommit(false);

//			購入情報登録
			purchaseId = purchaseDAO.insertPurchase(spectator.get(0).getSpectatorId(),con);

			if(purchaseId != 0){
	//			チケットのステータス変更
				for(int i=0; i<selTickets.length ; i++){
//					子供の時はステータスを変える
					if(selChils[i].equals("子供")){
						chilFlg=true;
					}
					updateNum += ticketDAO.purchaseTickets(selTickets[i],purchaseId,chilFlg,con);
					chilFlg=false;
				}
//				ポイント情報登録
				if(updateNum == selTickets.length){
					Point p = new Point();
					p.setSpectatorId(spectator.get(0).getSpectatorId());
					p.setFluctuation(usePoint);
					p.setPurchaseId(purchaseId);
					pointNum = pointDAO.insertUsePoint(p, con);
				}
			}
//			ポイント情報まで正常に動くならコミット
			if(pointNum > 0){
				con.commit();
			}else{
				con.rollback();
			}
//			トランザクション解除
			con.setAutoCommit(true);
			con.close();

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
			if(con != null){
				try {
					con.rollback();
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}

//		チケットの情報
		request.setAttribute("selTicketsData", selTicketsData);
        request.getRequestDispatcher("/customer/ticketComplete.jsp").forward(request, response);
    }
}