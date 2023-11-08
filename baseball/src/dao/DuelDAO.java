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

	//duelの情報を取得する
	public int insertDuel(Duel duel)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("INSERT INTO DUEL VALUES(null,?,?,?,?)");

		st.setInt(1, duel.getSchool1());
		st.setInt(2, duel.getSchool2());
		st.setString(3, duel.getStatus());
		st.setString(4, duel.getRound());

		int num=st.executeUpdate();

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
}
