package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import bean.Provisional;
import bean.Spectator;

public class SpectatorDAO extends DAO {

//メールとパスワード一致で観戦客情報を取得
	public Spectator loginSpec(String mail, String password)
		throws Exception {
		Spectator spectator=null;

		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
			"SELECT * FROM SPECTATOR WHERE MAIL=? AND PASSWORD=?");
		st.setString(1, mail);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			spectator=new Spectator();
			spectator.setSpectatorId(rs.getInt("SPECTATOR_ID"));
			spectator.setName(rs.getString("NAME"));
			spectator.setPassword(rs.getString("PASSWORD"));
			spectator.setTel(rs.getString("TEL"));
			spectator.setPoint(rs.getInt("POINT"));
			spectator.setMail(rs.getString("MAIL"));
		}

		st.close();
		con.close();
		return spectator;
	}

//	引数と同じメールアドレスを持つ場合は引数と同じメールアドレスを返す
	public String searchSameMail(String mail)throws Exception {
			String search="";
			Connection con=getConnection();

			PreparedStatement stdup=con.prepareStatement(
					"SELECT * FROM SPECTATOR WHERE MAIL=?");
				stdup.setString(1, mail);
				ResultSet rsdup=stdup.executeQuery();
				while (rsdup.next()) {
					search=rsdup.getString("mail");
				}

			stdup.close();
			con.close();
			return search;
		}

//	新規会員登録、登録されると1を返す
	public int addNewSpec(UUID uuid) throws Exception{
		Connection con=getConnection();
		int line=0;

		ProvisionalDAO provisional=new ProvisionalDAO();
		Provisional search=provisional.searchUuid(uuid);

		PreparedStatement st=con.prepareStatement(
				"INSERT INTO SPECTATOR VALUES(NULL,?,?,?,0,?)");
		if(search!=null){
			st.setString(1,search.getName());
			st.setString(2,search.getPassword());
			st.setString(3,search.getTel());
			st.setString(4,search.getMail());
			line=st.executeUpdate();

			provisional.delUuid(uuid);
		}

		st.close();
		con.close();

		return line;
	}

	//ポイントの更新、-1が戻り値の場合エラー
	public int updatePoint(int spectatorId,int point)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("SELECT POINT FROM SPECTATOR WHERE SPECTATOR_ID = ?");
		st.setInt(1, spectatorId);

		ResultSet rs=st.executeQuery();

		int pointPrev;

		if(rs.next()){
			pointPrev = rs.getInt("point");
		}else{
			int num=-1;
			return num;
		}

		int pointNew;

		if(point<0){
			if(pointPrev+point >= 0){
				pointNew = pointPrev + point;
			}else{
				int num=-1;
				return num;
			}
		}else{
			pointNew = pointPrev + point;
		}


		PreparedStatement stUpdate = con.prepareStatement("update spectator set point = ?");
		stUpdate.setInt(1, pointNew);

		int num = stUpdate.executeUpdate();

		return num;

	}

	//観戦客情報を変更する
	public int changeSpectatorInfo(Spectator spectator)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("UPDATE SPECTATOR SET NAME = ?,TEL = ? WHERE SPECTATOR_ID = ?");

		st.setString(1, spectator.getName());
		st.setString(2,spectator.getTel());
		st.setInt(3, spectator.getSpectatorId());

		int num = st.executeUpdate();

		st.close();
		con.close();

		return num;

	}

	//パスワードの再設定
	public int updatePassword(UUID uuid,String password)throws Exception{

		ProvisionalDAO PD=new ProvisionalDAO();
		Provisional provisional=PD.searchUuid(uuid);

		String mail=provisional.getMail();

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("update spectator set password = ? where mail = ?");
		st.setString(1, password);
		st.setString(2, mail);

		int num=st.executeUpdate();

		PD.delUuid(uuid);

		st.close();
		con.close();

		return num;

	}

	//メールアドレスを再設定する
	public int updateMail(int spectatorId,String mail,UUID uuid)throws Exception{

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("update spectator set mail = ? where spectator_id = ?");
		st.setString(1, mail);
		st.setInt(2, spectatorId);

		int num = st.executeUpdate();

		ProvisionalDAO PD = new ProvisionalDAO();
		PD.delUuid(uuid);

		st.close();
		con.close();

		return num;

	}
}
