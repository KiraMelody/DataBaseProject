package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
public class RestBean {
		public static JSONArray RestAll() throws ClassNotFoundException, JSONException
		{
			try{
				String sql = "select * from restaurant";
				return DBOperateTool.query(sql);
				}
			catch(SQLException e)
			{
				e.printStackTrace(System.err);
			}
			return null; 
			
		}
		
		public static Restaurant getRest(String rest_id) throws ClassNotFoundException
		{
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBControl.connect();
				st = conn.createStatement();
				String sql = "select * from restaurant where rid = '" + rest_id + "'";
				rs = st.executeQuery(sql);
				Restaurant R = new Restaurant(rest_id);
				if (rs.next())
				{
					R.setRestName(rs.getString(2));
					R.setProprietor(rs.getString(3));
					R.setTel(rs.getString(4));
					R.setAddress(rs.getString(5));
					R.setIntroduction(rs.getString(6));
					R.setProfit(rs.getDouble(7));
				}
				return R;
			}
			catch(SQLException e){
				e.printStackTrace(System.err);
				Restaurant r = new Restaurant(rest_id);
				return r;
			} 
			catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Restaurant r = new Restaurant(rest_id);
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
		
		public static double RestQueryProfit(String rest_id,String begin,String end) throws ClassNotFoundException
		{
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBControl.connect();
				st = conn.createStatement();
				String sql = "select sum(total) from order where rid = '" + rest_id + "'" + " and odatetime > '" + begin + "' and odatetime < '" + end + "'";
				rs = st.executeQuery(sql);
				double profit = rs.getDouble(1);
				return profit;
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
		
		public static JSONArray RestQueryHot(String rest_id,String begin,String end) throws ClassNotFoundException, JSONException
		{
			try{
				String sql = "select cid,count(*) from order,detail "
						+ "where order.oid = detail.oid and rid = '" + rest_id + "'" + " and odatetime > '" + begin + "' and odatetime < '" + end + "'"
						+ "group by cid";
				return DBOperateTool.query(sql);
			}
			catch(SQLException e){
				e.printStackTrace(System.err);
			} 
			return null;
		}
}

