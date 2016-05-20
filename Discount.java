package com.ibm.crl.util;

public class Discount {
	private String rest_id;
    private String discount_id;
    private String begin;
    private String end;
    private int limit;
    private int discount;
    public Discount(String id)
    {
        this.discount_id = id;
    }
    public void setRestId(String id)
    {
        this.rest_id = id;
    }
    public void setBegin(String begin)
    {
        this.begin = begin;
    }
    public void setEnd(String end)
    {
        this.end = end;
    }
    public void setLimit(int limit)
    {
        this.limit = limit;
    }
    public void setDiscount(int discount)
    {
        this.discount = discount;
    }
    public String getId()
    {
        return discount_id;
    }
    public String getRestId()
    {
        return rest_id;
    }
    public String getBegin()
    {
        return begin;
    }
    public String getEnd()
    {
        return end;
    }
    public int getLimit()
    {
        return limit;
    }
    public int getDiscount()
    {
        return discount;
    }
}
