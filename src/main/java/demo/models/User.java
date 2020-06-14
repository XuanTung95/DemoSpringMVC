package demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"userId"})})
public class User {
	@Id
	@Column(name = "userId")
	private String userId;
	@Column(name = "email")
	private String email;
	@Column(name = "passwords")
	private String passwords;
	
	public User() {
		
	}
	
	public User(String userId, String email, String passwords) {
		this.userId = userId;
		this.email = email;
		this.passwords = passwords;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	
	@Override
	public String toString() {
		StringBuilder strBd = new StringBuilder();
		strBd.append("UserId: " + getUserId());
		strBd.append("; Email: " + getEmail());
		strBd.append("; Pass: " + getPasswords());
		return strBd.toString();
	}
}
