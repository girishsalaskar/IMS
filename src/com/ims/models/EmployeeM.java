package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.EmployeeM_Data;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class EmployeeM extends IMSDB {

	public EmployeeM_Data[] getAllEmployees() {
		try {
			super.executeQuery("select * from empm");
			if (super.getRowCount() > 0) {
				EmployeeM_Data[] data = new EmployeeM_Data[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new EmployeeM_Data();
					initData(data[i], resultset);
				}
				return data;
			} else
				return null;
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			if (!(e instanceof EmptyResultSetException))
				Statics.handleException(e);
		}
		return null;
	}

	public EmployeeM_Data[] getAllEnabledEmployees() {
		try {
			super.executeQuery("select * from empm where empenabled=1");
			if (super.getRowCount() > 0) {
				EmployeeM_Data[] data = new EmployeeM_Data[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new EmployeeM_Data();
					initData(data[i], resultset);
				}
				return data;
			} else
				return null;
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			if (!(e instanceof EmptyResultSetException))
				Statics.handleException(e);
		}
		return null;
	}

	public int updateEmployee(EmployeeM_Data data, boolean newemp) {
		int ret = 0;
		if (newemp) {
			// INFO New Employee
			try {
				super.executeQuery(
						"select * from empm where empname=? and empmo=?",
						data.empname, data.empmo);
				if (resultset.next())
					ret = -1;

				else {
					if (super
							.executeUpdate(
									"insert into empm(empname, empadd, empmo, joindate, desgn, empsal, acbal, empenabled, emppic) values(?,?,?,?,?,?,?,?,?)",
									data.empname, data.empadd, data.empmo,
									new java.sql.Date(data.joindate.getTime()),
									data.desgn, data.empsal, data.acbal,
									data.empenabled, data.emppic) <= 0)
						ret = 0;
					else
						ret = 1;

					if (ret == 1) {
						long id = -1;
						super.executeQuery("select max(empid) from empm");
						if (resultset.next())
							id = resultset.getLong(1);

						if (id > 0) {
							if (super.executeUpdate(
									"insert into empadvance(empid, paiddate, prevbal, paidamt, balanceamt)"
											+ " values (?, ?, ?, ?, ?)", id,
									new java.sql.Date(data.joindate.getTime()),
									0, data.acbal, data.acbal) > 0)
								ret = 1;
							else
								ret = -2;
						} else
							ret = -2;
					} else
						ret = -2;
				}
			} catch (DatabaseNotConnectedException | SQLException e) {
				// TODO Auto-generated catch block
				ret = 0;
				if (!(e instanceof DatabaseNotConnectedException))
					e.printStackTrace();
			}
		} else {
			// INFO Update Existing Employee
			try {
				if (super
						.executeUpdate(
								"update empm set empname=?, empadd=?, empmo=?, joindate=?, desgn=?, empsal=?, acbal=?, empenabled=?, emppic=? where empid=?",
								data.empname, data.empadd, data.empmo,
								new java.sql.Date(data.joindate.getTime()),
								data.desgn, data.empsal, data.acbal,
								data.empenabled, data.emppic, data.empid) <= 0)
					ret = 0;
				else
					ret = 1;
			} catch (SQLException | DatabaseNotConnectedException e) {
				// TODO Auto-generated catch block
				ret = 0;
				if (!(e instanceof DatabaseNotConnectedException))
					e.printStackTrace();
				else
					Statics.handleException(e);
			}
		}
		return ret;
	}

	public EmployeeM_Data getLasteEmployee() {
		EmployeeM_Data data = null;
		try {
			super.executeQuery("select * from empm where empid=(select max(empid) from empm)");
			if (resultset.next()) {
				data = new EmployeeM_Data();
				initData(data, resultset);
			} else
				data = null;
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	public EmployeeM_Data getEmployeeByName(String empname) {
		EmployeeM_Data data = null;
		try {
			super.executeQuery("select * from empm where empname=?", empname);
			if (resultset.next()) {
				data = new EmployeeM_Data();
				initData(data, resultset);
			} else
				data = null;
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	public EmployeeM_Data getEmployeeByMobileNumber(String number) {
		EmployeeM_Data data = null;
		try {
			super.executeQuery("select * from empm where empmo=?", number);
			if (resultset.next()) {
				data = new EmployeeM_Data();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		}
		return data;
	}

	public EmployeeM_Data getEmployeeById(long empid) {
		EmployeeM_Data data = null;
		try {
			super.executeQuery("select * from empm where empid=?", empid);
			if (resultset.next()) {
				data = new EmployeeM_Data();
				this.initData(data, resultset);
			} else
				data = null;

		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			if (!(e instanceof DatabaseNotConnectedException))
				Statics.handleException(e);
			else
				e.printStackTrace();
		}
		return data;
	}

	private void initData(EmployeeM_Data data, ResultSet res) {
		try {
			data.empid = res.getLong("empid");
			data.empname = res.getString("empname");
			data.empadd = res.getString("empadd");
			data.empmo = res.getString("empmo");
			if (res.getDate("joindate") != null)
				data.joindate = new java.util.Date(res.getDate("joindate")
						.getTime());
			else
				data.joindate = null;
			data.acbal = res.getInt("acbal");
			data.remsalary = res.getInt("remsalary");
			if (res.getDate("leavedate") != null)
				data.leavedate = new java.util.Date(res.getDate("leavedate")
						.getTime());
			else
				data.leavedate = null;
			data.desgn = res.getString("desgn");
			data.empsal = res.getInt("empsal");
			data.empenabled = res.getShort("empenabled");
			data.emppic = res.getString("emppic");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
	}

	public int settleAccount(long empid, java.util.Date leavedate) {
		int ret = 0;
		try {
			ret = super.executeUpdate("update empm set acbal=0, remsalary=0,"
					+ "leavedate=?, empenabled=0 where empid=?",
					new java.sql.Date(leavedate.getTime()), empid);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return ret;
	}
}