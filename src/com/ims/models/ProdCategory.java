package com.ims.models;

import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.ProdCategoryData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class ProdCategory extends IMSDB {

	public ProdCategoryData[] getAllProductCategories() {
		ProdCategoryData[] data = null;
		try {
			super.executeQuery("select * from prodcategory order by catid");
			if (super.getRowCount() > 0) {
				data = new ProdCategoryData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new ProdCategoryData(resultset.getLong("catid"),
							resultset.getString("category"));
				}
				return data;
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			Statics.handleException(e);
		} catch (EmptyResultSetException e) {
			data = null;
		}
		return data;
	}

	public int updateCategory(ProdCategoryData data, boolean updateflag) {
		try {
			super.executeQuery(
					"select category from prodcategory where category like ?",
					data.catname);
			if (resultset.next()) {
				Statics.showMessage(null,
						"Category '" + resultset.getString("category")
								+ "' already exists!",
						javax.swing.JOptionPane.ERROR_MESSAGE);
				return 0;
			}
		} catch (DatabaseNotConnectedException | SQLException e1) {
			// TODO Auto-generated catch block
			Statics.handleException(e1);
		}
		if (updateflag) {// INFO Update Category
			try {
				return super.executeUpdate(
						"update prodcategory set category=? where catid=?",
						data.catname, data.catid);
			} catch (SQLException | DatabaseNotConnectedException e) {
				// TODO Auto-generated catch block
				Statics.handleException(e);
			}
		} else {// INFO Insert Category
			try {
				return super.executeUpdate(
						"insert into prodcategory(category) values(?)",
						data.catname);
			} catch (SQLException | DatabaseNotConnectedException e) {
				// TODO Auto-generated catch block
				Statics.handleException(e);
			}
		}
		return 0;
	}

	public String getProductCategoryByID(long catid) {
		String name = "";
		try {
			super.executeQuery(
					"select category from prodcategory where catid=?", catid);
			if (resultset.next()) {
				name = resultset.getString("category");
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return name;
	}

	public long getProductCategoryIdByName(String name) {
		long id = 0;
		try {
			super.executeQuery(
					"select catid from prodcategory where category=?", name);
			if (resultset.next()) {
				id = resultset.getLong("catid");
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return id;
	}
}