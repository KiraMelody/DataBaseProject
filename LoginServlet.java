package com.ibm.crl.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
			System.out.println(request.getParameter("data"));
			JSONObject chk =  new JSONObject (request.getParameter("data"));
			//System.out.println("get");//chk.toString());
			Date nowTime = new Date(System.currentTimeMillis());
			SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdFormatter.format(nowTime);
			if (chk.get("action").equals("login"))
			{
			String username="";
			username = chk.getString("username");
			String password="";
			password = chk.getString("password");
			String userrole="";
			userrole = chk.getString("userrole");
			boolean remember=chk.getBoolean("rememberme");
			long nowtime = System.currentTimeMillis();
			long lasttime = 0;
			if (remember)
				lasttime = 36000;
				//nowtime=nowtime + 36000;
			else
				lasttime = 3600;
			nowtime=nowtime + lasttime;
			JSONObject jout = new JSONObject();
			jout.put("result", "ok");
			String uid = LoginBean.login(username,password,userrole,nowtime);
			if (uid != null && (!uid.equals("error")))
			{
				//HttpSession session = request.getSession();
				//session.setAttribute("session_userinfo", user);
				if (userrole.equals("user"))
					jout.put("uid",uid);
				if (userrole.equals("rest"))
					jout.put("rid",uid);
				if (userrole.equals("deliverer"))
					jout.put("delivererid",uid);
				jout.put("sessionlife",lasttime);
			}
			else
			{
				//System.out.println("error");
				jout.put("result", "error");
				//response.getWriter().append(jout.toString());
			}
			response.getWriter().append(jout.toString());
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.getWriter().append("error!");
		}
		//doGet(request, response);
 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

