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

//	高校名を登録された分の数字が返される
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

	public int updateSchool(String[] schoolName,int tournamentId) throws Exception{
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
}
