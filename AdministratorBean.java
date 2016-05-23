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
import org.json.JSONObject;
public class AdministratorBean {
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
	public static JSONArray UserAll(String begin,String end) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select user.uid,username as uname,address as uaddr,tel as utel,count(order.oid) as uorderamount,sum(order.total) as uconsumption from user,order where order.uid = user.uid and odatetime > '" + begin + "' and odatetime < '" + end + "' group by user.uid,uname,utel,uaddr order by user.uid";
			return DBOperateTool.query(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		}
		return null; 
		
	}
	public static JSONObject QueryAll(String begin,String end) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select count(order.oid) as totalorderamount,sum(order.total) as totalrevenue from order where odatetime > '" + begin + "' and odatetime < '" + end + "' group by order.uid";
			JSONObject o = DBOperateTool.query(sql).getJSONObject(0);
			return o;
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		}
		return null; 
		
	}
	public static JSONArray RestQuery(String begin,String end) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select distinct restaurant.rid,restaurant.rname,rdesc,cname as rmostpopularcuisine "
					+ "from restaurant,detail,menu,( "
						+ "	select max(cnum) as cmax, rid"
						+ "	from (select count(*) as cnum, cid, detail.rid from detail,order "
							+ "where order.oid = detail.oid and order.odatetime > '" + begin + "' and order.odatetime < '" + end + "' "
									+ "group by cid, detail.rid "
								+ "	) as T group by rid) as S,(select count(*) as cnum, cid, detail.rid from detail,order "
								+ "	where order.oid = detail.oid and order.odatetime > '" + begin  + "' and order.odatetime < '" + end + "' "
								+ "	group by cid, detail.rid "
								+ "	) as TT "
					+ "where restaurant.rid = detail.rid and restaurant.rid = menu.rid and detail .cid = menu.cid and S.rid = restaurant.rid and "
					+ " TT.rid = restaurant.rid and TT.cnum = S.cmax and TT.cid = detail.cid; ";
			return DBOperateTool.query(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		}
		return null; 
	}
	public static JSONArray addRestQuery(String begin,String end) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select order.rid,count(order.oid) as rorderamount,sum(order.total) as rrevenue "
					+ "from order where order.odatetime > '"+ begin + "' and order.odatetime < '" + end + "' group by order.rid order by order.rid";				
			return DBOperateTool.query(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		}
		return null; 
	}
	public static JSONArray OrderQuery(String begin,String end) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select order.oid,restaurant.rid as orestid, restaurant.rname as orestname,restaurant.address as orestaddr,restaurant.tel as oresttel, "
					+ "order.odatetime,delivery.arrivaltime as ofinishtime,order.ostate,user.username as oconsumername,user.tel as oconsumertel,user.address as oconsumeraddr, "
					+ "deliverer.deliverername as odeliverername,deliverer.deliverertel as odeliverertel,delivery.fee as odelivererfee "
					+ "from restaurant,order,user,delivery,deliverer "
							+ " where order.rid = restaurant.rid and "
							+ " order.odatetime > '"+ begin + "' and order.odatetime < '" + end + "' "
									+ "and order.oid = delivery.oid and order.uid = user.uid and delivery.delivererid = deliverer.delivererid "
									+ "order by order.oid";
			return DBOperateTool.query(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		}
		return null; 
		
	}
	public static JSONArray DetailQuery(String begin,String end) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select order.oid,menu.cid,cname,cprice,cdesc,detail.camount "
					+ "from detail,order,menu "
							+	"where order.oid = detail.oid and order.odatetime > '"+ begin + "' and order.odatetime < '" + end + "' "
									+ "and detail.cid = menu.cid and detail.rid = menu.rid "
									+ "order by order.oid";
			return DBOperateTool.query(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		}
		return null; 
		
	}
	public static void updateRestaurant(String rid,String rname,String rtel,String raddr,String rdesc) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "update restaurant set rname = '" + rname + "', rdesc = '" + rdesc + "', tel = '" + rtel + "', address = '" + raddr + "' where rid = '" + rid + "'";
			DBOperateTool.update(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
	}
	public static void deleteRestaurant(String rid) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "delete from restaurant where rid = '" + rid + "'";
			DBOperateTool.delete(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
	}
	public static void setRestPassword(String rid,String pw) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "update account set password = '" + pw + "'" + " where uid = '" + rid + "'";
			DBOperateTool.delete(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
	}

	public static String createRest(String rname,String rtel,String raddr,String rdesc) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select max(rid) as cnt from restaurant";
			JSONArray arr = DBOperateTool.query(sql);
			int number = 0;
			if (arr.length()!=0)
				arr.getJSONObject(0).getInt("cnt");
			String rid=String.valueOf(number+1);
			sql = "insert into restaurant(rid,rname,tel,address,rdesc) values('" + rid + "','" + rname + "','" + rtel + "','" + raddr + "','" + rdesc + "')";
			DBOperateTool.add(sql);
			sql = "insert into account(uid,username,password,userrole) values('" + rid + "','" + rname + "','" + "123456" + "','" + "rest')";
			DBOperateTool.add(sql);
			return rid;
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		}
		return null;
	}

}
