package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

			PreparedStatement st=con.prepareStatement("select tickets.*,seat.type from tickets left join seat on tickets.seat_id = seat.seat_id where type = ? and status = 2 and match_id = ?");
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
					SQL += "(?,?,?,?,?,?,?,?)";
				}else{
					SQL += ",(?,?,?,?,?,?,?,?)";
				}
			}
			//System.out.println(SQL);
			st = con.prepareStatement(SQL);
			for(int i=0;i<list.size();i++){
				st.setString(1+8*i, list.get(i).getTicketsId());
				st.setInt(2+8*i, list.get(i).getPurchaseId());
				st.setInt(3+8*i, list.get(i).getMatchId());
				st.setString(4+8*i, list.get(i).getSeatId());
				st.setString(5+8*i, String.valueOf(list.get(i).getStatus()));
				st.setBoolean(6+8*i, list.get(i).getIsShared());
				st.setBoolean(7+8*i, false);
				st.setString(8+8*i, null);
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

		PreparedStatement st=con.prepareStatement("select tickets.*,seat.block from tickets left join seat on tickets.seat_id = seat.seat_id where block = ? and status = 2 and match_id = ?");
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
	public int purchaseTickets(String ticketsId,int purchaseId,boolean chilFlg,Connection con)throws Exception{

		PreparedStatement st=con.prepareStatement("UPDATE TICKETS SET PURCHASE_ID = ?,STATUS = 1,IS_CHILD = ? WHERE TICKETS_ID = ?");
		st.setInt(1, purchaseId);
		st.setBoolean(2, chilFlg);
		st.setString(3, ticketsId);

		int num=st.executeUpdate();

		st.close();

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

	//共有ステータスを共有済みに変更とUUIDを作成・取得
	public UUID ticketsShare(String ticketsId)throws Exception{
		Connection con=getConnection();
		UUID newUuid = null;

		PreparedStatement st=con.prepareStatement("SELECT UUID FROM TICKETS WHERE TICKETS_ID=?");
		st.setString(1, ticketsId);
		ResultSet rs = st.executeQuery();

		while(rs.next()){
			newUuid=(UUID)rs.getObject("UUID");
		}
		if(newUuid == null){
			st=con.prepareStatement("UPDATE TICKETS SET UUID=?,IS_SHARED=TRUE WHERE TICKETS_ID=?");
			newUuid = UUID.randomUUID();
			st.setObject(1, newUuid);
			st.setString(2, ticketsId);
			st.executeUpdate();
		}

		st.close();
		con.close();

		return newUuid;
	}

	//チケット表示（購入者）: 購入者のチケットを表示する
	public List<TicketsExp> viewTickets(int spectatorId,Date today)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT tickets_id,status,is_shared,is_child,event_date, purchase.spectator_id, seat.*,tournament.ordinal_num,tournament.name FROM TICKETS join match on tickets.match_id = match.match_id join purchase on tickets.purchase_id = purchase.purchase_id join seat on tickets.seat_id = seat.seat_id join tournament on match.tournament_id = tournament.tournament_id where purchase.spectator_id = ? and status in ('1', '4') and event_date >= ? order by event_date,tickets_id");
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
			t.setEventDayOfWeek();
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
			t.setTypeStr();
			t.setPrice();
			t.setDateStr();

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
			t.setDateStr();
			t.setEventDayOfWeek();
			t.setTypeStr();
			t.setPrice();

			list.add(t);
		}

		st.close();
		con.close();

		return list;
	}

	//共有されたチケットの情報取得
	public TicketsExp viewSharedTickets(String ticketsId)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT tickets_id,status,is_shared,is_child,event_date, purchase.spectator_id, seat.*,tournament.ordinal_num,tournament.name FROM TICKETS join match on tickets.match_id = match.match_id join purchase on tickets.purchase_id = purchase.purchase_id join seat on tickets.seat_id = seat.seat_id join tournament on match.tournament_id = tournament.tournament_id where tickets.tickets_id = ?");
		st.setString(1, ticketsId);

		ResultSet rs=st.executeQuery();

		TicketsExp ticketData=new TicketsExp();

		while(rs.next()){
			ticketData.setTicketsId(rs.getString("tickets_id"));
			ticketData.setStatus(rs.getString("status"));
			ticketData.setShared(rs.getBoolean("is_shared"));
			ticketData.setChild(rs.getBoolean("is_child"));
			ticketData.setEventDate(rs.getDate("event_date"));
			ticketData.setSpectatorId(rs.getInt("spectator_id"));
			ticketData.setSeatId(rs.getString("seat_id"));
			ticketData.setType(rs.getString("type"));
			ticketData.setStep(rs.getString("step"));
			ticketData.setNumber(rs.getInt("number"));
			ticketData.setGate(rs.getInt("gate"));
			ticketData.setPassage(rs.getString("passage"));
			ticketData.setBlock(rs.getString("block"));
			ticketData.setOrdinalNum(rs.getInt("ordinal_num"));
			ticketData.setTournamentName(rs.getString("name"));
			ticketData.setEventDayOfWeek();
			ticketData.setTypeStr();
			ticketData.setDateStr();
		}

		st.close();
		con.close();

		return ticketData;

	}

	//チケットのステータスを取得する
	public Tickets checkTickets(String ticketsId,Date today,Connection con)throws Exception{
		Boolean flg = false;

		if(con == null){
			con=getConnection();
			flg = true;
		}

		PreparedStatement st=con.prepareStatement("select * from tickets join match on tickets.match_id = match.match_id where event_date = ? and tickets_id = ?;");
		st.setDate(1, today);
		st.setString(2,ticketsId);

		ResultSet rs=st.executeQuery();

		Tickets ticket=new Tickets();

		while(rs.next()){
			ticket.setStatus(rs.getString("status"));
			ticket.setTicketsId(rs.getString("id"));
		}

		st.close();
		if(flg){
			con.close();
		}

		return ticket;

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

	//チケットのステータスを退場済みに変更する 要変更 リセールチケット追加
	public TicketsExp statusLeave(String ticketsId,Connection con)throws Exception {

		//購入者を特定
		PreparedStatement st1 = con.prepareStatement("select * from tickets join purchase on tickets.purchase_id = purchase.purchase_id where tickets_id = ?;");
		st1.setString(1, ticketsId);

		ResultSet rs = st1.executeQuery();
		TicketsExp ticket = new TicketsExp();
		ticket.setSpectatorId(-1);

		while(rs.next()){
			ticket.setSpectatorId(rs.getInt("spectator_id"));
			ticket.setChild(rs.getBoolean("is_child"));
			ticket.setType(ticketsId.substring(0,2));
			ticket.setMatchId(rs.getInt("match_id"));
			ticket.setSeatId(rs.getString("seat_id"));
			ticket.setPurchaseId(rs.getInt("purchase_id"));
		}

		//購入者が不明な場合、エラー。リターンする。
		if(ticket.getSpectatorId() == -1){
			throw new Exception();
		}

		//チケットのステータスを退場済みへ書き換え
		PreparedStatement st2=con.prepareStatement("update tickets set status = 7 where tickets_id = ?");
		st2.setString(1, ticketsId);
		st2.executeUpdate();

		//リセ―ルチケットを作成する
		String resaleNoStr = null;
		int resaleNo = Integer.parseInt(ticketsId.substring(8,10))+1;
		if (resaleNo < 10){
			resaleNoStr = "0"+resaleNo;
		}else{
			resaleNoStr = String.valueOf(resaleNo);
		}
		String newTicketsId = ticketsId.substring(0,8) + resaleNoStr + ticketsId.substring(10,18);

		PreparedStatement st3=con.prepareStatement("insert into tickets values (?,null,?,?,2,FALSE,FALSE,null)");
		st3.setString(1, newTicketsId);
		st3.setInt(2, ticket.getMatchId());
		st3.setString(3, ticket.getSeatId());
		st3.executeUpdate();

		st1.close();
		st2.close();
		st3.close();

		return ticket;
	}

//	日付とブロックで絞り込んだ販売中のチケット・座席情報
	public List<TicketsAndSeat> selectTickets(Date eventDate, String block)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select tickets.*,match.event_date,seat.* from tickets join match on tickets.match_id = match.match_id join seat on tickets.seat_id = seat.seat_id where match.event_date = ? and seat.block = ? and tickets.status = 2");
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

//	UUIDの一致するチケットIDを取得
	public String getTicketsIdByUuid(UUID uuid)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT TICKETS_ID FROM TICKETS WHERE UUID=?");
		st.setObject(1, uuid);

		ResultSet rs=st.executeQuery();

		String ticketsId = null;
		while(rs.next()){
			ticketsId = rs.getString("tickets_id");
		}

		st.close();
		con.close();

		return ticketsId;
	}

	// チケットIDからチケット情報を取得する
	public List<Tickets> getTicketsInfo(String tickets_id)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM TICKETS WHERE TICKETS_ID = ?");
		st.setString(1, tickets_id);

		ResultSet rs=st.executeQuery();

		List<Tickets> list=new ArrayList<>();

		while(rs.next()){
			Tickets t=new Tickets();
			t.setTicketsId(rs.getString("tickets_id"));
			t.setSeatId(rs.getString("seat_id"));
			t.setChild(rs.getBoolean("is_child"));
			list.add(t);
		}

		st.close();
		con.close();

		return list;

	}
}