package com.ims.models;

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
}