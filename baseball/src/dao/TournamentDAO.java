package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Tournament;

public class TournamentDAO extends DAO{

	//大会IDから大会名を取得
	public String getTournamentName(int tournament_id)throws Exception{

		String tournamentName = null;
		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("SELECT NAME FROM TOURNAMENT WHERE TOURNAMENT_ID = ?");
		st.setInt(1,tournament_id);

		ResultSet rs=st.executeQuery();

		if (rs.next()) {
	        tournamentName = rs.getString("name");
	    } else {
	      tournamentName=null;
	    }
		st.close();
		con.close();

		return tournamentName;
	}

	//大会IDから開催年を取得
	public int getTournamentYear(int tournament_id)throws Exception{

		int tournamentYear;
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT YEAR FROM TOURNAMENT WHERE TOURNAMENT_ID = ?");
		st.setInt(1, tournament_id);

		ResultSet rs=st.executeQuery();

		if (rs.next()) {
	        tournamentYear = rs.getInt("year");
	    } else {
	      tournamentYear=0;
	    }

		return tournamentYear;
	}

	//大会IDから開催年、第何回、大会名、開催季節を取得
	public List<Tournament> getTournamentInfo(int tournament_id)throws Exception{

		List<Tournament> list=new ArrayList<>();

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM TOURNAMENT WHERE TOURNAMENT_ID = ?");
		st.setInt(1, tournament_id);

		ResultSet rs=st.executeQuery();

		while(rs.next()){
			Tournament t=new Tournament();
			t.setYear(rs.getInt("year"));
			t.setOrdinalNum(rs.getInt("ordinal_num"));
			t.setName(rs.getString("name"));
			t.setSeason(rs.getString("season"));

			list.add(t);
		}

		st.close();
		con.close();

		return list;
	}

	public List<Tournament> getTournamentDetail()throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM TOURNAMENT");
		ResultSet rs=st.executeQuery();

		List<Tournament> list=new ArrayList<>();

		while(rs.next()){
			Tournament t=new Tournament();
			t.setTournamentId(rs.getInt("tournament_id"));
			t.setYear(rs.getInt("year"));
			t.setOrdinalNum(rs.getInt("ordinal_num"));
			t.setName(rs.getString("name"));

			list.add(t);
		}

		st.close();
		con.close();

		return list;

	}
}
