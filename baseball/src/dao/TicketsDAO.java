package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Seat;
import bean.Tickets;
import bean.TicketsAndSeat;
import bean.TicketsExp;

public class TicketsDAO extends DAO{

	private DataSource ds;

	//チケット番号、試合番号、座席番号、チケットステータス、共有ステータスを取得
	public List<Tickets> getTicketsInfo(int purchase_id) throws Exception{

		List<Tickets> list=new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM TICKETS WHERE PURCHASE_ID = ? AND (STATUS = 1 OR STATUS = 4)");
		st.setInt(1, purchase_id);

		ResultSet rs=st.executeQuery();

		while(rs.next()){
			Tickets t=new Tickets();
			t.setTicketsId(rs.getString("tickets_id"));
			t.setMatchId(rs.getInt("match_id"));
			t.setSeatId(rs.getString("seat_id"));
			t.setStatus(rs.getString("status"));
			t.setShared(rs.getBoolean("is_shared"));
			t.setChild(rs.getBoolean("is_child"));

			list.add(t);
		}

		st.close();
		con.close();

		return list;
	}

	//販売停止の際のチケットステータスを販売停止に変更する処理
	public int changeStopSales(Date date)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("UPDATE tickets SET status = '3' WHERE match_id IN (SELECT match_id FROM match WHERE event_date = ?)");

		st.setDate(1, date);

		int num=st.executeUpdate();

		st.close();
		con.close();

