package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class ProvisionalDao extends DAO {
//新規会員登録
//観戦客情報に同じ情報がないか確認、仮会員情報にUUIDを登録登録されたら戻り値1
	public int insertProvisio(String mail, String name, String password, String tel) throws Exception{
		Connection con=getConnection();
		int line=0;
		String search="";

		PreparedStatement stdup=con.prepareStatement(
				"SELECT * FROM SPECTATOR WHERE MAIL=?");
			stdup.setString(1, mail);
			ResultSet rsdup=stdup.executeQuery();
			while (rsdup.next()) {
				search=rsdup.getString("mail");
			}

		if(!(search.equals(mail))){
			PreparedStatement st=con.prepareStatement(
					"INSERT INTO PROVISIONAL VALUES(?,?,?,?,?,NULL)");
			String uuid = UUID.randomUUID().toString();
			st.setString(1, uuid);
			st.setString(2,mail);
			st.setString(3,name);
			st.setString(4,password);
			st.setString(5,tel);
			line=st.executeUpdate();
			st.close();
			con.close();
		}
		return line;
	}
}
