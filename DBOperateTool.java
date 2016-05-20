package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;

public class DBOperateTool {
	
	public static JSONArray query(String sql) throws SQLException, ClassNotFoundException, JSONException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = DBControl.connect();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			return makeJsonArray.RS2JS(rs);
		} catch (SQLException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					if (te == null) {
						te = e;
					}
				}
			}

			if (te != null) {
				throw te;
			}

		}
		return makeJsonArray.RS2JS(rs);

	}

	public static void add(String sql) throws SQLException, ClassNotFoundException {
		executeSQL(sql);

	}

	public static void delete(String sql) throws SQLException, ClassNotFoundException {
		executeSQL(sql);
	}

	public static void update(String sql) throws SQLException, ClassNotFoundException {
		executeSQL(sql);
	}

	private static void executeSQL(String sql) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement st = null;

		try {
			con = DBControl.connect();
			st = con.createStatement();
			st.execute(sql);

		} catch (SQLException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SQLException te = null;
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					te = e;
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					if (te == null) {
						te = e;
					}
				}
			}

			if (te != null) {
				throw te;
			}

		}
	}

}
