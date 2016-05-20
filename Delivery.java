package com.ibm.crl.util;

public class Delivery {
	private String order_id;
    private double fee;
    private String arrival_time;
    private String deliverer_id;

    public Delivery(String id)
    {
        this.order_id = id;
    }
    public void setFee(double fee)
    {
        this.fee = fee;
    }
    public void setArrivalTime(String time)
    {
        this.arrival_time = time;
    }
    public void setDelivererId(String id)
    {
        this.deliverer_id = id;
    }
    public String getId()
    {
        return order_id;
    }
    public double getFee()
    {
        return fee;
    }
    public String getArrivalTime()
    {
        return arrival_time;
    }
    public String getDelivererId()
    {
        return deliverer_id;
    }

}
