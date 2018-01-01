package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleDriver;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException{
		DriverManager.registerDriver(new OracleDriver());
		//Properties prop = new Properties();
		//InputStream in = new FileInputStream("connection.properties");
		//prop.load(in);
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "trms";
		String password = "qwerty";
		//System.out.println("GETTING CONNECTION");
		return DriverManager.getConnection(url, user, password);
	}
}


