package com.ibm.crl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBControl {
	private static final String CLOUDDB_DRIVER = "com.informix.jdbc.IfxDriver";

	public static Connection connect() throws SQLException, ClassNotFoundException, NamingException {
		Class.forName(CLOUDDB_DRIVER);
		String jdbcURL = "jdbc:informix-sqli://crl.ptopenlab.com:9088/d_1460372072390793:INFORMIXSERVER=ifxserver1;USER=vzyfqfde;PASSWORD=wdAvxgCTil;DB_LOCALE=en_us.utf8";
		System.out.println("@@@@@@@@@@@@@@ jdbc url =" + jdbcURL);
		return DriverManager.getConnection(jdbcURL);
	}

}
