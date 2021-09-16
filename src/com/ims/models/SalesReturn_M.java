package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.ProductsData;
import com.ims.models.data.SalesReturn_MData;
import com.ims.models.data.SalesReturn_SData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class SalesReturn_M extends IMSDB {

	public int makeSalesReturnEntry(SalesReturn_MData mdata,
			SalesReturn_SData[] sdata) {
		int ret = 0;
		try {
			super.setAutoCommit(false);
			ret += super
					.executeUpdate(
						"INSERT INTO `salesreturn_m`(`salesmid`, `custid`, `salesreturndate`, `total`, `returncharge`, `prevdr`, `returnamount`)"
									+ " VALUES(?,?,?,?,?,?,?)", mdata.salesmid,
							mdata.custid, mdata.salesreturndate, mdata.total,
							mdata.returncharge, mdata.prevdr,
							mdata.returnamount);

			ret += super.executeUpdate(
				"update customers set debtamt=? where custid=?", 0,
					mdata.custid);
			if (ret > 1) {
				super.executeQuery("select max(salereturnmid) from salesreturn_m");
				if (resultset.next()) {
					mdata.salesmid = resultset.getLong(1);
					for (int i = 0; i < sdata.length; i++) {
						int tret=0;
						tret = super
								.executeUpdate(
									"INSERT INTO `salesreturn_s`(`salemid`, `prodid`, `prodqty`, `prodrate`, `totrate`) "
												+ "VALUES(?,?,?,?,?)",
										mdata.salesmid, sdata[i].prodid,
										sdata[i].prodqty, sdata[i].prodrate,
										sdata[i].totrate);
						if(tret>0)
						{
							Products products = new Products();
							ProductsData prod = products
									.getProductById(sdata[i].prodid);
							prod.instock += sdata[i].prodqty;
							ret += super.executeUpdate(
									"update products set instock=? "
											+ " where prodid=?", prod.instock,
											prod.prodid);
						}
						ret+=tret;
					}
				}
			}
			super.commit();
			super.setAutoCommit(true);
		} catch (SQLException | DatabaseNotConnectedException e) {
			Statics.handleException(e);
		}
		return ret;
	}

	public SalesReturn_MData getSalesReturnData(long parseLong) {
		SalesReturn_MData mdata = null;
		try {
			super.executeQuery(
				"SELECT * FROM `salesreturn_m` WHERE `salesmid`=?",
					parseLong);
			if (resultset.next()) {
				mdata = new SalesReturn_MData();
				initData(mdata, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		}
		return mdata;
	}

	private void initData(SalesReturn_MData data, ResultSet res) throws SQLException {
		data.salereturnmid = res.getLong("salereturnmid");
		data.salesmid = res.getLong("salesmid");
		data.custid = res.getLong("custid");
		data.salesreturndate = new java.util.Date(res.getDate(
			"salesreturndate").getTime());
		data.total = res.getDouble("total");
		data.returncharge = res.getDouble("returncharge");
		data.prevdr = res.getDouble("prevdr");
		data.returnamount = res.getDouble("returnamount");

	}
}