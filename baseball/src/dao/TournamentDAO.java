package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TournamentDAO extends DAO{

	public String getTournamentName(int tournament_id)throws Exception{

		String tournamentName;
		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("SELECT NAME FROM TOURNAMENT WHERE TOURNAMENT_ID = ?");
		st.setInt(1,tournament_id);

		ResultSet rs=st.executeQuery();

		tournamentName=rs.getString("name");

		st.close();
		con.close();

		return tournamentName;
	}
}
