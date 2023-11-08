package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import bean.Provisional;

public class ProvisionalDAO extends DAO {
//	仮会員登録、作成したuuidを返す
	public UUID insertProv(String mail, String name, String password, String tel) throws Exception{
		Connection con=getConnection();
		SpectatorDAO spectator=new SpectatorDAO();
		UUID newUuid=null;

		String search=spectator.searchSameMail(mail);
		if(!(search.equals(mail))){
			PreparedStatement st=con.prepareStatement(
					"INSERT INTO PROVISIONAL VALUES(?,?,?,?,?,NULL)");
			String uuid = UUID.randomUUID().toString();
			st.setString(1, uuid);
			st.setString(2,mail);
			st.setString(3,name);
			st.setString(4,password);
			st.setString(5,tel);
			st.executeUpdate();
			newUuid = UUID.fromString(uuid);
			st.close();
			con.close();
		}
		return newUuid;
	}

//	引数のuuidの仮会員情報を取得
	public Provisional searchUuid(UUID uuid)throws Exception {
		Provisional search=null;
		String id=uuid.toString();

		Connection con=getConnection();
		PreparedStatement st;
		st=con.prepareStatement(
			"SELECT * FROM PROVISIONAL WHERE UUID=?");
		st.setString(1, id);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			search=new Provisional();
			search.setMail(rs.getString("mail"));
			search.setName(rs.getString("name"));
			search.setPassword(rs.getString("password"));
			search.setTel(rs.getString("tel"));
		}

		st.close();
		con.close();
		return search;
	}

//	引数のuuidの仮会員情報を削除
	public int delUuid(UUID uuid) throws Exception{
		Connection con=getConnection();
		int line=0;

		PreparedStatement st=con.prepareStatement(
				"DELETE PROVISIONAL WHERE UUID=?");
		st.setString(1, uuid.toString());

		line=st.executeUpdate();
		st.close();
		con.close();

		return line;
	}
}