package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.AssetsData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class Assets extends IMSDB {

	// public int updateAsset(AssetsData data)
	// {
	// int ret=0;
	// //long assetid=getAssetIdByName(data.assetname);
	// AssetsData oldData=getAssetByName(data.assetname);
	// try
	// {
	// if(oldData==null)
	// {
	// super.executeQuery("select * from assets where assetname=?",
	// data.assetname);
	// if(resultset.next())
	// {
	// //INFO Alread Exist;
	// ret=-2;
	// return ret;
	// }
	// super.executeUpdate("insert into assets(assetname, assetqty, assetamt)"
	// + "values(?, ?, ?)", data.assetname, data.assetqty, data.assetamt);
	// ret=1;
	// }
	// else
	// {
	// data.assetid=oldData.assetid;
	// data.assetqty=data.assetqty+oldData.assetqty;
	// data.assetamt=data.assetamt+oldData.assetamt;
	// super.executeUpdate("update assets set assetname=?, assetqty=?, assetamt=?"
	// + " where assetid=?",data.assetname, data.assetqty,
	// data.assetamt, data.assetid);
	// ret=1;
	// }
	// }
	// catch(SQLException | DatabaseNotConnectedException e)
	// {
	// //TODO Catch Block
	// Statics.handleException(e);
	// ret=-1;
	// }
	// return ret;
	// }

	public AssetsData[] getAllAssets() {
		AssetsData[] data = null;
		try {
			super.executeQuery("select * from assets");
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new AssetsData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new AssetsData();
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

	public int updateAsset(AssetsData data) {
		int ret = 0;
		try {
			if (!data.astname.equalsIgnoreCase("crate")) {
				if (data.assetid == 0)
					ret = super.executeUpdate(
							"insert into assets(astname, astvalue)"
									+ "values(?,?)", data.astname,
							data.astvalue);
				else
					ret = super
							.executeUpdate(
									"update assets set astname=?, astvalue=?, assetid=?",
									data.astname, data.astvalue, data.assetid);
			} else {
				AssetsData crate = getAssetByName("crate");
				if (crate == null) {
					ret = super.executeUpdate(
							"insert into assets(astname, astvalue)"
									+ "values(?,?)", data.astname,
							data.astvalue);
				} else {
					int qty = Integer.parseInt(crate.astvalue);
					// qty+=Integer.parseInt(data.astvalue);
					qty = Integer.parseInt(data.astvalue);
					ret = super
							.executeUpdate(
									"update assets set astname=?, astvalue=? where assetid=?",
									data.astname, qty + "", crate.assetid);
				}
			}
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO
			Statics.handleException(e);
		}
		return ret;
	}

	public AssetsData getAssetByName(String assetName) {
		AssetsData ret = null;
		try {
			super.executeQuery("select * from assets where astname=?",
					assetName);
			if (resultset.next()) {
				ret = new AssetsData();
				initData(ret, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
			ret = null;
		}
		return ret;
	}

	private void initData(AssetsData data, ResultSet res) throws SQLException {
		data.assetid = res.getInt("assetid");
		data.astname = res.getString("astname");
		data.astvalue = res.getString("astvalue");
	}

}