package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.Firm_Data;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class Firm extends IMSDB {

	public Firm_Data getFirmData() {
		Firm_Data data = null;
		try {
			super.executeQuery("select * from firm");
			if (resultset.next()) {
				data = new Firm_Data();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
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
							"insert into firm(firmname, licno, firmaddr, firmprop, firmphone)"
									+ " values(?, ?, ?, ?, ?)", data.firmname,
							data.licno, data.firmaddr, data.firmprop,
							data.firmphone);
				}
			} catch (DatabaseNotConnectedException | SQLException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
	}
}