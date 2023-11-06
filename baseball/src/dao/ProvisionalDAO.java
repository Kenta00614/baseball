package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class ProvisionalDAO extends DAO {
	public int insertProvisio(String mail, String name, String password, String tel) throws Exception{
		Connection con=getConnection();
		SpectatorDAO spectator=new SpectatorDAO();
		int line=0;
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
			line=st.executeUpdate();
			st.close();
			con.close();
		}
		return line;
	}
}
