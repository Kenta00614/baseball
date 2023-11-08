package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import bean.Provisional;
import bean.Spectator;

public class SpectatorDAO extends DAO {

	public Spectator loginSpec(String mail, String password)
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
		if(search!=null){
			st.setString(1,search.getName());
			st.setString(2,search.getPassword());
			st.setString(3,search.getTel());
			st.setString(4,search.getMail());
			line=st.executeUpdate();

			provisional.delUuid(uuid);
		}

		st.close();
		con.close();

		return line;
	}

}
