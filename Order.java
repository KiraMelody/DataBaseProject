package com.ibm.crl.util;


public class Order {
	private String order_id;
    private String user_id;
    private String rest_id;
    private String order_time;
    private String state;
    private double total;
    private double cost;
    public Order(String id)
    {
        this.order_id= id;
    }
    public void setUserId(String id)
    {
        this.user_id = id;
    }

    public void setRestId(String id)
    {
        this.rest_id = id;
    }
    public void setOrderTime(String time)
    {
        this.order_time = time;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    public void setTotal(double total)
    {
        this.total = total;
    }
    public void setCost(double cost)
    {
        this.cost = cost;
    }
    public String getId()
    {
        return order_id;
    }
    public String getUserId()
    {
        return user_id;
    }
    public String getRestId()
    {
        return rest_id;
    }
    public String getOrderTime()
    {
        return order_time;
    }
    public String getState()
    {
        return state;
    }
    public double getTotal()
    {
        return total;
    }
    public double getCost()
    {
        return cost;
    }
}
