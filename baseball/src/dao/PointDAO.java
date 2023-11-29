package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Point;

public class PointDAO extends DAO{

	//たまったポイント、使われたポイントを記帳する
	public int insertUsePoint(Point point, Connection con)throws Exception{

		SpectatorDAO s=new SpectatorDAO();
		int check = s.updatePoint(point.getSpectatorId(),point.getFluctuation(),con);

		if(check < 0){
			return -1;
		}

		PreparedStatement st=con.prepareStatement("insert into point values(null,?,?,?,null)");

		st.setInt(1,point.getSpectatorId());
		st.setInt(2, point.getFluctuation());
		st.setInt(3, point.getPurchaseId());

		int num=st.executeUpdate();
		st.close();

		return num;

	}

	public int insertSavePoint(Point point)throws Exception{

		SpectatorDAO s=new SpectatorDAO();
		int check = s.updatePoint(point.getSpectatorId(),point.getFluctuation(),null);

		if(check < 0){
			return -1;
		}

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("insert into point values(null,?,?,null,?)");

		st.setInt(1,point.getSpectatorId());
		st.setInt(2, point.getFluctuation());
		st.setString(3, point.getTicketsId());

		int num=st.executeUpdate();
		st.close();
		con.close();

		return num;

	}
}
