package com.ims.models;

import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.UserData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class User extends IMSDB {

	private String user;

	public User(String user) {
		this.user = user;
	}

	public boolean changePassword(String oldpass, String newpass) {
		try {
			int res = super.executeUpdate("update USERS set pwd=sha1('"
					+ newpass + "') where USERID='" + this.user
					+ "' and pwd=sha1('" + oldpass + "')");
			if (res > 0)
				return true;
			else
				return false;
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return false;
	}

	public boolean addNewUser(String uname, String mob, String upass, int uautho) {
		try {
			super.executeQuery("select userid from users where userid=?",
					this.user);
			if (resultset.next()) {
				Statics.showMessage(null, "User already exists!",
						javax.swing.JOptionPane.ERROR_MESSAGE);
				return false;
			}
			int res = super.executeUpdate(
					"insert into users(userid, uautho, uname, mob, pwd) values(?, ?, ?, ?, sha1('"
							+ upass + "'))", this.user, uautho, uname, mob);
			if (res > 0)
				return true;
			else
				return false;
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return false;
	}

	public UserData[] getAllUsers() {
		try {
			super.executeQuery("select userid, uname, pwd, mob, uautho from users");
			UserData[] data = new UserData[super.getRowCount()];
			for (int i = 0; resultset.next(); i++) {
				data[i] = new UserData(resultset.getString("userid"),
						resultset.getString("uname"),
						resultset.getString("pwd"), resultset.getString("mob"),
						resultset.getInt("uautho"));
			}
			return data;
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			return null;
		}
		return null;
	}

	public int updateUser(String username, String mobno, int uautho) {
		try {
			return super.executeUpdate(
					"update users set uname=?, mob=?, uautho=? where userid=?",
					username, mobno, uautho, this.user);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return 0;
	}
}