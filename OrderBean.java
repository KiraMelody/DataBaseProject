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
	
	public static String addOrder(String uid,String rid,String time,double total) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			String sql = "select max(oid) from order";
			rs = st.executeQuery(sql);
			int number=0;
			if(rs.next())
			{
				number = rs.getInt(1);
			}
			else
				number = 0;
			String str=String.valueOf(number+1);
			System.out.println(total);
			sql = "insert into Order(oid,uid,rid,odatetime,ostate,total,cost)" + "values('" + str + "','" + uid +
			"','" + rid + "','" + time + "','pending'," + total +"," + total + ")";
			st.executeUpdate(sql);
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
	public static void updateOrder(String oid,String state) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "update Order set ostate = '" + state + "' where oid = '" + oid + "'";
			DBOperateTool.update(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		} 
		
	}
	public static JSONArray OrderAllforUser(String user_id) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select distinct order.rid,rname,order.oid,order.odatetime,ostate,total,user.uid,user.username as oconsumername,user.tel as oconsumertel,user.address as oconsumeraddr "
					+ "from user,order,restaurant "
					+ "where order.uid = user.uid and order.rid = restaurant.rid "
					+ "and order.uid = '" + user_id + "' order by odatetime desc";
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
			String sql = "select distinct order.rid,rname,order.oid,order.odatetime,ostate,total,user.uid,user.username as oconsumername,user.tel as oconsumertel,user.address as oconsumeraddr "
					+ "from user,order,restaurant "
					+ "where order.uid = user.uid and order.rid = restaurant.rid and "
					+ "order.rid = '" + rest_id + "' order by odatetime desc";
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
			String sql = "select distinct order.rid,rname,order.oid,order.odatetime,ostate,total,user.uid,user.username as oconsumername,user.tel as oconsumertel,user.address as oconsumeraddr,deliverer.deliverername as odeliverername,deliverer.deliverertel as odeliverertel,delivery.fee as odelivererfee,delivery.arrivaltime as oarrivaltime "
					+ "from user,order,restaurant,delivery,deliverer "
					+ "where order.uid = user.uid and order.rid = restaurant.rid and order.oid = delivery.oid and "
					+ "delivery.delivererid = deliverer.delivererid and delivery.delivererid = '" + deliverer_id + "' order by odatetime desc";
			return DBOperateTool.query(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		return null;
	}
	public static JSONArray addOrderDelivery(String rest_id) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select distinct order.oid,deliverername,deliverertel,delivery.arrivaltime,delivery.fee "
					+ "from order,delivery,deliverer "
					+ "where order.oid = delivery.oid and delivery.delivererid = deliverer.delivererid and "
					+ "order.rid = '" + rest_id + "' order by order.oid desc";
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