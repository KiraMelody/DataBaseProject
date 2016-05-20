package com.ibm.crl.util;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class OrderSeverlet
 */
@WebServlet("/OrderSeverlet")
public class OrderSeverlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderSeverlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("err");
		try {
			JSONObject geT =  new JSONObject (request.getParameter("data"));
			String uid = geT.getString("uid");
			String rid = geT.getString("rid");
			double total = geT.getDouble("total");
			JSONArray cui = geT.getJSONArray("data");
			//response.getWriter().append(cui.toString());
			//System.out.println("haha");
			long nowtime = System.currentTimeMillis();
			String time = String.valueOf(nowtime);
			//SimpleDateFormat sdFormatter = new SimpleDateFormat
			String oid = OrderBean.addOrder(uid,rid,time,total);
			if (!(oid.equals("error")))
			{	
			for (int i=0;i< cui.length();i++){
				JSONObject cuisine = cui.getJSONObject(i);
				String cid = cuisine.getString("cid");
				int camount = cuisine.getInt("camount");
				OrderBean.addDetail(oid,rid,cid,camount);
			}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//doGet(request, response);
	}

}
