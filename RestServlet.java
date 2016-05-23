package com.ibm.crl.util;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
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
		//doPost(request,response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ok");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdFormatter.format(nowTime);
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
			if (chk.get("action").equals("getrestcuisinelist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = makeJsonArray.MakeMenu(chk.get("rid").toString());
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getrestorderlist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = OrderBean.OrderAllforRest(chk.get("rid").toString());
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
				String rest_id = chk.get("rid").toString();
				String begin = chk.get("statstart").toString();
				String end = chk.get("statend").toString();
				arr = RestBean.RestQueryHot(rest_id, begin, end);
				arr = makeJsonArray.MakeQuery(arr,rest_id);
				double income = RestBean.RestQueryProfit(rest_id, begin, end);
				jout.put("result","ok");
				jout.put("revenue",income);
				jout.put("popularcuisine",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("setorderdeliverer"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				String oid = chk.get("oid").toString();
				String delivererid = chk.get("delivererid").toString();
				double fee = chk.getDouble("delivererfee");
				DeliveryBean.setDelivery(oid,delivererid,fee);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			
			
			if (chk.get("action").equals("createcuisine"))
			{
				JSONObject jout = new JSONObject();
				String rid = chk.get("rid").toString();
				String cname = chk.get("cname").toString();
				String cdesc = chk.get("cdesc").toString();
				double cprice = chk.getDouble("cprice");
				String cid = MenuBean.setCuisine(rid,cname,cdesc,cprice);
				jout.put("result","ok");
				JSONObject o = new JSONObject();
				o.put("rid", rid);
				o.put("cid", cid);
				o.put("cname", cname);
				o.put("cdesc", cdesc);
				o.put("cprice", cprice);
				jout.put("data",o);
				System.out.println(jout.toString());
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("deletecuisine"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				String rid = chk.get("rid").toString();
				String cid = chk.get("cid").toString();
				MenuBean.deleteCuisine(rid,cid);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("setcuisineinfo"))
			{
				JSONObject jout = new JSONObject();
				String rid = chk.get("rid").toString();
				String cid = chk.get("cid").toString();
				String cname = chk.get("cname").toString();
				String cdesc = chk.get("cdesc").toString();
				double cprice = chk.getDouble("cprice");
				MenuBean.updateCuisine(rid,cid,cname,cdesc,cprice);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getrestdesc"))
			{
				JSONObject jout = new JSONObject();
				String rid = chk.get("rid").toString();
				Restaurant u = RestBean.getRest(rid);
				jout.put("result","ok");
				jout.put("rdesc", u.getIntroduction());
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("setrestdesc"))
			{
				JSONObject jout = new JSONObject();
				String rid = chk.get("rid").toString();
				String rdesc = chk.get("rdesc").toString();
				RestBean.setRestDesc(rid,rdesc);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
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
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
