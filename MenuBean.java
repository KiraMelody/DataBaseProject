package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuBean {
	public static JSONArray MakeCuisine () throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select detail.oid,menu.cid,menu.rid,cname,cdesc,cprice,camount from menu,detail where menu.cid = detail.cid and detail.rid = menu.rid group by detail.oid,menu.cid,menu.rid,menu.rid,cname,cdesc,cprice,camount";
			return DBOperateTool.query(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		}
		return null; 
		}
	public static JSONArray getMenuforRest(String rest_id) throws ClassNotFoundException, JSONException
	{
		try{
			
			String sql = "select * from menu where rid = '" + rest_id + "'";
			return DBOperateTool.query(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		return null;
	}
	public static JSONArray getCuisine(String rest_id,String cuisine_id) throws ClassNotFoundException, JSONException
	{
		try{
			
			String sql = "select * from menu where rid = '" + rest_id + "' and cid = '" + cuisine_id + "'";
			return DBOperateTool.query(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		return null;
	}
	public static void deleteCuisine(String rest_id,String cuisine_id) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "delete from menu where rid = '" + rest_id + "' and cid = '" + cuisine_id + "'";
			DBOperateTool.delete(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
	}
	public static String setCuisine(String rid,String cname,String cdesc,double cprice) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select max(cid) as cnt from menu where rid ='" + rid + "'";
			JSONArray arr = DBOperateTool.query(sql);
			int number = 10000000;
			JSONObject o = arr.getJSONObject(0);
			if (!o.isNull("cnt"))number = o.getInt("cnt");
			String cid = String.valueOf(number+1);
			sql = "insert into menu(cid,cname,rid,cdesc,cprice) values('" + cid + "','" + cname + "','" + rid + "','" + cdesc + "'," + cprice + ")";
			DBOperateTool.add(sql);
			return cid;
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		return null;
	}
	public static void updateCuisine(String rid,String cid,String cname,String cdesc,double cprice) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "update menu set cname = '" + cname + "', cdesc = '" + cdesc + "', cprice = " + cprice + "where rid = '" + rid + "' and cid = '" + cid + "'";
			DBOperateTool.update(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
	}
}
