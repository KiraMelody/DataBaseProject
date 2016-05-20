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
public class AdministratorBean {
		public JSONArray AdminQuery(String admin_id,String begin,String end) throws ClassNotFoundException, SQLException, JSONException 
		{
			JSONArray arr = new JSONArray();
			String sql = "select rid,count(*) end as count,sum(total) end as sum from order where odatetime >" + begin + " and odatetime < " + end + "group by rid order by sum(total) desc";
			arr = DBOperateTool.query(sql);
			return arr;
		}

}
