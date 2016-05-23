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
	public static void addDetail(String oid,String rid,String cid,int camount) throws ClassNotFoundException, SQLException, JSONException
	{
		try{
			String ccamount = String.valueOf(camount);
			String sql = "insert into detail(oid,rid,cid,camount)" + "values('" + oid + "','" + rid + "','" + cid + "'," + ccamount + ")";
			DBOperateTool.add(sql); 
		}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		} 
		
	}
	
}
