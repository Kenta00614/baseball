package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Point;

public class PointDAO extends DAO{

	//使われたポイントを記帳する
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
//	たまったポイントを記帳する
	public int insertSavePoint(Point point,Connection con)throws Exception{
		boolean flg = false;

		SpectatorDAO s=new SpectatorDAO();
		int check = s.updatePoint(point.getSpectatorId(),point.getFluctuation(),con);

		if(check < 0){
			throw new Exception();
		}

		if(con == null){
			con=getConnection();
			flg = true;
		}

		PreparedStatement st=con.prepareStatement("insert into point values(null,?,?,null,?)");

		st.setInt(1,point.getSpectatorId());
		st.setInt(2, point.getFluctuation());
		st.setString(3, point.getTicketsId());

		int num=st.executeUpdate();
		st.close();
		if(flg){
			con.close();
		}
		return num;

	}
}
