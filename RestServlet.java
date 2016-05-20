package com.ibm.crl.util;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


/**
 * Servlet implementation class RestServlet
 */
@WebServlet("/RestServlet")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestServlet() {
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
		try {
			JSONObject chk =  new JSONObject (request.getParameter("data"));
			System.out.println(chk.toString());
			
			if (chk.get("action").equals("getrestlist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = RestBean.RestAll();
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getcuisinelist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = makeJsonArray.MakeMenu(chk.get("restid").toString());
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			
			if (chk.get("action").equals("getrestorderlist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = OrderBean.OrderAllforRest(chk.get("restid").toString());
				arr = makeJsonArray.MakeOrder(arr);
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getdelivererlist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = DelivererBean.DelivererAll();
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getreststatistics"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				String rest_id = chk.get("restid").toString();
				String begin = chk.get("begin").toString();
				String end = chk.get("end").toString();
				arr = RestBean.RestQueryHot(rest_id, begin, end);
				double income = RestBean.RestQueryProfit(rest_id, begin, end);
				jout.put("result","ok");
				jout.put("revenue",income);
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("setorderdeliverer"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				String oid = chk.get("oid").toString();
				String delivererid = chk.get("delivererid").toString();
				double fee = (double) chk.get("delivererfee");
				DeliveryBean.setDelivery(oid,delivererid,fee);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("confirmorder"))
			{
				JSONObject jout = new JSONObject();
				String oid = chk.get("oid").toString();
				long nowtime = System.currentTimeMillis();
				String time = String.valueOf(nowtime);
				DeliveryBean.setArrivalTime(oid,time);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("submitorder"))
			{
				String uid = chk.getString("uid");
				String rid = chk.getString("rid");
				double total = chk.getDouble("total");
				JSONArray cui = chk.getJSONArray("data");
				long nowtime = System.currentTimeMillis();
				String time = String.valueOf(nowtime);
				//SimpleDateFormat sdFormatter = new SimpleDateFormat
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
				}
			}
			//doGet(request, response);
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
