package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.ProductsData;
import com.ims.models.data.Purchases_MData;
import com.ims.models.data.Purchases_SData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class Purchases_M extends IMSDB {

	public int makePurchaseEntry(Purchases_MData mdata,
			Purchases_SData[] proddata, float newcr) {
		int ret = 0;
		try {
			super.setAutoCommit(false);
			ret = super.executeUpdate(
					"insert into purchases_m(pomid, supid, purchasedate,"
							+ "subtotal, vat, discount, total, paidamt) "
							+ "values(?,?,?,?,?,?,?,?)", mdata.pomid,
					mdata.supid,
					new java.sql.Date(mdata.purchasedate.getTime()),
					mdata.subtotal, mdata.vat, mdata.discount, mdata.total,
					mdata.paidamt);

			ret += super.executeUpdate(
					"update suppliers set supcr=? where supid=?", newcr,
					mdata.supid);

			if (ret > 0) {
				super.executeQuery("select max(purmid) from purchases_m");
				if (resultset.next()) {
					long purmid = resultset.getLong("max(purmid)");
					for (int i = 0; i < proddata.length; i++) {
						ret += super
								.executeUpdate(
										"insert into purchases_s(purmid, prodid, prodqty,"
												+ "purchrate, salesrate) values(?,?,?,?,?)",
										purmid, proddata[i].prodid,
										proddata[i].prodqty,
										proddata[i].purchrate,
										proddata[i].salesrate);

						Products products = new Products();
						ProductsData prod = products
								.getProductById(proddata[i].prodid);
						prod.instock += proddata[i].prodqty;
						prod.purchrate = proddata[i].purchrate;
						prod.salesrate = proddata[i].salesrate;
						ret += super.executeUpdate(
								"update products set instock=?, purchrate=?,"
										+ " salesrate=? where prodid=?",
								prod.instock, prod.purchrate, prod.salesrate,
								prod.prodid);
					}
				}
			}
			super.commit();
			super.setAutoCommit(true);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return ret;
	}

	public Purchases_MData[] getPurchasesData(long supid,
			java.util.Date purchdate) {
		Purchases_MData[] data = null;
		try {
			super.executeQuery(
					"select * from purchases_m where supid=? and purchasedate=?",
					supid, new java.sql.Date(purchdate.getTime()));
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new Purchases_MData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new Purchases_MData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	private void initData(Purchases_MData data, ResultSet res)
			throws SQLException {
		data.purmid = res.getLong("purmid");
		data.pomid = res.getObject("pomid") != null ? res.getLong("pomid")
				: null;
		data.supid = res.getLong("supid");
		data.purchasedate = new java.util.Date(res.getDate("purchasedate")
				.getTime());
		data.subtotal = res.getFloat("subtotal");
		data.vat = res.getFloat("vat");
		data.discount = res.getFloat("discount");
		data.total = res.getFloat("total");
		data.paidamt = res.getFloat("paidamt");
	}
}