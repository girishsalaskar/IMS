package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.ProductsData;
import com.ims.models.data.Sales_MData;
import com.ims.models.data.Sales_SData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class Sales_M extends IMSDB {

	public int makeSalesEntry(Sales_MData salem, Sales_SData[] sales,
			float newdr) {
		int ret = 0;
		try {
			super.setAutoCommit(false);
			ret += super
					.executeUpdate(
							"insert into sales_m(somid, custid, salesdate, subtotal, "
									+ "vat, discount, total, paidamt) values(?,?,?,?,?,?,?,?)",
							salem.somid, salem.custid, new java.sql.Date(
									salem.salesdate.getTime()), salem.subtotal,
							salem.vat, salem.discount, salem.total,
							salem.paidamt);

			ret += super.executeUpdate(
					"update customers set debtamt=? where custid=?", newdr,
					salem.custid);
			if (ret > 0) {
				super.executeQuery("select max(salemid) from sales_m");
				if (resultset.next()) {
					long salesmid = resultset.getLong("max(salemid)");
					for (int i = 0; i < sales.length; i++) {
						ret += super
								.executeUpdate(
										"insert into sales_s(salemid, prodid,"
												+ " prodqty, prodrate, totrate) values(?,?,?,?,?)",
										salesmid, sales[i].prodid,
										sales[i].prodqty, sales[i].prodrate,
										sales[i].totrate);
						Products products = new Products();
						ProductsData prod = products
								.getProductById(sales[i].prodid);
						prod.instock -= sales[i].prodqty;
						prod.salesrate = sales[i].prodrate;
						ret += super.executeUpdate(
								"update products set instock=?, salesrate=?"
										+ " where prodid=?", prod.instock,
								prod.salesrate, prod.prodid);
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

	public Sales_MData[] getSalesData(long custid, java.util.Date salesdate) {
		Sales_MData[] data = null;
		try {
			super.executeQuery(
					"select * from sales_m where custid=? and salesdate=?",
					custid, new java.sql.Date(salesdate.getTime()));
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new Sales_MData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new Sales_MData();
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

	private void initData(Sales_MData data, ResultSet res) throws SQLException {
		data.salemid = res.getLong("salemid");
		data.somid = (res.getObject("somid") != null ? res.getLong("somid")
				: null);
		data.custid = res.getLong("custid");
		data.salesdate = new java.util.Date(res.getDate("salesdate").getTime());
		data.subtotal = res.getFloat("subtotal");
		data.vat = res.getFloat("vat");
		data.discount = res.getFloat("discount");
		data.total = res.getFloat("total");
		data.paidamt = res.getFloat("paidamt");
	}
}