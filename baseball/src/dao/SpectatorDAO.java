package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import bean.Provisional;
import bean.Spectator;

public class SpectatorDAO extends DAO {

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
			spectator.setPoint(rs.getInt("point"));
			spectator.setMail(rs.getString("mail"));
		}

		st.close();
		con.close();
		return spectator;
	}

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

	public int addNewSpec(UUID uuid) throws Exception{
		Connection con=getConnection();
		int line=0;

		ProvisionalDAO provisional=new ProvisionalDAO();
		Provisional search=provisional.searchUuid(uuid);

		PreparedStatement st=con.prepareStatement(
				"INSERT INTO SPECTATOR VALUES(NULL,?,?,?,0,?)");
		st.setString(1,search.getName());
		st.setString(2,search.getPassword());
		st.setString(3,search.getTel());
		st.setString(4,search.getMail());
		line=st.executeUpdate();

		provisional.delUuid(uuid);
		st.close();
		con.close();

		return line;
	}

}
