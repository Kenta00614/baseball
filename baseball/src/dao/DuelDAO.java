package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Duel;
import bean.DuelExp;
import bean.School;

public class DuelDAO extends DAO{

	//試合番号から高校名、試合ステータスを取得
	public List<DuelExp> getDuelDetail(int duel_id)throws Exception{

		List<DuelExp> list=new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("select school.name,duel.status from duel cross join school where duel_id = ?");
		st.setInt(1,duel_id);

		ResultSet rs=st.executeQuery();
		Boolean flg = false;

		while(rs.next()){
			DuelExp d;
			School s=new School();
			s.setName(rs.getString("name"));
			if(flg){
				d = list.get(list.size()-1);
				d.setSchoolB2(s);
			}else{
				d=new DuelExp();
				d.setSchoolB1(s);
				d.setStatus(rs.getString("status"));
				list.add(d);
			}
			flg = !flg;
		}

		return list;

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
			st.setString(3, "1");
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

		st.setInt(1, duel.getSchool1());
		st.setInt(2, duel.getSchool2());
		st.setString(3, duel.getStatus());
		st.setString(4, duel.getRound());
		st.setInt(5, duel.getDuelId());

		int num=st.executeUpdate();

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

	public DuelExp getDuel(int duel_id)throws Exception{

		DuelExp duel=new DuelExp();
		Connection con = getConnection();
		PreparedStatement st=con.prepareStatement("select * FROM duel JOIN school ON duel.school1 = school.school_id where duel_id = ? order by duel_id");
		st.setInt(1,duel_id);

		ResultSet rs=st.executeQuery();

		while(rs.next()){
			DuelExp d;
			School s=new School();
			d=new DuelExp();
		}

		return duel;

	}
}
