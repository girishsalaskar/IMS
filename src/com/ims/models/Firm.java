package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.Firm_Data;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class Firm extends IMSDB {

	public Firm_Data getFirmById(int id) {
		Firm_Data data = null;
		try {
			super.executeQuery("select * from firm where firmid=?", id);
			if (resultset.next()) {
				data = new Firm_Data();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		}
		return data;
	}

	public Firm_Data getFirmData() {
		Firm_Data data = null;
		try {
			super.executeQuery("select * from firm where active=true");
			if (resultset.next()) {
				data = new Firm_Data();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		}
		return data;
	}

	public int updateFirmData(Firm_Data data) {
		int ret = -1;
		if (data.firmid < 1) {
			try {
				super.executeQuery(
						"select * from firm where binary firmname=? and binary firmaddr=? and "
								+ "binary firmprop=? and binary firmphone=?",
						data.firmname, data.firmaddr, data.firmprop,
						data.firmphone);
				if (resultset.next())
					ret = -2;
				else {
					ret = super.executeUpdate(
							"insert into firm(firmname, licno, firmaddr, firmprop, firmphone, active)"
									+ " values(?, ?, ?, ?, ?, ?)",
							data.firmname, data.licno, data.firmaddr,
							data.firmprop, data.firmphone, data.active);
				}
			} catch (DatabaseNotConnectedException | SQLException e) {
				Statics.handleException(e);
			}
		} else {
			try {
				ret = super.executeUpdate(
						"update firm set firmname=?, licno=?, firmaddr=?,"
								+ " firmprop=?, firmphone=? where firmid=?",
						data.firmname, data.licno, data.firmaddr,
						data.firmprop, data.firmphone, data.firmid);
			} catch (SQLException | DatabaseNotConnectedException e) {
				Statics.handleException(e);
			}
		}
		return ret;
	}

	private void initData(Firm_Data data, ResultSet res) {
		try {
			data.firmid = res.getInt("firmid");
			data.firmname = res.getString("firmname");
			data.licno = res.getString("licno");
			data.firmaddr = res.getString("firmaddr");
			data.firmprop = res.getString("firmprop");
			data.firmphone = res.getString("firmphone");
			data.active = res.getInt("active");
		} catch (SQLException e) {
			Statics.handleException(e);
		}
	}

	public Firm_Data[] getAllFirms() {
		Firm_Data[] data = null;
		try {
			super.executeQuery("select * from firm");
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new Firm_Data[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new Firm_Data();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			e.printStackTrace();
		}
		return data;
	}

	public int activateFirm(Firm_Data data) {
		int ret=0;
		if (data != null) {
			try {
				super.setAutoCommit(false);
				ret+=super.executeUpdate("update firm set active=0");
				ret+=super.executeUpdate("update firm set active=1 where firmid=?",
						data.firmid);
				super.commit();
				super.setAutoCommit(true);
			} catch (SQLException | DatabaseNotConnectedException e) {
				Statics.handleException(e);
			}
		}
		return ret;
	}
}