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

	//開催年、第何回、大会名、開催季節を取得
	public Tournament getTournamentInfo(int tournament_id)throws Exception{

		Tournament t=null;

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM TOURNAMENT WHERE TOURNAMENT_ID = ?");
		st.setInt(1, tournament_id);

		ResultSet rs=st.executeQuery();

		while(rs.next()){
			t=new Tournament();
			t.setYear(rs.getInt("year"));
			t.setOrdinalNum(rs.getInt("ordinal_num"));
			t.setName(rs.getString("name"));
			t.setSeason(rs.getString("season"));
		}

		st.close();
		con.close();

		return t;
	}

	//大会ID、開催年、第何回、大会名を取得する
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

	//大会情報を登録する（大会IDはセットしない）
	public int insertTournament(Tournament tournament)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("INSERT INTO TOURNAMENT VALUES(NULL,?,?,?,?)");
		st.setInt(1, tournament.getYear());
		st.setInt(2, tournament.getOrdinalNum());
		st.setString(3, tournament.getName());
		st.setString(4, tournament.getSeason());

		int num=st.executeUpdate();

		st.close();
		con.close();

		return num;

	}

	//大会情報を変更する
	public int changeTournament(Tournament tournament)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("UPDATE TOURNAMENT SET YEAR = ?,ORDINAL_NUM = ?,NAME = ?,SEASON = ? WHERE TOURNAMENT_ID = ?");

		try{
		st.setInt(1, tournament.getYear());
		st.setInt(2, tournament.getOrdinalNum());
		st.setString(3, tournament.getName());
		st.setString(4, tournament.getSeason());
		st.setInt(5, tournament.getTournamentId());

		int num =st.executeUpdate();

		return num;

		}catch(Exception e){
			int num = 0;
			return num;
		}

	}

	//開催年と開催季節で大会IDを取得する
	public int getTournamentId(int year,String season)throws Exception{

		try{

			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement("SELECT TOURNAMENT_ID FROM TOURNAMENT WHERE YEAR = ? AND SEASON = ?");
			st.setInt(1, year);
			st.setString(2, season);

			ResultSet rs=st.executeQuery();

			int tournamentId;

			if(rs.next()){
				tournamentId=rs.getInt("tournament_id");
			}else{
				tournamentId=0;
			}

			return tournamentId;

		}catch(Exception e){
			int num = 0;
			return num;
		}

	}
}
