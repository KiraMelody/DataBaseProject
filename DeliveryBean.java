package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;

public class DeliveryBean {
	public static JSONArray DeliveryAll() throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select * from delivery";
			return DBOperateTool.query(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		}
		return null; 
		
	}
	public static void setDelivery(String oid,String did,double fee) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "insert into Delivery(oid,delivererid,fee)" + "values('" + oid + "','" + did +
					"','" + fee + "' )";
			DBOperateTool.add(sql);
			OrderBean.updateOrder(oid, "delivering");
			DBOperateTool.update(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		} 
		
	}
	public static void setArrivalTime(String oid,String time) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "update delivery set arrivaltime = '" + time + "' where oid = '" + oid + "'";
			DBOperateTool.update(sql);
			OrderBean.updateOrder(oid, "finished");
			DBOperateTool.update(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		} 
		
	}
	public static Delivery getDelivery(String order_id) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			String sql = "select oid,fee,arrivaltime,delivererid from delivery where oid = '" + order_id + "'";
			rs = st.executeQuery(sql);
			Delivery R = new Delivery(order_id);
			if (rs.next())
			{
				R.setFee(rs.getDouble(2));
				R.setArrivalTime(rs.getString(3));
				R.setDelivererId(rs.getString(4));
				return R;
			}
			else return null;
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			SQLException te = null;
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					if (te == null) {
						te = e;
					}
				}
			}
			
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					te = e;
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					if (te == null) {
						te = e;
					}
				}
			}

		}
		return null;
	}
}
