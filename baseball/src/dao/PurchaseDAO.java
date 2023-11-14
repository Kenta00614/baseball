package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.Purchase;

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
	public int insertPurchase(int spectatorId)throws Exception{

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Connection con=getConnection();
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
		con.close();

		return purchaseId;

	}

	//*購入履歴の取得
	/*public List<PurchaseExp> getPurchaseHistory(int spectatorId)throws Exception{

		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("select purchase_id from purchase where spectator_id = ?");
		st.setInt(1, spectatorId);

		ResultSet rs = st.executeQuery();

		List<Integer> purchaseIdList = new ArrayList<>();


		///PreparedStatement st=con.prepareStatement("select purchase.*,tickets.match_id,tickets.seat_id,tickets.status,tickets.is_child from purchase join tickets on purchase.purchase_id = tickets.purchase_id");
		 *
		 *
		 * sql   select purchase.* ,tickets.status,tickets.is_child,match.event_date,match.sale_start_at,seat.type,tournament.ordinal_num,tournament.name from purchase join tickets on purchase.purchase_id = tickets.purchase_id join match on tickets.match_id = match.match_id join seat on tickets.seat_id = seat.seat_id join tournament on match.tournament_id = tournament.tournament_id where purchase.spectator_id = 1 and tickets.status = 3;

	}
	*/

}
