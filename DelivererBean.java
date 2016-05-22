package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;

public class DelivererBean {
	public static JSONArray DelivererAll() throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select * from deliverer";
			return DBOperateTool.query(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		}
		return null; 
		
	}
	public static Deliverer getDeliverer(String deliverer_id) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			String sql = "select delivererid,deliverername,deliverertel from deliverer where delivererid = '" + deliverer_id + "'";
			rs = st.executeQuery(sql);
			Deliverer R = new Deliverer(deliverer_id);
			if (rs.next())
			{
				R.setDelivererName(rs.getString(2));
				R.setTel(rs.getString(3));
			}
			return R;
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
	
	public static double DelivererQueryFee(String deliverer_id,String begin,String end) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			//System.out.println(deliverer_id);
			String sql = "select sum(fee) as sum from delivery where delivererid = '" + deliverer_id + "'" + " and arrivaltime > '" + begin + "' and arrivaltime < '" + end + "'";
			rs = st.executeQuery(sql);
			double fee = 0;
			if (rs.next())
				fee = rs.getDouble(1);
			System.out.println(fee);
			return fee;
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
			return 0;
		} 
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
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
	}
}
