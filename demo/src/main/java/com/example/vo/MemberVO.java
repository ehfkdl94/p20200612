package com.example.vo;

public class MemberVO {
	private String userid = null;
	private String password = null;
	private String username = null;
	private String phone = null;
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", password=" + password + ", username=" + username + ", phone=" + phone
				+ ", userage=" + userage + ", joindate=" + joindate + "]";
	}
	private int userage = 0;
	private String joindate = null;
	
	public String getUserid() {
		return this.userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getUserage() {
		return this.userage;
	}
	public void setUserage(int userage) {
		this.userage = userage;
	}
	
	public String getJoindate() {
		return this.joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	
}
