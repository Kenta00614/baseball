package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Staff;

public class StaffDAO extends DAO {
//IDとパスワード一致で職員情報を取得
	public Staff loginStaff(String id, String password)
		throws Exception {
		Staff staff=null;
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM STAFF WHERE STAFF_ID=? AND PASSWORD=?");
		st.setString(1, id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			staff=new Staff();
			staff.setStaffId(rs.getString("STAFF_ID"));
			staff.setName(rs.getString("NAME"));
			staff.setBirth(rs.getDate("BIRTH"));
			staff.setPassword(rs.getString("PASSWORD"));
			staff.setPosition(rs.getString("POSITION"));
		}

		st.close();
		con.close();
		return staff;
	}


//	職員ID・名前・生年月日・役職の情報から新規職員登録
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
