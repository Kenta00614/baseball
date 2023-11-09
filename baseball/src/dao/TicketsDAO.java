package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Tickets;

public class TicketsDAO extends DAO{

	//チケット番号、試合番号、座席番号、チケットステータス、共有ステータスを取得
	public List<Tickets> getTicketsInfo(int purchase_id) throws Exception{

		List<Tickets> list=new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM TICKETS WHERE PURCHASE_ID = ? AND (STATUS = 1 OR STATUS = 4)");
		st.setInt(1, purchase_id);

		ResultSet rs=st.executeQuery();

		while(rs.next()){
			Tickets t=new Tickets();
			t.setTicketsId(rs.getString("tickets_id"));
			t.setMatchId(rs.getInt("match_id"));
			t.setSeatId(rs.getString("seat_id"));
			t.setStatus(rs.getString("status"));
			t.setShared(rs.getBoolean("is_shared"));
			t.setChild(rs.getBoolean("is_child"));

			list.add(t);
		}

		st.close();
		con.close();

		return list;
	}

	//販売停止の際のチケットステータスを販売停止に変更する処理
	public int changeStopSales(Date date)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("UPDATE tickets SET status = '3' WHERE match_id IN (SELECT match_id FROM match WHERE event_date = ?)");

		st.setDate(1, date);

		int num=st.executeUpdate();

		st.close();
		con.close();

		return num;



	}

}