		return num;

	}

	//選択された座種の販売中チケット枚数を取得
	public List<Tickets> getTypeSurplus(String type, int matchId)throws Exception{

			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement("select tickets.*,seat.type from tickets left join seat on tickets.seat_id = seat.seat_id where type = ? and status = 3 and match_id = ?");
			st.setString(1, type);
			st.setInt(2, matchId);

			ResultSet rs=st.executeQuery();

			List<Tickets> list=new ArrayList<>();

			while(rs.next()){
				Tickets t=new Tickets();
				t.setTicketsId(rs.getString("tickets_id"));
				t.setSeatId(rs.getString("seat_id"));
				list.add(t);
			}

			st.close();
			con.close();

			return list;

		}

	//中山先生のチケット情報の追加
	public int insert(List<Tickets> list){
		//System.out.println(list.size());
		if (list == null || list.size() == 0) return 0;

		PreparedStatement st = null;
		Connection con = null;
		int num = 0;
		try{

			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/baseball");

			con = ds.getConnection();

			String SQL = "INSERT INTO TICKETS VALUES ";
			for(int i=0;i<list.size();i++){
				if(i==0){
					SQL += "(?,?,?,?,?,?,?)";
				}else{
					SQL += ",(?,?,?,?,?,?,?)";
				}
			}
			//System.out.println(SQL);
			st = con.prepareStatement(SQL);
			for(int i=0;i<list.size();i++){
				st.setString(1+7*i, list.get(i).getTicketsId());
				st.setInt(2+7*i, list.get(i).getPurchaseId());
				st.setInt(3+7*i, list.get(i).getMatchId());
				st.setString(4+7*i, list.get(i).getSeatId());
				st.setString(5+7*i, String.valueOf(list.get(i).getStatus()));
				st.setBoolean(6+7*i, list.get(i).getIsShared());
				st.setBoolean(7+7*i, false);
			}

			boolean bool = st.execute();
			System.out.println(bool);
		}catch(Exception e){
			e.getStackTrace();
		}finally{

			try{
				st.close();
			}catch(Exception e){
				e.getStackTrace();
			}

			try{
				con.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}

		return num;
	}

//選択されたブロックの販売中チケット枚数を取得
	public List<Tickets> getBlockSurplus(String block, int matchId)throws Exception{

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement("select tickets.*,seat.block from tickets left join seat on tickets.seat_id = seat.seat_id where block = ? and status = 3 and match_id = ?");
		st.setString(1, block);
		st.setInt(2,matchId);

		ResultSet rs=st.executeQuery();

		List<Tickets> list=new ArrayList<>();

		while(rs.next()){
			Tickets t=new Tickets();
			t.setTicketsId(rs.getString("tickets_id"));
			list.add(t);
		}

		st.close();
		con.close();

		return list;

	}

	//チケットステータスを購入済みに変更、購入番号を登録する
	public int purchaseTickets(String ticketsId,int purchaseId,boolean chilFlg)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("UPDATE TICKETS SET PURCHASE_ID = ?,STATUS = 1,IS_CHILD = ? WHERE TICKETS_ID = ?");
		st.setInt(1, purchaseId);
		st.setBoolean(2, chilFlg);
		st.setString(3, ticketsId);

		int num=st.executeUpdate();

		st.close();
		con.close();

		return num;
	}

	//試合中止の際のチケットステータスを払い戻し可に変更する
	public int changePostpone(Date date)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("UPDATE tickets SET status = '6' WHERE match_id IN (SELECT match_id FROM match WHERE match.event_date = ?)");
		st.setDate(1, date);

		int num=st.executeUpdate();

		st.close();
		con.close();

		return num;

	}

	//チケットステータスを取得する
	public String getStatus(String ticketsId)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select tickets.status from tickets where tickets_id = ?");
		st.setString(1, ticketsId);

		ResultSet rs = st.executeQuery();

		while(rs.next()){
			String status=rs.getString("status");
			return status;
		}

		return null;

	}

	//チケットステータスを払い戻し済みに変更する
	public int changePaid(String ticketsId)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("update tickets set status = 5 where tickets_id = ?");
		st.setString(1,ticketsId);

		int num = st.executeUpdate();

		st.close();
		con.close();
		return num;
	}

	//共有ステータスを共有済みに変更する
	public int ticketsShare(String ticketsId)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("update tickets set is_shared = true where tickets_id = ?");
		st.setString(1, ticketsId);

		int num = st.executeUpdate();

		st.close();
		con.close();

		return num;

	}

	//チケット表示（購入者）: 購入者のチケットを表示する
	public List<TicketsExp> viewTickets(int spectatorId,Date today)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT tickets_id,status,is_shared,is_child,event_date, purchase.spectator_id, seat.*,tournament.ordinal_num,tournament.name FROM TICKETS join match on tickets.match_id = match.match_id join purchase on tickets.purchase_id = purchase.purchase_id join seat on tickets.seat_id = seat.seat_id join tournament on match.tournament_id = tournament.tournament_id where purchase.spectator_id = ? and event_date <= ?");
		st.setInt(1, spectatorId);
		st.setDate(2, today);

		ResultSet rs=st.executeQuery();

		List<TicketsExp> list=new ArrayList<>();

		while(rs.next()){
			TicketsExp t=new TicketsExp();
			t.setTicketsId(rs.getString("tickets_id"));
			t.setStatus(rs.getString("status"));
			t.setShared(rs.getBoolean("is_shared"));
			t.setChild(rs.getBoolean("is_child"));
			t.setEventDate(rs.getDate("event_date"));
			t.setSpectatorId(rs.getInt("spectator_id"));
			t.setSeatId(rs.getString("seat_id"));
			t.setType(rs.getString("type"));
			t.setStep(rs.getString("step"));
			t.setNumber(rs.getInt("number"));
			t.setGate(rs.getInt("gate"));
			t.setPassage(rs.getString("passage"));
			t.setBlock(rs.getString("block"));
			t.setOrdinalNum(rs.getInt("ordinal_num"));
			t.setTournamentName(rs.getString("name"));

			list.add(t);
		}

		st.close();
		con.close();

		return list;

	}

	//払い戻し可のチケットを表示する
	public List<TicketsExp> viewTicketsRefund(int spectatorId)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT tickets_id,status,is_shared,is_child,event_date, purchase.spectator_id, seat.*,tournament.ordinal_num,tournament.name FROM TICKETS join match on tickets.match_id = match.match_id join purchase on tickets.purchase_id = purchase.purchase_id join seat on tickets.seat_id = seat.seat_id join tournament on match.tournament_id = tournament.tournament_id where purchase.spectator_id = ? and tickets.status = 6;");
		st.setInt(1, spectatorId);

		ResultSet rs=st.executeQuery();

		List<TicketsExp> list=new ArrayList<>();

		while(rs.next()){
			TicketsExp t=new TicketsExp();
			t.setTicketsId(rs.getString("tickets_id"));
			t.setStatus(rs.getString("status"));
			t.setShared(rs.getBoolean("is_shared"));
			t.setChild(rs.getBoolean("is_child"));
			t.setEventDate(rs.getDate("event_date"));
			t.setSpectatorId(rs.getInt("spectator_id"));
			t.setSeatId(rs.getString("seat_id"));
			t.setType(rs.getString("type"));
			t.setStep(rs.getString("step"));
			t.setNumber(rs.getInt("number"));
			t.setGate(rs.getInt("gate"));
			t.setPassage(rs.getString("passage"));
			t.setBlock(rs.getString("block"));
			t.setOrdinalNum(rs.getInt("ordinal_num"));
			t.setTournamentName(rs.getString("name"));

			list.add(t);
		}

		st.close();
		con.close();

		return list;
	}

	//共有されたチケットの情報取得
	public List<TicketsExp> viewSharedTickets(String ticketsId)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT tickets_id,status,is_shared,is_child,event_date, purchase.spectator_id, seat.*,tournament.ordinal_num,tournament.name FROM TICKETS join match on tickets.match_id = match.match_id join purchase on tickets.purchase_id = purchase.purchase_id join seat on tickets.seat_id = seat.seat_id join tournament on match.tournament_id = tournament.tournament_id where tickets.tickets_id = ?");
		st.setString(1, ticketsId);

		ResultSet rs=st.executeQuery();

		List<TicketsExp> list=new ArrayList<>();

		while(rs.next()){
			TicketsExp t=new TicketsExp();
			t.setTicketsId(rs.getString("tickets_id"));
			t.setStatus(rs.getString("status"));
			t.setShared(rs.getBoolean("is_shared"));
			t.setChild(rs.getBoolean("is_child"));
			t.setEventDate(rs.getDate("event_date"));
			t.setSpectatorId(rs.getInt("spectator_id"));
			t.setSeatId(rs.getString("seat_id"));
			t.setType(rs.getString("type"));
			t.setStep(rs.getString("step"));
			t.setNumber(rs.getInt("number"));
			t.setGate(rs.getInt("gate"));
			t.setPassage(rs.getString("passage"));
			t.setBlock(rs.getString("block"));
			t.setOrdinalNum(rs.getInt("ordinal_num"));
			t.setTournamentName(rs.getString("name"));

			list.add(t);
		}

		st.close();
		con.close();

		return list;

	}

	//チケットのステータスを取得する
	public String checkTickets(String ticketsId,Date today)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from tickets join match on tickets.match_id = match.match_id where event_date = ? and tickets_id = ?;");
		st.setDate(1, today);
		st.setString(2,ticketsId);

		ResultSet rs=st.executeQuery();

		String status=null;

		while(rs.next()){
			Tickets t = new Tickets();
			status = t.getStatus();
		}

		st.close();
		con.close();

		return status;

	}

	//チケットのステータスを入場中に変更する
	public int statusAdmission(String ticketsId)throws Exception{

		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("update tickets set status = 4 where tickets_id = ?");
		st.setString(1, ticketsId);

		int num=st.executeUpdate();

		st.close();
		con.close();

		return num;

	}

	//チケットのステータスを退場済みに変更する
	public String statusLeave(String ticketsId)throws Exception {

		Connection con=getConnection();
		PreparedStatement stSpec = con.prepareStatement("select * from tickets join purchase on tickets.purchase_id = purchase.purchase_id where tickets_id = ?;");
		stSpec.setString(1, ticketsId);

		ResultSet rs = stSpec.executeQuery();
		String spectatorId = null;

		while(rs.next()){
			spectatorId = rs.getString("spectator_id");
		}

		if(spectatorId == null){

			return spectatorId;
		}

		PreparedStatement st=con.prepareStatement("update tickets set status = 7 where tickets_id = ?");
		st.setString(1, ticketsId);

		st.executeUpdate();


		st.close();
		stSpec.close();
		con.close();

		return spectatorId;

	}

