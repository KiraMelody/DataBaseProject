package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;

public class MenuBean {
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
}
