package com.ibm.crl.util;

import java.io.IOException;

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
		try {
			String geT=request.getParameter("data");
			JSONObject uuser=null;
			uuser = new JSONObject(geT);
			String username="";
			username = uuser.getString("username");
			String password="";
			password = uuser.getString("password");
			String userrole="";
			userrole = uuser.getString("userrole");
			boolean remember=uuser.getBoolean("rememberme");
			long nowtime = System.currentTimeMillis();
			if (remember)
				nowtime=nowtime + 36000;
			else
				nowtime=nowtime + 3600;
			String time = String.valueOf(nowtime);
			JSONObject jout = new JSONObject();
			jout.put("result", "ok");
			String uid = LoginBean.login(username,password,userrole,time);
			if (uid != null && uid != "error")
			{
				//HttpSession session = request.getSession();
				//session.setAttribute("session_userinfo", user);
				jout.put("id",uid);
				//response.getWriter().append(jout.toString());
				System.out.println("��¼�ɹ�");
				
			}
			else
			{
				response.getWriter().append("error");
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
