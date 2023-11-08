package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Spectator;

public class StaffDAO extends DAO {

	public Spectator loginStaff(String mail, String password)
		throws Exception {
		Spectator spectator=null;

		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
			"SELECT * FROM SPECTATOR WHERE MAIL=? AND PASSWORD=?");
		st.setString(1, mail);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			spectator=new Spectator();
			spectator.setSpectatorId(rs.getInt("SPECTATOR_ID"));
			spectator.setName(rs.getString("NAME"));
			spectator.setPassword(rs.getString("PASSWORD"));
			spectator.setTel(rs.getString("TEL"));
			spectator.setPoint(rs.getInt("POINT"));
			spectator.setMail(rs.getString("MAIL"));
		}

		st.close();
		con.close();
		return spectator;
	}



	public int addNewStaff(String id, String name, Date birth, String position) throws Exception{
		int line=0;
		String search=null;
		Connection con=getConnection();

		PreparedStatement stSearch=con.prepareStatement(
				"SELECT * FROM STAFF WHERE STAFF_ID=?");
			stSearch.setString(1, id);
			ResultSet rs=stSearch.executeQuery();

			while (rs.next()) {
				search=rs.getString("STAFF_ID");
			}

		PreparedStatement st=con.prepareStatement(
				"INSERT INTO STAFF VALUES(?,?,?,?,?)");
		if(search==null){
			st.setString(1,id);
			st.setString(2,name);
			st.setDate(3,birth);

			String password = birth.toString();
	        int dateIndex = password.lastIndexOf('-');
	        String date = password.substring(dateIndex + 1);
	        String yyyymm = password.substring(0,dateIndex);
	        int monthIndex = yyyymm.lastIndexOf('-');
	        String month = yyyymm.substring(monthIndex + 1);
	        String year = yyyymm.substring(0,monthIndex);

			st.setString(4,year+month+date);
			st.setString(5,position);
			line=st.executeUpdate();
		}

		st.close();
		con.close();

		return line;
	}
}
