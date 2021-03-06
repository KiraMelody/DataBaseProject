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
 * Servlet implementation class DelivererServlet
 */
@WebServlet("/DelivererServlet")
public class DelivererServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelivererServlet() {
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
			if (chk.get("action").equals("getdeliverylist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = DeliveryBean.DeliveryAll();
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
			if (chk.get("action").equals("getdelivererorderlist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = OrderBean.OrderAllforDeliverer(chk.get("delivererid").toString());
				arr = makeJsonArray.MakeOrder(arr);
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getdelivererstatistics"))
			{
				JSONObject jout = new JSONObject();
				String deliverer_id = chk.get("delivererid").toString();
				String begin = chk.get("statstart").toString();
				String end = chk.get("statend").toString();
				double fee = DelivererBean.DelivererQueryFee(deliverer_id,begin,end);
				JSONArray arr = new JSONArray();
				arr = OrderBean.OrderAllforDelivererStatistics(deliverer_id,begin,end);
				arr = makeJsonArray.MakeOrder(arr);
				jout.put("result","ok");
				jout.put("totalsalary",fee);
				jout.put("data", arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("delivererconfirm"))
			{
				JSONObject jout = new JSONObject();
				String oid = chk.get("oid").toString();
				OrderBean.updateOrder(oid,"needconfirm");
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//doGet(request, response);
	}

}
