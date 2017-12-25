package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException{
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		prop.load(in);
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		//System.out.println("GETTING CONNECTION");
		return DriverManager.getConnection(url, user, password);
	}
}

/*url=jdbc:oracle:thin:@mydbinstance.c6jbqtcnf5pz.us-east-2.rds.amazonaws.com:1521:ORCL
user=admin
password=password
 * 
 * 
 */
