package com.ibm.crl.util;

public class Login {
	//private String log_id;
	private String username;
    private String password;
    private String userrole;

    /*public void setlog_id(String log_id)
    {
    	this.log_id = log_id;
    }*/
    public void setUsername(String username)
    {
        this.username= username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setUserrole(String userrole)
    {
        this.userrole = userrole;
    }
    public String getUsername()
    {
    	return username;
    }
    /*public String getId()
    {
        return log_id;
    }*/
    public String getPassword()
    {
        return password;
    }

    public String getUserrole()
    {
        return userrole;
    }
}
