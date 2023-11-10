package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class SchoolDAO extends DAO {
//トーナメントIDで高校情報のリスト取得
	public List<School> searchSchool(int id)throws Exception {
		List<School> list=new ArrayList<>();
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM SCHOOL WHERE TOURNAMENT_ID=?");
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			School school=new School();
			school.setSchoolId(rs.getInt("SCHOOL_ID"));
			school.setTournamentId(rs.getInt("TOURNAMENT_ID"));
			school.setName(rs.getString("NAME"));
			list.add(school);
		}

		st.close();
		con.close();
		return list;
	}

//	高校情報を登録。登録したぶんの数字が返される
	public int insertSchool(String[] schoolName,int tournamentId) throws Exception{
		int line=0;
		Connection con=getConnection();

		if(schoolName!=null){
			PreparedStatement st=con.prepareStatement(
					"INSERT INTO SCHOOL VALUES(NULL,?,?)");
			for(int i=0;i<schoolName.length; i++){
				st.setInt(1, tournamentId);
				st.setString(2,schoolName[i]);
				line+=st.executeUpdate();
			}

			st.close();
			con.close();
		}
		return line;
	}

//高校情報を変更。変更した情報ぶんの数字が返ってくる
	public int updateSchool(String[] schoolName, int tournamentId) throws Exception{
		int line=0;
		List<School> list=new ArrayList<>();
		Connection con=getConnection();

		list =searchSchool(tournamentId);

		PreparedStatement st=con.prepareStatement(
				"UPDATE SCHOOL SET NAME=? WHERE SCHOOL_ID=?");

		for (int i=0;i<schoolName.length; i++){
			label:for (int j=0;j<schoolName.length; j++){
				if(i != j && schoolName[i].equals(schoolName[j])){
					schoolName[i]="";
					break label;
				}
			}

			st.setString(1,schoolName[i]);
			st.setInt(2,list.get(i).getSchoolId());
			line+=st.executeUpdate();
		}

			st.close();
			con.close();
		return line;
	}
}
