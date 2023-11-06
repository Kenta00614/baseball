package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import bean.Match;

public class MatchDAO extends DAO{

	public void getMatchInfo(int match_id)throws Exception{

		List<Match> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH JOIN DUEL ON MATCH.DUEL1 = DUEL.DUEL_ID WHERE MATCH_ID = ?");
	}
}
