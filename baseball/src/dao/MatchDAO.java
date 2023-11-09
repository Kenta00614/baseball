package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Duel;
import bean.Match;

public class MatchDAO extends DAO{

	//大会番号、開催日を取得
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

	//開催日から試合情報を取得
	public List<Match> searchMatch(Date eventDate)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH WHERE EVENT_DATE = ?");
		st.setDate(1, eventDate);

		ResultSet rs=st.executeQuery();

		List<Match> list=new ArrayList<>();

		while(rs.next()){
			Match m = new Match();
			m.setMatchId(rs.getInt("match_id"));
			m.setTournamentId(rs.getInt("tournament_id"));
			m.setSaleStartAt(rs.getDate("event_date"));
			m.setDuel1(rs.getInt("duel1"));
			m.setDuel2(rs.getInt("duel2"));
			m.setDuel3(rs.getInt("duel3"));
			m.setDuel4(rs.getInt("duel4"));

			list.add(m);

		}

		st.close();
		con.close();

		return list;

	}

	//試合情報の追加
	public int insertMatch(Match match, Duel duel)throws Exception{

		try{
			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement("insert into MATCH values(null,?,?,?,?,?,?,?)");
			st.setInt(1,match.getTournamentId());
			st.setDate(2, match.getEventDate());
			st.setDate(3, match.getSaleStartAt());
			st.setInt(4, match.getDuel1());
			st.setInt(5, match.getDuel2());
			st.setInt(6, match.getDuel3());
			st.setInt(7, match.getDuel4());

			DuelDAO DD=new DuelDAO();
			st.executeUpdate();

			int num=DD.insertDuel(duel);

			st.close();
			con.close();

			return num;

		}catch(Exception e){

			// 0の時は同じ日付などが理由で登録できていない場合

			int num=0;
			return num;
		}

	}

	//試合日情報を変更する
	public int changeMatch(Match match, Duel duel)throws Exception{

		try{
			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement("UPDATE MATCH SET DUEL1 = ?,DUEL2 = ?, DUEL3 = ?, DUEL4 = ? WHERE MATCH_ID = ?");

			st.setInt(1, match.getDuel1());
			st.setInt(2, match.getDuel2());
			st.setInt(3, match.getDuel3());
			st.setInt(4, match.getDuel4());
			st.setInt(5, match.getMatchId());

			st.executeUpdate();

			DuelDAO DD = new DuelDAO();

			int num=DD.changeDuel(duel);

			st.close();
			con.close();

			return num;

		}catch(Exception e){

			int num=0;
			return num;

		}

	}

	//すべての試合日情報を取得
	public List<Match> getMatchAll()throws Exception{

		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH");
		ResultSet rs=st.executeQuery();

		List<Match> list=new ArrayList<>();

		while(rs.next()){
			Match m=new Match();
			m.setMatchId(rs.getInt("match_id"));
			m.setTournamentId(rs.getInt("tournament_id"));
			m.setEventDate(rs.getDate("event_date"));
			m.setSaleStartAt(rs.getDate("sale_start_at"));
			m.setDuel1(rs.getInt("duel1"));
			m.setDuel2(rs.getInt("duel2"));
			m.setDuel3(rs.getInt("duel3"));
			m.setDuel4(rs.getInt("duel4"));

			list.add(m);
		}

		st.close();
		con.close();

		return list;
	}


	//試合日情報と試合情報の削除
	public Match deleteMatch(Date event_date)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT DUEL1,DUEL2,DUEL3,DUEL4 FROM MATCH WHERE EVENT_DATE = ?");
		st.setDate(1, event_date);

		ResultSet rs=st.executeQuery();

		PreparedStatement stDel=con.prepareStatement("DELETE FROM MATCH WHERE EVENT_DATE = ?");
		stDel.setDate(1, event_date);

		stDel.executeUpdate();

		Match match=new Match();

		if(rs.next()){
			match.setDuel1(rs.getInt("duel1"));
			match.setDuel2(rs.getInt("duel2"));
			match.setDuel3(rs.getInt("duel3"));
			match.setDuel4(rs.getInt("duel4"));
		}

		Integer Duel1 = match.getDuel1();
		Integer Duel2 = match.getDuel2();
		Integer Duel3 = match.getDuel3();
		Integer Duel4 = match.getDuel4();

		DuelDAO DD=new DuelDAO();
		DD.deleteDuel(Duel1);

		if(Duel2!=null){
			DD.deleteDuel(Duel2);
		}

		if(Duel3!=null){
			DD.deleteDuel(Duel3);
		}

		if(Duel4!=null){
			DD.deleteDuel(Duel4);
		}

		st.close();
		con.close();

		return match;

	}
}
