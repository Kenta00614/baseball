package bean;

public class Spectator implements java.io.Serializable {
	private int spectatorId;
	private String name;
	private String password;
	private String tel;
	private int point;
	private String mail;

	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	public int getSpectatorId() {
		return spectatorId;
	}
	public void setSpectatorId(int spectatorId) {
		this.spectatorId = spectatorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
