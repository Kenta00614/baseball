package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Match;

public class MatchDAO extends DAO{

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

	//match_idから開催日を取得
	public Date getEventDate(int match_id)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT EVENT_DATE FROM MATCH WHERE MATCH_ID = ?");
		st.setInt(1, match_id);

		ResultSet rs=st.executeQuery();

		Date eventDate;

		if(rs.next()){
			eventDate = rs.getDate("event_date");
		}else{
			eventDate=null;
		}

		return eventDate;
	}

}
