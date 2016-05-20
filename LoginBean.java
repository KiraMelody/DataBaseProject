package com.ibm.crl.util;

import java.sql.*;

import javax.naming.NamingException;

public class LoginBean {
	public static boolean islogon(String uid,String userrole) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			long nowtime = System.currentTimeMillis();
			String time = String.valueOf(nowtime);
			String sql = "select outtime from login where uid = '" + uid + "' and userrole = '" + userrole + "'";
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				String outtime = rs.getString(1);
				if (outtime.compareTo(time) > 0)
					return true;
			}
			sql = "delete from login where uid = '" + uid + "' and userrole = '" + userrole + "'";
			st.executeQuery(sql);
			return false;
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
		return false;
	}
	public static String login(String username,String password,String userrole,String time) throws ClassNotFoundException, NamingException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			//boolean exists = false;
			//if (user.getUserrole()=="user")
			String sql = "select uid from account where uname = '" + username + "' and password = '" + password + 
					"' and userrole = '" + userrole + "'";
				rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println("��½�ɹ�");
					String uid = rs.getString(1);
					/*Userinfo user = new Userinfo();
					user.setUserID(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setTel(rs.getString(4));
					user.setAddress(rs.getString(5));
					user.setType(rs.getString(6));*/
					sql = "insert into login(uid,uname,userrole,outtime) values('" + uid + "','" + username + "','" + userrole + "','" + time + "')";
					rs=st.executeQuery(sql);
					return uid;
				}
				return "error";
				
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
