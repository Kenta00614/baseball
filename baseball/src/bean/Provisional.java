package bean;

import java.util.UUID;

public class Provisional implements java.io.Serializable {
	private UUID uuid;
	private String mail;
	private String name;
	private String password;
	private String tel;
	private int spectatorId;

	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	public int getSpectatorId() {
		return spectatorId;
	}
	public void setSpectatorId(int spectatorId) {
		this.spectatorId = spectatorId;
	}
}
