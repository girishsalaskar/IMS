package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.RouteMData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;
import com.jdev.girish.database.exceptions.EmptyResultSetException;

public class RouteM extends IMSDB {

	public int updateRouteDetails(RouteMData data) {
		int ret = 0;
		try {
			if (data.routeid == -1) {
				ret = super.executeUpdate(
						"insert into route_m(routeno, routename, locations)"
								+ "values(?,?,?)", data.routeno,
						data.routename, data.locations);
			} else {
				ret = super.executeUpdate(
						"update route_m set routeno=?, routename=?, locations=?"
								+ "where routeid=?", data.routeno,
						data.routename, data.locations, data.routeid);
			}
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return ret;
	}

	public RouteMData[] getAllRouteDetails() {
		RouteMData[] data = null;
		try {
			super.executeQuery("select * from route_m");
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new RouteMData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new RouteMData();
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

	private void initData(RouteMData data, ResultSet res) throws SQLException {
		data.routeid = res.getInt("routeid");
		data.routeno = res.getInt("routeno");
		data.routename = res.getString("routename");
		data.locations = res.getString("locations");
	}

	public RouteMData[] getRoutesByRouteName(String text) {
		RouteMData[] data = null;
		try {
			super.executeQuery("select * from route_m where routename like ?",
					text + "%");
			if (resultset.next()) {
				resultset.beforeFirst();
				data = new RouteMData[super.getRowCount()];
				for (int i = 0; resultset.next(); i++) {
					data[i] = new RouteMData();
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

	public RouteMData getRouteByRouteNo(int routeno) {
		RouteMData data = null;
		try {
			super.executeQuery("select * from route_m where routeno=?", routeno);
			if (resultset.next()) {
				data = new RouteMData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}