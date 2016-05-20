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
	public static JSONArray MakeCuisine (String order_id) throws ClassNotFoundException, JSONException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		JSONArray arr = new JSONArray();
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			System.out.println(order_id);
			String sql = "select menu.cid,cname,cdesc,cprice,camount from menu,detail where detail.oid = '" + order_id + "' and menu.cid = detail.cid and detail.rid = menu.rid";
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
		for (int i = 0; i < arr.length(); i++) 
		{
			JSONObject o = arr.getJSONObject(i);
			User u = UserBean.getUser(o.get("uid").toString());
			Delivery d = DeliveryBean.getDelivery (o.get("oid").toString());
			JSONArray carr = makeJsonArray.MakeCuisine(o.get("oid").toString());
			o.put("ocontent", carr);
			o.put("oconsumername", u.getName());
			o.put("oconsumertel", u.getTel());
			o.put("oconsumeraddr", u.getAddress());
			//System.out.println(d.getFee());
			//o.put("odelivererfee", d.getFee());
			ans.put(o);
		}
		return ans;
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
