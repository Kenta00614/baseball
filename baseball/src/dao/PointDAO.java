package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	@SuppressWarnings("unused")
	public int insertSavePoint(Point point,Connection con)throws Exception{
		boolean flg = false;
		int num=0;

		PreparedStatement st = con.prepareStatement("SELECT point.fluctuation from point left join tickets on tickets.purchase_id = point.purchase_id where point.spectator_id=? and point.purchase_id=? and tickets.tickets_id=?");
		st.setInt(1, point.getSpectatorId());
		st.setInt(2, point.getPurchaseId());
		st.setString(3, point.getTicketsId());

		ResultSet rs=st.executeQuery();
		Point t=new Point();
		while(rs.next()){
			t.setFluctuation(rs.getInt("point.fluctuation"));
		}
		if(t.getFluctuation()==0 ){

			SpectatorDAO s=new SpectatorDAO();
			int check = s.updatePoint(point.getSpectatorId(),point.getFluctuation(),con);

			if(check < 0){
				throw new Exception();
			}

			if(con == null){
				con=getConnection();
				flg = true;
			}

			st=con.prepareStatement("insert into point values(null,?,?,null,?)");

			st.setInt(1,point.getSpectatorId());
			st.setInt(2, point.getFluctuation());
			st.setString(3, point.getTicketsId());

			num=st.executeUpdate();
			st.close();
			if(flg){
				con.close();
			}
		}
		return num;

	}
}
