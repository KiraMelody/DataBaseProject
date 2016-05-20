package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;
public class AdministratorBean {
		public Vector AdminQuery(Administrator user) throws ClassNotFoundException
		{
			Vector vt = new Vector();
			if (user==null) return vt;
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				conn = DBControl.connect();
				System.out.println("ok");
				st = conn.createStatement();
				/*boolean exists = false;
				String sql = "select * from admin where admin_id='" + user.getId() + "'";
				rs = st.executeQuery(sql);*/
				String sql = "select rid,sum(cost) from order where odatetime >" + user.getBegin() + " and odatetime < " + user.getEnd() + "group by rid ordered by sum(cost) desc";
				rs = st.executeQuery(sql);
				while(rs.next())
				{
					Restaurant R = new Restaurant(rs.getString(1));
					R.setProfit(rs.getDouble(2));
					vt.add(R);
				}
				return vt;
			}
			catch(SQLException e){
				e.printStackTrace(System.err);
				return vt;
			} 
			catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return vt;
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

}
