package com.ibm.crl.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Object;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;


/**
 * Servlet implementation class RegisteServlet
 */
@WebServlet("/RegisteServlet")
public class RegisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisteServlet() {
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
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			String geT=request.getParameter("data");
			JSONObject uuser=null;
			uuser = new JSONObject(geT);
			String username="";
			username = uuser.getString("username");
			String password="";
			password = uuser.getString("password");
			String tel="";
			tel = uuser.getString("tel");
			String address="";
			address = uuser.getString("address");
			JSONObject jout = new JSONObject();
			Registe userbean = new Registe();
			userbean.setTel(tel);
			userbean.setUsername(username);
			userbean.setPassword(password);
			userbean.setAddress(address);
			boolean result = false;
			result = UserBean.updateUser(userbean);
			if (result)
			{
		//		HttpSession session = request.getSession();
		//		session.setAttribute("session_userinfo", userbean);
				jout.put("result", "ok");
				response.getWriter().append(jout.toString());
				System.out.println("注册成功");
			}
			else
			{
				jout.put("result", "fail");
				response.getWriter().append(jout.toString());
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.getWriter().append("error!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}
