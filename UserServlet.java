package com.ibm.crl.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		JSONObject chk;
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdFormatter.format(nowTime);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			chk = new JSONObject (request.getParameter("data"));
			System.out.println(chk.get("action"));
			if (chk.get("action").equals("getuserinfo"))
			{
				JSONObject jout = new JSONObject();
				String user_id = chk.get("uid").toString();
				User u = UserBean.getUser (user_id);
				jout.put("result","ok");
				jout.put("username",u.getName());
				jout.put("tel",u.getTel());
				jout.put("address",u.getAddress());
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("setuserinfo"))
			{
				JSONObject jout = new JSONObject();
				String user_id = chk.get("uid").toString();
				String username = chk.get("username").toString();
				String tel = chk.get("tel").toString();
				String address = chk.get("address").toString();
				UserBean.updateUserInfo (user_id,username,tel,address);
			}
			if (chk.get("action").equals("confirmorder"))
			{
				JSONObject jout = new JSONObject();
				String oid = chk.get("oid").toString();
				DeliveryBean.setArrivalTime(oid,time);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getcuisinelist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = makeJsonArray.MakeMenu(chk.get("rid").toString());
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getorderlist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = OrderBean.OrderAllforUser(chk.get("uid").toString());
				arr = makeJsonArray.MakeOrder(arr);
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("submitorder"))
			{
				String uid = chk.getString("uid");
				String rid = chk.getString("rid");
				double total = chk.getDouble("total");
				JSONArray cui = chk.getJSONArray("data");
				JSONObject jout = new JSONObject();
				String oid = OrderBean.addOrder(uid,rid,time,total);
				if (!(oid.equals("error")))
				{	
					for (int i=0;i< cui.length();i++)
					{
						JSONObject cuisine = cui.getJSONObject(i);
						String cid = cuisine.getString("cid");
						int camount = cuisine.getInt("camount");
						DetailBean.addDetail(oid,rid,cid,camount);
					}
					jout.put("result","ok");
					response.getWriter().append(jout.toString());
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
