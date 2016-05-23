package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.NamingException;

import org.json.JSONException;

public class UserBean {
	public static boolean updateUser(Registe user) throws ClassNotFoundException
	{
		if (user==null) return false;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			System.out.println("ok");
			st = conn.createStatement();
			boolean exists = false;
			String sql = "select * from user where username='" + user.getUsername() + "'";
			
			rs = st.executeQuery(sql);
			if(rs.next())
				exists=true;
			rs.close();
			if (exists) return false;
			sql = "select max(uid) from user";
			rs = st.executeQuery(sql);
			int number=0;
			if(rs.next())
			{
				number = rs.getInt(1);
			}
			else
				number = 10000000;
			String str=String.valueOf(number+1);
			user.setUser_id(str);
			sql = "insert into User(uid,username,tel,address)" + "values('" + user.getId() + "','" + user.getUsername() +
			"','" + user.getTel() + "','" + user.getAddress() + "' )";
			int nResult = st.executeUpdate(sql);
			sql = "insert into account(uid,username,password,userrole)" + "values('" + user.getId() + "','" + user.getUsername() +
					"','" + user.getPassword() + "','user')";
			st.executeUpdate(sql);
			return nResult == 1;
		}
		catch(SQLException e){
			//System.out("error");
			e.printStackTrace(System.err);
			return false;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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
	public static User getUser(String user_id) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = DBControl.connect();
			st = conn.createStatement();
			String sql = "select * from user where uid='" + user_id + "'";
			rs = st.executeQuery(sql);
			User U = new User(user_id);
			if (rs.next())
			{
				U.setUserName(rs.getString(2));
				U.setTel(rs.getString(3));
				U.setAddress(rs.getString(4));
			}
			return U;
		}
		catch(SQLException e){
			e.printStackTrace(System.err);
		} catch (NamingException e) {
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
		return null;
	}
	public static void updateUserInfo(String uid,String tel,String addr) throws ClassNotFoundException, JSONException
	{
		try{
			String sql = "update User set tel = '" + tel
					+ "' , address = '" + addr
					+ "' where uid = '" + uid + "'";
			DBOperateTool.update(sql);
			}
		catch(SQLException e)
		{
			e.printStackTrace(System.err);
		} 
		
	}
}
