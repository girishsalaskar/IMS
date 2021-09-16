package com.ims.models;

import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.CratePurchasesData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class CratePurchases extends IMSDB {

	public int cratePurchases(CratePurchasesData data) {
		int ret = 0;
		try {
			super.setAutoCommit(false);
			ret = super.executeUpdate(
					"insert into cratepurch(purchdate, crateqty, rate, amt)"
							+ "values(?, ?, ?, ?)", data.purchdate,
					data.crateqty, data.rate, data.amt);
			// TODO Add entry to assets

			super.commit();
			super.setAutoCommit(true);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
			ret = 0;
		}
		return ret;
	}
}