//	日付とブロックで絞り込んだ販売中のチケット・座席情報
	public List<TicketsAndSeat> selectTickets(Date eventDate, String block)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select tickets.*,match.event_date,seat.* from tickets join match on tickets.match_id = match.match_id join seat on tickets.seat_id = seat.seat_id where match.event_date = ? and seat.block = ? and tickets.status = 3");
		st.setDate(1, eventDate);
		st.setString(2, block);

		ResultSet rs=st.executeQuery();

		List<TicketsAndSeat> list=new ArrayList<>();

		while(rs.next()){
			TicketsAndSeat ts=new TicketsAndSeat();
			Tickets t=new Tickets();
			Seat s=new Seat();

			t.setTicketsId(rs.getString("tickets_id"));
			t.setPurchaseId(rs.getInt("purchase_id"));
			t.setMatchId(rs.getInt("match_id"));
			t.setSeatId(rs.getString("seat_id"));
			t.setStatus(rs.getString("status"));
			t.setShared(rs.getBoolean("is_shared"));
			t.setChild(rs.getBoolean("is_child"));

			s.setSeatId(rs.getString("seat_id"));
			s.setType(rs.getString("type"));
			s.setStep(rs.getString("step"));
			s.setNumber(rs.getInt("number"));
			s.setGate(rs.getInt("gate"));
			s.setPassage(rs.getString("passage"));
			s.setBlock(rs.getString("block"));
			s.setTypeStr();

			ts.setSeat(s);
			ts.setTicket(t);
			ts.setEventDate(rs.getDate("event_date"));

			list.add(ts);
		}

		st.close();
		con.close();

		return list;

	}

