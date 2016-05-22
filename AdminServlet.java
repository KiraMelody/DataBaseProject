package com.ibm.crl.util;

import java.io.IOException;
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
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		try {
			chk = new JSONObject (request.getParameter("data"));
			System.out.println(chk.get("action"));
			if (chk.get("action").equals("getadminrestlist"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				arr = AdministratorBean.RestAll();
				jout.put("result","ok");
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("setrestinfo"))
			{
				JSONObject jout = new JSONObject();
				String rid = chk.getString("rid");
				String rname = chk.getString("rname");
				String raddr = chk.getString("raddr");
				String rdesc = chk.getString("rdesc");
				String rtel = chk.getString("rtel");
				AdministratorBean.updateRestaurant(rid, rname, rtel, raddr, rdesc);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("deleterest"))
			{
				JSONObject jout = new JSONObject();
				String rid = chk.getString("rid");
				AdministratorBean.deleteRestaurant(rid);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("setrestpassword"))
			{
				JSONObject jout = new JSONObject();
				String rid = chk.getString("rid");
				String newpassword = chk.getString("newpassword");
				AdministratorBean.setRestPassword(rid,newpassword);
				jout.put("result","ok");
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("createrest"))
			{
				JSONObject jout = new JSONObject();
				String rname="";
				String rtel="";
				String raddr="";
				String rdesc="";
				String rid = AdministratorBean.createRest(rname, rtel, raddr, rdesc);
				jout.put("result","ok");
				jout.put("rid",rid);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getadminuserstatistics"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				String begin = chk.getString("statstart");
				String end = chk.getString("staend");
				JSONObject o = AdministratorBean.QueryAll(begin, end);
				arr = AdministratorBean.UserAll(begin,end);
				jout.put("result","ok");
				jout.put("totalrevenue", o.getDouble("totalrevenue"));
				jout.put("totalorderamount", o.getDouble("totalorderamount"));
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getadminreststatistics"))
			{
				JSONObject jout = new JSONObject();
				JSONArray arr = new JSONArray();
				String begin = chk.getString("statstart");
				String end = chk.getString("staend");
				JSONObject o = AdministratorBean.QueryAll(begin, end);
				arr = AdministratorBean.RestQuery(begin,end);
				jout.put("result","ok");
				jout.put("totalrevenue", o.getDouble("totalrevenue"));
				jout.put("totalorderamount", o.getDouble("totalorderamount"));
				jout.put("data",arr);
				response.getWriter().append(jout.toString());
			}
			if (chk.get("action").equals("getadminorderstatistics"))
			{
				JSONObject jout = new JSONObject();
				JSONArray ans = new JSONArray();
				JSONArray arr = new JSONArray();
				JSONArray cui = new JSONArray();
				String begin = chk.getString("statstart");
				String end = chk.getString("staend");
				//JSONObject o = AdministratorBean.QueryAll(begin, end);
				arr = AdministratorBean.OrderQuery(begin,end);
				cui = AdministratorBean.DetailQuery(begin,end);
				for (int i = 0;i < arr.length();i++)
				{
					JSONObject t = arr.getJSONObject(i);
					JSONArray add = new JSONArray();
					String oid = t.getString("oid");
					for (int j = 0;j < cui.length();j++)
					{
						JSONObject c = cui.getJSONObject(j);
						String _oid = c.getString("oid"); 
						if (_oid.equals(oid))
						{
							add.put(c);
						}
					}
					t.put("ocontent", add);
					ans.put(t);
				}
				jout.put("result","ok");
				//jout.put("totalrevenue", o.getDouble("totalrevenue"));
				//jout.put("totalorderamount", o.getDouble("totalorderamount"));
				jout.put("data",ans);
				response.getWriter().append(jout.toString());
			}
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}
