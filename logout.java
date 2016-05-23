package com.ibm.crl.util;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		//session.invalidate();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String geT=request.getParameter("data");
			JSONObject uuser=null;
			try {
				uuser = new JSONObject(geT);
				String userrole = "";
				userrole = uuser.getString("userrole");
				String uid="";
				if (userrole.equals("user"))
				{
					uid = uuser.getString("uid");
				}
				if (userrole.equals("rest"))
				{
					uid = uuser.getString("rid");
				}
				if (userrole.equals("delivererid"))
				{
					uid = uuser.getString("delivererid");
				}
				String sql = "delete from login where uid = '" + uid + "' and userrole = '" + userrole + "'";
				DBOperateTool.delete(sql);
				JSONObject jout = new JSONObject();
				jout.put("result", "ok");
				response.getWriter().append(jout.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
