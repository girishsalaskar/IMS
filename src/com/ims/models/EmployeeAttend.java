package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.EmployeeAttend_Data;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class EmployeeAttend extends IMSDB {

	public int updateAttendance(EmployeeAttend_Data data[]) {
		int ret = 0;
		if (data != null) {
			if (data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					try {
						super.executeUpdate(
								"insert into empattendance(empid, attenddate, status) values(?,?,?)",
								data[i].empid, data[i].attenddate,
								data[i].status);
						ret++;
					} catch (SQLException | DatabaseNotConnectedException e) {
						// TODO Auto-generated catch block
						if (!(e instanceof DatabaseNotConnectedException))
							Statics.handleException(e);
						else
							e.printStackTrace();
					}
				}
			}
		}
		return ret;
	}

	public EmployeeAttend_Data[] getAttendanceByDate(java.util.Date attenddate) {
		EmployeeAttend_Data[] data = null;
		try {
			super.executeQuery(
					"select * from empattendance where attenddate=?",
					new java.sql.Date(attenddate.getTime()));
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new EmployeeAttend_Data[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new EmployeeAttend_Data();
					initData(data[i], resultset);
				}
			} else
				data = null;
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			if (!(e instanceof DatabaseNotConnectedException)
					&& !(e instanceof EmptyResultSetException))
				Statics.handleException(e);
			else
				e.printStackTrace();
		}
		return data;
	}

	public EmployeeAttend_Data[] getMonthWiseAttendance(int month, int year) {
		EmployeeAttend_Data[] data = null;
		try {
			super.executeQuery(
					"select * from empattendance where month(attenddate)=? and year(attenddate)=?",
					month, year);
			if (super.getRowCount() > 0) {
				data = new EmployeeAttend_Data[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new EmployeeAttend_Data();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public EmployeeAttend_Data[] getMonthWiseAttendance(int month, int year,
			long l) {
		EmployeeAttend_Data[] data = null;
		try {
			super.executeQuery(
					"select * from empattendance where month(attenddate)=? and year(attenddate)=? and empid=?",
					month, year, l);
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new EmployeeAttend_Data[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new EmployeeAttend_Data();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	private void initData(EmployeeAttend_Data data, ResultSet res)
			throws SQLException {
		data.attendid = res.getLong("attendid");
		data.empid = res.getLong("empid");
		if (res.getDate("attenddate") != null)
			data.attenddate = new java.util.Date(res.getDate("attenddate")
					.getTime());
		else
			data.attenddate = null;
		data.status = res.getInt("status");
	}
}