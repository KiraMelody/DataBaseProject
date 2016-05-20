package com.ibm.crl.util;

public class Deliverer {
	private String deliverer_id;
    private String deliverer_name;
    private String tel;

    public Deliverer(String id)
    {
        this.deliverer_id= id;
    }
    public void setDelivererName(String name)
    {
        this.deliverer_name = name;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }
    public String getId()
    {
        return deliverer_id;
    }
    public String getDelivererName()
    {
        return deliverer_name;
    }
    public String getTel()
    {
        return tel;
    }
}
