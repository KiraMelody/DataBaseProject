package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;

public class OrderBean {
	public static void addDetail(String oid,String rid,String cid,int camount) throws ClassNotFoundException, SQLException, JSONException
	{
		String ccamount = String.valueOf(camount);
		String sql = "insert into detail(oid,rid,cid,camount)" + "values('" + oid + "','" + rid + "','" + cid + "'," + ccamount + ")";
		ResultSet rs = (ResultSet) DBOperateTool.query(sql); 
	}
	public static String addOrder(String uid,String rid,String time,double total) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			//System.out.println("ok");
			st = conn.createStatement();
			String sql = "select count(*) from order";
			rs = st.executeQuery(sql);
			int number=0;
			while(rs.next())
			{
				number = rs.getInt(1);
			}
			String str=String.valueOf(number+1);
			String ttotal=String.valueOf(total);
			//user.setUser_id(str);
			sql = "insert into Order(oid,uid,rid,odatetime,ostate,total,cost)" + "values('" + str + "','" + uid +
			"','" + rid + "','" + time + "','notdone'," + ttotal +"," + ttotal + ")";
			int nResult = st.executeUpdate(sql);
			return str;
		}
		catch(SQLException e){
			//System.out("error");
			e.printStackTrace(System.err);
		} catch (NamingException e) {
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
		return "error";
	}
	public static JSONArray OrderAllforUser(String user_id) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select * from order where uid = '" + user_id + "' order by odatetime desc";
			System.out.println(DBOperateTool.query(sql).toString());
			return DBOperateTool.query(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		return null;
	}
	
	public static JSONArray OrderAllforRest(String rest_id) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select * from order where rid = '" + rest_id + "' order by odatetime desc";
			return DBOperateTool.query(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		return null;
	}
	
	public static JSONArray OrderAllforDeliverer(String deliverer_id) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select order.oid,uid,odatetime,ostate,total from order,delivery where delivererid = '" + deliverer_id + "' and order.oid = delivery.oid order by odatetime desc";
			return DBOperateTool.query(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		return null;
	}
	
	public static Order getOrder(String order_id) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			String sql = "select oid,uid,rid,odatetime,ostate,total from order where oid = '" + order_id + "'";
			rs = st.executeQuery(sql);
			Order O = new Order(order_id);
			if (rs.next())
			{
				O.setUserId(rs.getString(2));
				O.setRestId(rs.getString(3));
				O.setOrderTime(rs.getString(4));
				O.setState(rs.getString(5));
				O.setTotal(rs.getDouble(6));
				O.setCost(rs.getDouble(7));
				return O;
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
