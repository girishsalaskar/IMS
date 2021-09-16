package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.EmpPayment_Data;
import com.ims.models.data.EmployeeM_Data;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class EmpPayment extends IMSDB {

	public int makePaymentEntry(EmpPayment_Data data) {
		int ret = 0;
		try {
			super.executeQuery(
					"select * from emppayment where salyear=? and salmonth=? and empid=?",
					data.salyear, data.salmonth, data.empid);
			if (resultset.next())
				return -1;

			super.setAutoCommit(false);
			ret = super
					.executeUpdate(
							"insert into emppayment(empid, salmonth, salyear, workingdays, attenddays,"
									+ "empsalary, netsalary, deduct, advdeduct, paidamt, remamt, paydate)"
									+ " values(?,?,?,?,?,?,?,?,?,?,?,?)",
							data.empid, data.salmonth, data.salyear,
							data.workingdays, data.attenddays, data.empsalary,
							data.netsalary, data.deduct, data.advdeduct,
							data.paidamt, data.remamt, new java.sql.Date(
									data.paydate.getTime()));

			EmployeeM_Data edata = new EmployeeM().getEmployeeById(data.empid);
			int acbal = edata.acbal - data.advdeduct;

			super.executeUpdate(
					"update empm set acbal=?, remsalary=? where empid=?",
					acbal, data.remamt, data.empid);
			ret = 1;

			super.commit();
			super.setAutoCommit(true);

		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			ret = 0;
			Statics.handleException(e);
		}
		return ret;
	}
	
	public EmpPayment_Data getPaymentDetails(int salyear, int salmonth, long empid)
	{
		EmpPayment_Data data=null;
		try {
			super.executeQuery(
					"select * from emppayment where salyear=? and salmonth=? and empid=?",
					salyear, salmonth, empid);
			if (resultset.next())
			{
				data=new EmpPayment_Data();
				initData(data, resultset);
			}
		} catch (SQLException | DatabaseNotConnectedException e) {
			Statics.handleException(e);
		}
		return data;
	}

	private void initData(EmpPayment_Data data, ResultSet res) throws SQLException {
		data.payid=res.getLong("payid");
		data.empid=res.getLong("empid");
		data.salmonth=res.getInt("salmonth");
		data.salyear=res.getInt("salyear");
		data.workingdays=res.getInt("workingdays");
		data.attenddays=res.getInt("attenddays");
		data.empsalary=res.getInt("empsalary");
		data.netsalary=res.getInt("netsalary");
		data.deduct=res.getInt("deduct");
		data.advdeduct=res.getInt("advdeduct");
		data.paidamt=res.getInt("paidamt");
		data.remamt=res.getInt("remamt");
		data.paydate=new java.util.Date(res.getDate("paydate").getTime());
	}
}