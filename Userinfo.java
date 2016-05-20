package com.ibm.crl.util;

public class Userinfo {
	private int userid;
	private String username;
	private String password;
	private String address;
	private String tel;
	private String type;
	//private int status;
	
	public static final int TYPE_USER = 0;
	public static final int TYPE_ADMIN = 1;
	
	//public static final int STATUS_NORMAL = 0;
	//public static final int STATUS_FORBIDEN = 1;
	
	public int getUserID()
	{
		return this.userid;
	}
	public void setUserID(int id)
	{
		this.userid = id;
	}
	public String getUsername()
	{
		return this.username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getAddress()
	{
		return this.address;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public String getTel()
	{
		return this.tel;
	}
	public void setTel(String tel)
	{
		this.tel=tel;
	}
	public String getType()
	{
		return this.type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	/*public int getStatus()
	{
		return this.status;
	}*/
	/*public void setStatue(int status)
	{
		this.status = status;
	}*/
	//public boolean isLogon()
	//{
	//	return this.userid>0 && this.status!=STATUS_FORBIDEN;
	//}
}
