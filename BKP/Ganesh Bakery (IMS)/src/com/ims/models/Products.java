package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.ProductsData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class Products extends IMSDB {

	public ProductsData getNameWiseProducts(String prodname) {
		ProductsData data = null;
		try {
			super.executeQuery("select * from products where prodname=?",
					prodname);
			if (resultset.next()) {
				data = new ProductsData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	public ProductsData getProductById(long prodid) {
		ProductsData data = null;
		try {
			super.executeQuery("select * from products where prodid=?", prodid);
			if (resultset.next()) {
				data = new ProductsData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	public ProductsData[] getCategoryWiseProducts(String category) {
		ProductsData[] data = null;
		try {
			super.executeQuery("select * from products where products.catid="
					+ "(select prodcategory.catid from prodcategory"
					+ " where prodcategory.category=?)", category);

			if (resultset.next()) {
				resultset.beforeFirst();
				data = new ProductsData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new ProductsData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException
				| EmptyResultSetException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	public int updateProduct(ProductsData data, boolean update) {
		int ret = 0;
		// TODO Purchase Rate and Sales Rate to be Updated
		if (update) {
			try {
				ret = super.executeUpdate(
						"update products set prodname=?, unitname=?, unitdesc=?,"
								+ " proddesc=?, catid=? where prodid=?",
						data.prodname, data.unitname, data.unitdesc,
						data.proddesc, data.catid, data.prodid);
			} catch (SQLException | DatabaseNotConnectedException e) {
				// TODO Auto-generated catch block
				ret = 0;
				Statics.handleException(e);
			}
		} else {
			try {
				super.executeQuery(
						"select prodname from products where prodname=?",
						data.prodname);
				if (resultset.next()) {
					Statics.showMessage(null,
							"Product alread exist in category!",
							javax.swing.JOptionPane.ERROR_MESSAGE);
					return 0;
				}
			} catch (DatabaseNotConnectedException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				ret = super
						.executeUpdate(
								"insert into products(prodname, unitname, unitdesc,"
										+ " proddesc, purchrate, salesrate, instock, catid) "
										+ "values(?, ?, ?, ?, ?, ?, ?, ?)",
								data.prodname, data.unitname, data.unitdesc,
								data.proddesc, 0, 0, 0, data.catid);
			} catch (SQLException | DatabaseNotConnectedException e) {
				// TODO Auto-generated catch block
				ret = 0;
				Statics.handleException(e);
			}
		}
		return ret;
	}

	public ProductsData[] getAllProducts() {
		ProductsData[] data = null;
		try {
			super.executeQuery("select * from products order by prodid");
			if (super.getRowCount() > 0) {
				data = new ProductsData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new ProductsData();
					initData(data[i], resultset);
				}
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			data = null;
		}
		return data;
	}

	private void initData(ProductsData data, ResultSet res) {
		try {
			data.prodid = res.getLong("prodid");
			data.prodname = res.getString("prodname");
			data.unitname = res.getString("unitname");
			data.unitdesc = res.getString("unitdesc");
			data.proddesc = res.getString("proddesc");
			data.purchrate = res.getFloat("purchrate");
			data.salesrate = res.getFloat("salesrate");
			data.instock = res.getInt("instock");
			data.catid = res.getLong("catid");
		} catch (SQLException e) {
			Statics.handleException(e);
		}
	}
}