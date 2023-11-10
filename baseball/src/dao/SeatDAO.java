package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Seat;

public class SeatDAO extends DAO{

	private DataSource ds;

	public int insert(List<Seat> list){
		if (list == null || list.size() == 0) return 0;

		PreparedStatement st = null;
		Connection con = null;
		int num = 0;
		try{

			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/kadai");

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
			//boolean bool = st.execute();
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
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/kadai");

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





}
