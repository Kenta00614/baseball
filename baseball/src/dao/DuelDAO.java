package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
}
