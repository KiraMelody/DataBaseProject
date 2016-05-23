package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class makeJsonArray {
	
	public static JSONArray MakeMenu (String rest_id) throws ClassNotFoundException, JSONException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		JSONArray arr = new JSONArray();
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			String sql = "select distinct menu.cid,cname,cdesc,cprice from menu where rid = '" + rest_id + "'";
			rs = st.executeQuery(sql);
			arr = RS2JS(rs);
			return arr;
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
		return arr;
		}
	
	public static JSONArray MakeOrder (JSONArray arr) throws ClassNotFoundException, JSONException
	{
		JSONArray ans = new JSONArray();
		JSONArray carr = MenuBean.MakeCuisine();
		if (arr == null)
		{
			return null;
		}
		for (int i = 0; i < arr.length(); i++) 
		{
			JSONObject o = arr.getJSONObject(i);
			JSONArray oarr = new JSONArray();
			for (int j = 0; j < carr.length();j++)
			{
				JSONObject t = carr.getJSONObject(j);
				String oid = t.getString("oid");
				if (oid.equals(o.get("oid").toString()))
					oarr.put(t);
			}
			o.put("ocontent", oarr);
			ans.put(o);
		}
		System.out.println(ans.toString());
		return ans;
	}
	public static JSONArray MakeQuery (JSONArray arr,String rest_id) throws ClassNotFoundException, JSONException, SQLException, NamingException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			JSONArray ans = new JSONArray();
			for (int i = 0; i < arr.length(); i++) 
			{
				JSONObject o = arr.getJSONObject(i);
				String cid = o.get("cid").toString();
				System.out.println(cid + " " + rest_id);
				String sql = "select distinct cname,cdesc,cprice from menu where cid = '" + cid + "' and rid = '" + rest_id + "'";
				rs = st.executeQuery(sql);
				if (rs.next())
				{
				o.put("cname", rs.getString(1));
				o.put("cdesc", rs.getString(2));
				o.put("cprice", rs.getDouble(3));
				}
				ans.put(o);
			}
			return ans;
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
		public static JSONArray RS2JS(ResultSet rs) throws SQLException,JSONException  
		{   
			JSONArray array = new JSONArray();   
			ResultSetMetaData metaData = rs.getMetaData();  
			int columnCount = metaData.getColumnCount();   
			while (rs.next()) 
			{  
				JSONObject jsonObj = new JSONObject();    
				for (int i = 1; i <= columnCount; i++) 
				{  
	            String columnName =metaData.getColumnLabel(i);  
	            String value = rs.getString(columnName);  
	            jsonObj.put(columnName, value);  
				}   
				array.put(jsonObj);   
			}  
			return array;  
		}  
		
}
