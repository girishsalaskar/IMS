package com.ims.models;

import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.ExpensesData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class Expenses extends IMSDB {

	public int insertExpenses(ExpensesData data) {
		int ret = 0;
		try {
			ret = super.executeUpdate(
					"insert into expenses(expenses, expamt, expdate, expdesc)"
							+ " values(?,?,?,?)", data.expenses, data.expamt,
					new java.sql.Date(data.expdate.getTime()), data.expdesc);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
			ret = 0;
		}
		return ret;
	}
}