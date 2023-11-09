package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

}
