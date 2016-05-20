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
			if (chk.get("action").equals("getorderlist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				//System.out.println(chk.get("userid").toString());
				//System.out.println(DBOperateTool.query("select * from order where uid = '" + chk.get("userid").toString() + "'").toString());
				arr = OrderBean.OrderAllforUser(chk.get("userid").toString());
				arr = makeJsonArray.MakeOrder(arr);
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
			//doGet(request, response);
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

}
