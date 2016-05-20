package com.ibm.crl.util;

public class Restaurant {
	private String rest_id;
    private String rest_name;
    private String proprietor;
    private String tel;
    private String address;
    private String introduction;
    private double profit;
    public Restaurant(String id)
    {
        this.rest_id= id;
    }
    public void setRestName(String name)
    {
        this.rest_name = name;
    }
    public void setProprietor(String proprietor)
    {
        this.proprietor = proprietor;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }
    public void setAddress(String addr)
    {
        this.address = addr;
    }
    public void setIntroduction(String intr)
    {
        this.introduction = intr;
    }
    public void setProfit(double profit)
    {
        this.profit = profit;
    }
    public String getId()
    {
        return rest_id;
    }
    public String getRestName()
    {
        return rest_name;
    }
    public String getProprietor()
    {
        return proprietor;
    }
    public String getTel()
    {
        return tel;
    }
    public String getAddress()
    {
        return address;
    }
    public String getIntroduction()
    {
        return introduction;
    }
    public double getProfit()
    {
    	return profit;
    }
}