//	チケットのチケット・座席情報
	public List<TicketsAndSeat> getSelectTickets(String[] ticketsId)throws Exception{

		Connection con=getConnection();

		String SQL = "select match.event_date,tickets.tickets_id,tickets.is_child,seat.* from tickets join match on tickets.match_id = match.match_id join seat on tickets.seat_id = seat.seat_id where ";
		for(int i=0;i<ticketsId.length;i++){
			if(i==0){
				SQL += "tickets.tickets_id=?";
			}else{
				SQL += " or tickets.tickets_id=?";
			}
		}

		PreparedStatement st = con.prepareStatement(SQL);

		for(int i=0;i<ticketsId.length;i++){
			st.setString(i+1, ticketsId[i]);
		}

		ResultSet rs=st.executeQuery();

		List<TicketsAndSeat> list=new ArrayList<>();

		while(rs.next()){
			TicketsAndSeat ts=new TicketsAndSeat();
			Tickets t=new Tickets();
			Seat s=new Seat();

			t.setTicketsId(rs.getString("tickets_id"));
			t.setChild(rs.getBoolean("is_child"));

			s.setType(rs.getString("type"));
			s.setStep(rs.getString("step"));
			s.setNumber(rs.getInt("number"));
			s.setGate(rs.getInt("gate"));
			s.setPassage(rs.getString("passage"));
			s.setTypeStr();

			ts.setSeat(s);
			ts.setTicket(t);
			ts.setEventDate(rs.getDate("event_date"));

			list.add(ts);
		}

		st.close();
		con.close();

		return list;

	}

}