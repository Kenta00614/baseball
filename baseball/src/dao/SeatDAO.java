package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Seat;

public class SeatDAO extends DAO{

	private DataSource ds;

	@SuppressWarnings("unused")
	public int insert(List<Seat> list){
		if (list == null || list.size() == 0) return 0;

		PreparedStatement st = null;
		Connection con = null;
		int num = 0;
		try{

			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/baseball");

			con = ds.getConnection();

			String SQL = "INSERT INTO SEAT VALUES ";
			for(int i=0;i<list.size();i++){
				if(i==0){
					SQL += "(?,?,?,?,?,?,?)";
				}else{
					SQL += ",(?,?,?,?,?,?,?)";
				}
			}
			//System.out.println(list.size());
			st = con.prepareStatement(SQL);
			for(int i=0;i<list.size();i++){
				st.setString(1+7*i, list.get(i).getSeatId());
				st.setString(2+7*i, list.get(i).getType());
				st.setString(3+7*i, list.get(i).getStep());
				st.setInt(4+7*i, list.get(i).getNumber());
				st.setInt(5+7*i, list.get(i).getGate()); //入口
				st.setString(6+7*i, list.get(i).getPassage()); //通路
				st.setString(7+7*i, list.get(i).getBlock()); //ブロック
			}
			boolean bool = st.execute();
			//System.out.println(bool);
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

	public ArrayList<Seat> getAllId(){

		PreparedStatement st = null;
		Connection con = null;
		ArrayList<Seat> list = new ArrayList<Seat>();
		Seat seat = null;
		try{

			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/baseball");

			con = ds.getConnection();

			String SQL = "SELECT * FROM SEAT";

			st = con.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				seat = new Seat();
				seat.setSeatId(rs.getString("seat_id"));
				list.add(seat);
			}


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

		return list;
	}

//	座種でブロック名を取得
	public ArrayList<String> getBlock(String seatType)throws Exception{
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement("select block from seat where type = ?");
		st.setString(1, seatType);

		ResultSet rs=st.executeQuery();

		ArrayList<String> list= new ArrayList<>();

		while(rs.next()){
			try{
				if(!(list.get(list.size()-1).equals(rs.getString("block")))){
					list.add(rs.getString("block"));
				}
			}catch(Exception ArrayIndexOutOfBoundsException){
				list.add(rs.getString("block"));
			}
		}

		st.close();
		con.close();

		return list;

	}

//	ブロックで座席idとstep取得
	public HashMap<String,Object> getSeat(String block)throws Exception{
		Connection con=getConnection();
		int startNum = 0;
		int endNum = 0;
		int step = 0;
		HashMap<String,Object> map = new HashMap<>();
		String dummyId = "";
		int count = 0;

		PreparedStatement st0=con.prepareStatement("select min(number) as min,max(number) as max,count(distinct step) as step from seat where block = ?");
		st0.setString(1, block);
		ResultSet rs0=st0.executeQuery();

		while(rs0.next()){
			startNum = rs0.getInt("min");
			endNum = rs0.getInt("max");
			step = rs0.getInt("step");
		}

		PreparedStatement st=con.prepareStatement("select seat_id,step,number from seat where block = ? order by step desc,number");
		st.setString(1, block);
		ResultSet rs=st.executeQuery();

		ArrayList<ArrayList<Seat>> list= new ArrayList<>();
		for(int i=0;i<step;i++){
			list.add(new ArrayList<Seat>(endNum-startNum+1));
			for(int j=0;j<(endNum-startNum+1);j++){
				dummyId = String.valueOf(count++);
				Seat seat = new Seat();
				seat.setSeatId(dummyId);
				list.get(i).add(seat);
			}
		}

		count = -1;
		String tempStep = "";

		while(rs.next()){
			Seat s = new Seat();
			s.setSeatId(rs.getString("seat_id"));
			s.setStep(rs.getString("step"));
			s.setNumber(rs.getInt("number"));
			if(!tempStep.equals(s.getStep())){
				tempStep = s.getStep();
				count++;
			}
			list.get(count).add(s.getNumber()-1, s);
			list.get(count).remove(list.get(count).size()-1);

		}

		st.close();
		con.close();

		map.put("step", step);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("seats", list);

		return map;

	}
}