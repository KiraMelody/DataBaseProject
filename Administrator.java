package com.ibm.crl.util;

public class Administrator {
	private String admin_id;
    private int authorization;
    private int begin;
    private int end;
    public Administrator(String id)
    {
        this.admin_id= id;
    }
    public void setAuthorization(int authorization)
    {
        this.authorization = authorization;
    }
    public void setBegin(int begin)
    {
        this.begin = begin;
    }
    public void setEnd(int end)
    {
        this.end = end;
    }
    public String getId()
    {
        return admin_id;
    }
    public int getAuthorization()
    {
        return authorization;
    }
    public int getBegin()
    {
        return begin;
    }
    public int getEnd()
    {
        return end;
    }
}
