package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Spectator;
import bean.TicketsAndSeat;
import dao.DAO;
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

		List<TicketsAndSeat> selTicketsData=new ArrayList<>();
		int  purchaseId;
		int updateNum=0;
		int pointNum = 0;
		boolean chilFlg = false;

//		DAO
		TicketsDAO ticketDAO=new TicketsDAO();

		try {
			DAO d = new DAO();
			Connection con=d.getConnection();

//			購入情報登録
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			PreparedStatement st=con.prepareStatement("INSERT INTO PURCHASE VALUES(NULL,?,?)");

			st.setInt(1,spectator.get(0).getSpectatorId());
			st.setTimestamp(2, timestamp);

			st.executeUpdate();

			st=con.prepareStatement("SELECT PURCHASE_ID FROM PURCHASE WHERE PURCHASE_AT = ?");

			st.setTimestamp(1, timestamp);
			ResultSet rs=st.executeQuery();

			if(rs.next()){
				purchaseId=rs.getInt("purchase_id");
			}else{
				purchaseId=0;
			}

			if(purchaseId != 0){
	//			チケットのステータス変更
				for(int i=0; i<selTickets.length ; i++){
//					子供の時はステータスを変える
					if(selChils[i].equals("子供")){
						chilFlg=true;
					}
					st=con.prepareStatement("UPDATE TICKETS SET PURCHASE_ID = ?,STATUS = 1,IS_CHILD = ? WHERE TICKETS_ID = ?");
					st.setInt(1, purchaseId);
					st.setBoolean(2, chilFlg);
					st.setString(3, selTickets[i]);

					updateNum +=st.executeUpdate();
					chilFlg=false;
				}
	//			ポイント情報登録
//				観戦客のポイント変更
				if(updateNum == selTickets.length){
					st=con.prepareStatement("SELECT POINT FROM SPECTATOR WHERE SPECTATOR_ID = ?");
					st.setInt(1, spectator.get(0).getSpectatorId());

					rs=st.executeQuery();

					int pointPrev = 0;

					if(rs.next()){
						pointPrev = rs.getInt("point");
					}

					int pointNew = 0;

					if(usePoint<0){
						if(pointPrev+usePoint>= 0){
							pointNew = pointPrev + usePoint;
						}
					}else{
						pointNew = pointPrev + usePoint;
					}

					PreparedStatement stUpdate = con.prepareStatement("update spectator set point = ?");
					stUpdate.setInt(1, pointNew);

					int num = stUpdate.executeUpdate();

//					ポイントテーブルに情報追加
					if(num>0){
						st=con.prepareStatement("insert into point values(null,?,?,?,null)");

						st.setInt(1,spectator.get(0).getSpectatorId());
						st.setInt(2, usePoint);
						st.setInt(3, purchaseId);

						pointNum=st.executeUpdate();
					}
				}
			}
			if(pointNum > 0){
				con.commit();
			}else{
				con.rollback();
			}
			con.setAutoCommit(true);
			st.close();
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
			e.printStackTrace();
		}

//		チケットの情報
		request.setAttribute("selTicketsData", selTicketsData);
        request.getRequestDispatcher("/customer/ticketComplete.jsp").forward(request, response);
    }
}