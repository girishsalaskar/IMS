package com.ims.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ims.misc.Statics;
import com.ims.models.data.RouteTripData;
import com.jdev.girish.database.exceptions.DatabaseNotConnectedException;

public class RouteTrip extends IMSDB {

	public int makeSentEntry(RouteTripData data) {
		int ret = 0;
		try {
			super.setAutoCommit(false);
			ret = super.executeUpdate(
					"insert into route_trip(routeid, vehicleno, empdriver,"
							+ "cratesend, tripdate) values(?,?,?,?,?)",
					data.routeid, data.vehicleno, data.empdriver,
					data.cratesend, new java.sql.Date(data.tripdate.getTime()));

			super.executeQuery("select astvalue from assets where astname='Crate'");
			if (resultset.next()) {
				int qty = Integer.parseInt(resultset.getString("astvalue"));
				qty -= data.cratesend;
				super.executeUpdate(
						"update assets set astvalue=? where astname='Crate'",
						qty + "");
			}
			super.commit();
			super.setAutoCommit(true);
		} catch (SQLException | DatabaseNotConnectedException e) {
			// TODO
			Statics.handleException(e);
		}
		return ret;
	}

	// public RouteTripData[] getRouteWiseNRTrip(int routeid) {
	// RouteTripData[] data=null;
	// try {
	// super.executeQuery("select * from route_trip where routeid=? and cratereceived is null",
	// routeid);
	// if(resultset.next())
	// {
	// resultset.beforeFirst();
	// data=new RouteTripData[super.getRowCount()];
	// for(int i=0;resultset.next();i++)
	// {
	// data[i]=new RouteTripData();
	// initData(data[i], resultset);
	// }
	// }
	// } catch (DatabaseNotConnectedException | SQLException |
	// EmptyResultSetException e) {
	// // TODO Auto-generated catch block
	// Statics.handleException(e);
	// }
	// return data;
	// }

	public RouteTripData getRouteWiseNRTrip(int routeid) {
		RouteTripData data = null;
		try {
			super.executeQuery(
					"select * from route_trip where routeid=? and cratereceived is null",
					routeid);
			if (resultset.next()) {
				data = new RouteTripData();
				initData(data, resultset);
			}
		} catch (DatabaseNotConnectedException | SQLException e) {
			// TODO Auto-generated catch block
			Statics.handleException(e);
		}
		return data;
	}

	private void initData(RouteTripData data, ResultSet res)
			throws SQLException {
		data.tripid = res.getLong("tripid");
		data.routeid = res.getInt("routeid");
		data.vehicleno = res.getString("vehicleno");
		data.empdriver = res.getString("empdriver");
		data.cratesend = res.getInt("cratesend");
		data.cratereceived = res.getInt("cratereceived");
		data.tripdate = new java.util.Date(res.getDate("tripdate").getTime());
	}
}