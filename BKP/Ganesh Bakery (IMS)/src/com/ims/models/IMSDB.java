package com.ims.models;

import java.sql.SQLException;

import com.ims.Program;
import com.ims.misc.Statics;
import com.jdev.girish.database.Database;
import com.jdev.girish.database.exceptions.DriverNotRegisteredException;

public class IMSDB extends Database {

	private String dbclass;

	private String dburl;

	private String dbuser;

	private String dbpass;

	protected boolean connected;

	public IMSDB() {
		super();
		dbclass = Program.APP.getProperty("JDBC_DRIVER");
		dburl = Program.APP.getProperty("DB_URL");
		dbuser = Program.APP.getProperty("DB_USER");
		dbpass = Program.APP.getProperty("DB_PASS");

		try {
			super.registerDriver(dbclass);
			super.connect(dburl, dbuser, dbpass);
			connected = true;
		} catch (ClassNotFoundException | DriverNotRegisteredException
				| SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
			connected = false;
		}
	}

	/**
	 * @return the connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * @param connected
	 *            the connected to set
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}