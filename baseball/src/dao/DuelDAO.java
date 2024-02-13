package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Duel;
import bean.DuelExp;

public class DuelDAO extends DAO{

	//試合番号から高校名、試合ステータスを取得
	public DuelExp getDuelDetail(int duel_id)throws Exception{

		DuelExp duel=new DuelExp();
		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("SELECT s1.NAME AS NAME1, s1.SCHOOL_ID AS ID1, s2.NAME AS NAME2, s2.SCHOOL_ID AS ID2, d.STATUS, d.ROUND FROM DUEL d INNER JOIN SCHOOL s1 ON d.SCHOOL1 = s1.SCHOOL_ID INNER JOIN SCHOOL s2 ON d.SCHOOL2 = s2.SCHOOL_ID WHERE DUEL_ID=?");
		st.setInt(1,duel_id);

		ResultSet rs=st.executeQuery();

		while(rs.next()){
			duel.setSchoolNameA(rs.getString("name1"));
			duel.setSchoolNameB(rs.getString("name2"));
			duel.setSchoolId1(rs.getInt("id1"));
			duel.setSchoolId2(rs.getInt("id2"));
			duel.setStatus(rs.getString("status"));
			duel.setRound(rs.getString("round"));
			duel.setRoundStr();
			duel.setStatusStr();
			duel.setDuelId(duel_id);
		}

		return duel;

	}

	//duelの情報を登録する登録されるとduelIdを返す
	public int insertDuel(Duel duel)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("INSERT INTO DUEL VALUES(null,?,?,?,?)");
		int num=0;
		int school1 = duel.getSchool1();
		int school2 = duel.getSchool2();

		if(school1 != 0 && school2 != 0){
			st.setInt(1, duel.getSchool1());
			st.setInt(2, duel.getSchool2());
			if(duel.getStatus() == null){
				st.setString(3, "1");
			}else{
				st.setString(3, duel.getStatus());
			}
			st.setString(4, duel.getRound());
			st.executeUpdate();

			st=con.prepareStatement("select duel_id from duel");
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				num = rs.getInt("duel_id");
			}
		}


		st.close();
		con.close();

		return num;

	}

	//duel_idが一致するduelの情報を更新する
	public int changeDuel(Duel duel)throws Exception{

		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("UPDATE DUEL SET SCHOOL1 = ?,SCHOOL2 = ?,STATUS = ?,ROUND = ? WHERE DUEL_ID = ?");

		int num = 0;
		int school1 = duel.getSchool1();
		int school2 = duel.getSchool2();

		if(school1 != 0 && school2 != 0){
			st.setInt(1, duel.getSchool1());
			st.setInt(2, duel.getSchool2());
			st.setString(3, duel.getStatus());
			st.setString(4, duel.getRound());
			st.setInt(5, duel.getDuelId());
			num=st.executeUpdate();
		}else{
			deleteDuel(duel.getDuelId());
		}

		st.close();
		con.close();

		return num;


	}

	//試合情報を削除
	public Duel deleteDuel(int duel_id)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("DELETE FROM DUEL WHERE DUEL_ID = ?");
		st.setInt(1, duel_id);

		int num=st.executeUpdate();

		Duel duel = new Duel();

		if(num==0){
			duel=null;
		}

		st.close();
		con.close();

		return duel;
	}
}
