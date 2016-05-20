package com.ibm.crl.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;


public class DetailBean {
	public static JSONArray getDetail(String order_id) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "select * from detail where oid = '" + order_id + "'";
			return DBOperateTool.query(sql);
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} 
		return null;
	}
	
}
