package com.ibm.crl.util;

import java.sql.*;

import javax.naming.NamingException;

public class LoginBean {
	public static Userinfo logon(String username,String password) throws ClassNotFoundException, NamingException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			//boolean exists = false;
			//if (user.getUserrole()=="user")
			String sql = "select * from account where name = '" + username + "' and password = '" + password + 
					"'";
				rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println("µÇÂ½³É¹¦");
					Userinfo user = new Userinfo();
					user.setUserID(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setTel(rs.getString(4));
					user.setAddress(rs.getString(5));
					user.setType(rs.getString(6));
					return user;
				}
				
			}catch(SQLException e)
			{
				e.printStackTrace(System.err);
			}
			finally
			{
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
