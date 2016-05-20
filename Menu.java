package com.ibm.crl.util;

public class Menu {
	private String rest_id;
    private String cuisine_id;
    private String cuisine;
    private String introduction;
    private double price;

    public Menu(String id)
    {
        this.rest_id = id;
    }
    public void setCuisineId(String id)
    {
        this.cuisine_id = id;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setCuisine(String cuisine)
    {
        this.cuisine = cuisine;
    }
    public void setIntroduction(String intro)
    {
        this.introduction = intro;
    }
    public String getId()
    {
        return rest_id;
    }
    public String getCuisineId()
    {
        return cuisine_id;
    }
    public double getPrice()
    {
        return price;
    }
    public String getCuisine()
    {
        return cuisine;
    }
    public String getIntroduction()
    {
        return introduction;
    }
}
