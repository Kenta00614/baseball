package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.Purchase;
import bean.PurchaseExp;

public class PurchaseDAO extends DAO{

	//購入番号、購入日時を取得
	public List<Purchase> getPurchaseInfo(int spectator_id)throws Exception{

		List<Purchase> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM PURCHASE WHERE SPECTATOR_ID = ?");
		st.setInt(1, spectator_id);

		ResultSet rs=st.executeQuery();


		while(rs.next()){
			Purchase p=new Purchase();
			p.setPurchaseId(rs.getInt("purchase_id"));
			p.setPurchaseAt(rs.getTimestamp("purchase_at"));
			list.add(p);
	}

		st.close();
		con.close();

		return list;
	}


	//購入日時を取得、観戦客IDと購入日時を登録する
	public int insertPurchase(int spectatorId,Connection con)throws Exception{

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		PreparedStatement st=con.prepareStatement("INSERT INTO PURCHASE VALUES(NULL,?,?)");

		st.setInt(1,spectatorId);
		st.setTimestamp(2, timestamp);

		st.executeUpdate();

		PreparedStatement stPurchaseId=con.prepareStatement("SELECT PURCHASE_ID FROM PURCHASE WHERE PURCHASE_AT = ?");

		stPurchaseId.setTimestamp(1, timestamp);
		ResultSet rs=stPurchaseId.executeQuery();

		int purchaseId;

		if(rs.next()){
			purchaseId=rs.getInt("purchase_id");
		}else{
			purchaseId=0;
		}

		st.close();

		return purchaseId;

	}

	//購入履歴の取得
	public List<PurchaseExp> getPurchaseHistory(int spectatorId)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(" select purchase.* ,tickets.status,tickets.is_child,match.event_date,seat.type,tournament.ordinal_num,tournament.name from purchase join tickets on purchase.purchase_id = tickets.purchase_id join match on tickets.match_id = match.match_id join seat on tickets.seat_id = seat.seat_id join tournament on match.tournament_id = tournament.tournament_id where purchase.spectator_id = ? and tickets.status != 2 order by event_date,purchase_at");
		st.setInt(1, spectatorId);

		ResultSet rs=st.executeQuery();

		List<PurchaseExp> list=new ArrayList<>();

		while(rs.next()){
			PurchaseExp p=new PurchaseExp();
			p.setPurchaseId(rs.getInt("purchase_id"));
			p.setOrdinalNum(rs.getInt("ordinal_num"));
			p.setTournamentName(rs.getString("name"));
			p.setEventDate(rs.getDate("event_date"));
			p.setPurchaseAt(rs.getTimestamp("purchase_at"));
			p.setSeatType(rs.getString("type"));
			p.setChild(rs.getBoolean("is_child"));
			p.setDateStr();
			p.setPurchaseStr();
			list.add(p);
		}

		st.close();
		con.close();

		return list;
	}


	}



