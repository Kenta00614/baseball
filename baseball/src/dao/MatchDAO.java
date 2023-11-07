package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Match;

public class MatchDAO extends DAO{

	//試合日番号から大会ID、開催日を取得する。
	public  List<Match> getMatchInfo(int match_id)throws Exception{

		List<Match> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH WHERE MATCH_ID = ?");
		st.setInt(1,match_id);

		ResultSet rs=st.executeQuery();

		while(rs.next()){
			Match m=new Match();
			m.setTournamentId(rs.getInt("tournament_id"));
			m.setEventDate(rs.getDate("event_date"));

			list.add(m);
		}

		st.close();
		con.close();

		return list;
	}
}
