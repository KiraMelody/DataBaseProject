package com.ibm.crl.util;

public class User {
	private String user_id;
    private String user_name;
    private String tel;
    private String address;

    public User(String id)
    {
        this.user_id= id;
    }
    public void setUserName(String name)
    {
        this.user_name = name;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getId()
    {
        return user_id;
    }

    public String getName()
    {
        return user_name;
    }

    public String getTel()
    {
        return tel;
    }
    public String getAddress()
    {
        return address;
    }
}
