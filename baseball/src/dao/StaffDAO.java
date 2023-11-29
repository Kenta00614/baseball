package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Staff;

public class StaffDAO extends DAO {

//IDとパスワード一致で職員情報を取得。生年月日とパスワードが同じならnull
	public Staff loginStaff(String id, String password)throws Exception {
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
			st.setString(4,password);
			st.setString(5,position);
			line=st.executeUpdate();
		}

		stSearch.close();
		st.close();
		con.close();

		return line;
	}

//	パスワードの変更。成功すると1を返す
	public int updateStaff(String id, String password) throws Exception{
		int line=0;
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"UPDATE STAFF SET PASSWORD=? WHERE STAFF_ID=?");

			st.setString(1,password);
			st.setString(2,id);
			line=st.executeUpdate();

			st.close();
			con.close();
		return line;
	}

	//職員情報を取得する
	public List<Staff> selectStaffAll()throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from staff");

		ResultSet rs=st.executeQuery();

		List<Staff> list=new ArrayList<>();

		while(rs.next()){
			Staff s=new Staff();
			s.setStaffId(rs.getString("staff_id"));
			s.setName(rs.getString("name"));
			s.setBirth(rs.getDate("birth"));
			s.setPosition(rs.getString("position"));

			list.add(s);
		}

		st.close();
		con.close();

		return list;
	}

	//職員情報の削除
	public int deleteStaff(String staffId)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("delete from staff where staff_id = ?");
		st.setString(1, staffId);

		int num = st.executeUpdate();

		st.close();
		con.close();

		return num;

	}
}
