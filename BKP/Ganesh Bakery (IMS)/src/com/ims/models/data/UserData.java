package com.ims.models.data;

public class UserData {
	// TODO Check all primary key id's for data types
	public String userid;
	public String username;
	public String userpass;
	public String usermobile;
	public int userauth;

	public UserData(String userid, String username, String userpass,
			String usermobile, int userauth) {
		this.userid = userid;
		this.username = username;
		this.userpass = userpass;
		this.usermobile = usermobile;
		this.userauth = userauth;
	}

	public UserData() {
	}
}