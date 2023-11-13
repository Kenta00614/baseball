package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Point;

public class PointDAO extends DAO{

	//たまったポイント、使われたポイントを記帳する
	public int insertPoint(Point point)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("insert into point values(null,?,?,?,?)");

		st.setInt(1,point.getSpectatorId());
		st.setInt(2, point.getFluctuation());
		st.setInt(3, point.getPurchaseId());
		st.setString(4, point.getTicketsId());

		int num=st.executeUpdate();
		st.close();
		con.close();

		return num;

	}
}
