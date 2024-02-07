package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Match;

public class MatchDAO extends DAO{

	//大会番号、開催日を取得
	public  Match getMatchInfo(int match_id)throws Exception{

		Match m=null;
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH WHERE MATCH_ID = ?");
		st.setInt(1,match_id);

		ResultSet rs=st.executeQuery();

		while(rs.next()){
			m=new Match();
			m.setMatchId(match_id);
			m.setTournamentId(rs.getInt("tournament_id"));
			m.setEventDate(rs.getDate("event_date"));
			m.setEventDayOfWeek();
		}

		st.close();
		con.close();

		return m;
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
	public List<Match> searchMatchDetail(Date eventDate)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH WHERE EVENT_DATE = ?");
		st.setDate(1, eventDate);

		ResultSet rs=st.executeQuery();

		List<Match> list=new ArrayList<>();

		while(rs.next()){
			Match m = new Match();
			m.setMatchId(rs.getInt("match_id"));
			m.setTournamentId(rs.getInt("tournament_id"));
			m.setSaleStartAt(rs.getDate("sale_start_at"));
			m.setEventDate(rs.getDate("event_date"));
			m.setDuel1(rs.getInt("duel1"));
			m.setDuel2(rs.getInt("duel2"));
			m.setDuel3(rs.getInt("duel3"));
			m.setDuel4(rs.getInt("duel4"));
			m.setEventDateStr();
			m.setSaleStartAtStr();
			list.add(m);

		}

		st.close();
		con.close();

		return list;

	}

	//試合情報の追加
	public int insertMatch(Match match)throws Exception{

		try{
			Connection con=getConnection();

			String SQL = "insert into MATCH values(null,?,?,?,?,";
				if(match.getDuel4() > 0){
					SQL += "?,?,?)";
				}else if(match.getDuel3() > 0){
					SQL += "?,?,null)";
				}else if(match.getDuel2() > 0){
					SQL += "?,null,null)";
				}else{
					SQL += "null,null,null)";
				}


			PreparedStatement st=con.prepareStatement(SQL);
			st.setInt(1,match.getTournamentId());
			st.setDate(2, match.getEventDate());
			st.setDate(3, match.getSaleStartAt());
			st.setInt(4, match.getDuel1());

			if(match.getDuel2() > 0){
				st.setInt(5, match.getDuel2());
			}
			if(match.getDuel3() > 0){
				st.setInt(6, match.getDuel3());
			}
			if(match.getDuel4() > 0){
				st.setInt(7, match.getDuel4());
			}

			int num = st.executeUpdate();

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
	public int changeMatch(int duelId,int matchId,int duelNum)throws Exception{
		int num = 0;
		try{
			Connection con=getConnection();
			String SQL = "UPDATE MATCH SET ";
			if(duelNum == 1){
				if(duelId != 0){
					SQL += "DUEL1 = ?";
				}else{
					SQL += "duel1=0 ";
				}
			}else if(duelNum == 2){
				if(duelId != 0){
					SQL += "DUEL2 = ?";
				}else{
					SQL += "duel2=null ";
				}
			}else if(duelNum == 3){
				if(duelId != 0){
					SQL += "DUEL3 = ?";
				}else{
					SQL += "duel3=null ";
				}
			}else if(duelNum == 4){
				if(duelId != 0){
					SQL += "DUEL4 = ?";
				}else{
					SQL += "duel4=null ";
				}
			}

			SQL += " WHERE MATCH_ID = ?";
			PreparedStatement st=con.prepareStatement(SQL);

			if(duelId != 0){
				st.setInt(1, duelId);
				st.setInt(2, matchId);
			}else{
				st.setInt(1, matchId);
			}



			num = st.executeUpdate();


			st.close();
			con.close();

			return num;

		}catch(Exception e){

			num=0;
			return num;

		}

	}

	//すべての試合日情報を取得
	public List<Match> getMatchAll()throws Exception{

		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH order by match_id");
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

	//トーナメントIDから試合日番号、開催日、チケット販売日を取得
	public List<Match> searchMatchTournament(int tournament_id)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH WHERE TOURNAMENT_ID = ? order by event_date");

		st.setInt(1, tournament_id);

		ResultSet rs=st.executeQuery();

		List<Match> list=new ArrayList<>();

		while(rs.next()){
			Match m=new Match();
			m.setMatchId(rs.getInt("match_id"));
			m.setEventDate(rs.getDate("event_date"));
			m.setSaleStartAt(rs.getDate("sale_start_at"));
			m.setEventDayOfWeek();
			m.setSaleDayOfWeek();
			m.setDispFlg();
			m.setSaleFlg();
			m.setDuel1(rs.getInt("duel1"));
			m.setDuel2(rs.getInt("duel2"));
			m.setDuel3(rs.getInt("duel3"));
			m.setDuel4(rs.getInt("duel4"));
			m.setEventDateStr();
			m.setSaleStartAtStr();
			list.add(m);
		}

		st.close();
		con.close();

		return list;
	}

	//開催日から試合情報を取得
	public List<Match> searchSaleStartDate(Date saleStartDate)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM MATCH WHERE sale_start_at = ?");
		st.setDate(1, saleStartDate);

		ResultSet rs=st.executeQuery();

		List<Match> list=new ArrayList<>();

		while(rs.next()){
			Match m = new Match();
			m.setMatchId(rs.getInt("match_id"));
			m.setSaleStartAt(rs.getDate("sale_start_at"));
			m.setEventDate(rs.getDate("event_date"));
			list.add(m);
		}

		st.close();
		con.close();

		return list;

	}

}
