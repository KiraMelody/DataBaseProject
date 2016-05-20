package com.ibm.crl.util;

public class Detail {
	private String order_id;
	private String rest_id;
    private String cuisine_id;
    private int number;

    public Detail(String id)
    {
        this.order_id= id;
    }
    public void setRestId(String id)
    {
        this.rest_id = id;
    }
    public void setCuisineId(String id)
    {
        this.cuisine_id = id;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }
    public String getId()
    {
        return order_id;
    }
    public String getRestId()
    {
        return rest_id;
    }
    public String getCuisineId()
    {
        return cuisine_id;
    }
    public int getNumber()
    {
        return number;
    }
}
