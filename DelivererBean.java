package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.naming.NamingException;

public class DelivererBean {
	public static Deliverer getDeliverer(String deliverer_id) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			String sql = "select * from deliverer where delivererid = '" + deliverer_id + "'";
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
			Deliverer r = new Deliverer(deliverer_id);
			return r;
		} 
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Deliverer r = new Deliverer(deliverer_id);
			return r;
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
	
	public static double DelivererQueryFee(String deliverer_id,String begin,String end) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			String sql = "select sum(fee) from delivery where delivererid = '" + deliverer_id + "'" + " and arrivaltime > '" + begin + "' and arrivaltime < '" + end + "'";
			rs = st.executeQuery(sql);
			double fee = rs.getDouble(1);
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
