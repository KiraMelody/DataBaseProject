package com.ibm.crl.util;

public class Registe {
	private String user_id;
	private String username;
    private String password;
    private String tel;
    private String address;
    //private String userrole;

    public void setAddress(String address)
    {
    	this.address = address;
    }
    public void setTel(String tel)
    {
    	this.tel = tel;
    }
    public void setUser_id(String user_id)
    {
    	this.user_id = user_id;
    }
    public void setUsername(String username)
    {
        this.username= username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    /*public void setUserrole(String userrole)
    {
        this.userrole = userrole;
    }*/
    public String getAddress()
    {
    	return address;
    }
    public String getTel()
    {
    	return tel;
    }
    public String getUsername()
    {
    	return username;
    }
    public String getId()
    {
        return user_id;
    }
    public String getPassword()
    {
        return password;
    }

    /*public String getUserrole()
    {
        return userrole;
    }*/
}
