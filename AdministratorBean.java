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
			String sql = "select distinct user.uid,uname,uaddr,utel,count(order.oid) as uorderamount,sum(order.total) as uconsumption from user,order where order.uid = user.uid and ostatetime > '" + begin + "' amd pstatetome < '" + end + "' group by user.uid";
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
			String sql = "select count(order.oid) as totalorderamount,sum(order.total) as totalrevenue from order where ostatetime > '" + begin + "' and ostatetome < '" + end + "' group by user.uid";
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
			String sql = "select distinct restaurant.rname,rdesc,cname "
					+ "from restaurant,detail,menu,( "
						+"	select max(cnum) as cmax, rid"
						+"	from (select count(*) as cnum, cid, detail.rid from detail,order "
							+	"where order.oid = detail.oid and order.odatetime > '"+ begin + "' and order.odatetime < '" + end + "' "
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
	public static void updateRestaurant(String rid,String rname,String rtel,String raddr,String rdesc) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "update restaurant set rname = '" + rname + "', rdesc = '" + rdesc + "', rtel = " + rtel + "', raddr = " + raddr + "' where rid = '" + rid + "'";
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
			String sql = "delete from restaurant where rid = '" + rid + "'";
			DBOperateTool.delete(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
	}

	public static String createRest(String rname,String rtel,String raddr,String rdesc) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select count(*) as cnt from restaurant";
			JSONArray arr = DBOperateTool.query(sql);
			int number = arr.getJSONObject(0).getInt("cnt");
			String rid=String.valueOf(number+1);
			sql = "insert into restaurant(rid,rname,rtel,raddr,rdesc) values('" + rid + "','" + rname + "','" + rtel + "','" + raddr + "'," + rdesc + ")";
			DBOperateTool.add(sql);
			return rid;
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		}
		return null;
	}

}
