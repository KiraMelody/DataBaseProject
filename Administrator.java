package com.ibm.crl.util;

public class Administrator {
	private String admin_id;
    private int authorization;
    public Administrator(String id)
    {
        this.admin_id= id;
    }
    public void setAuthorization(int authorization)
    {
        this.authorization = authorization;
    }

    public String getId()
    {
        return admin_id;
    }
    public int getAuthorization()
    {
        return authorization;
    }

}
