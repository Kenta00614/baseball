package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Spectator;

public class SpectatorDAO extends DAO {

//mailとpasswordで観戦客情報全てを取得するsearchSpecメソッド
	public Spectator searchSpec(String mail, String password)
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
			spectator.setSpectatorId(rs.getInt("spectatorId"));
			spectator.setName(rs.getString("name"));
			spectator.setPassword(rs.getString("password"));
			spectator.setTel(rs.getString("tel"));
			spectator.setPoint(rs.getString("point"));
			spectator.setMail(rs.getString("mail"));
		}

		st.close();
		con.close();
		return spectator;
	}

//	引数のmailと同じものがあるか参照戻り値は空白かmailの名前
	public String searchSameMail(String mail)throws Exception {
			String search="";
			Connection con=getConnection();

			PreparedStatement stdup=con.prepareStatement(
					"SELECT * FROM SPECTATOR WHERE MAIL=?");
				stdup.setString(1, mail);
				ResultSet rsdup=stdup.executeQuery();
				while (rsdup.next()) {
					search=rsdup.getString("mail");
				}

			stdup.close();
			con.close();
			return search;
		}

}
