package com.caricature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnection {

	//needed to manually register our driver since tomcat is in charge
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static DAOConnection instance;
	private String url;
	private String username;
	private String password;
	
	public DAOConnection()
	{
		url = "jdbc:postgresql://project0.c59oarwg7xwr.us-east-2.rds.amazonaws.com"
				+ ":5432/postgres?currentSchema=caricature";
		username = "admini";
		password = "googenhiem";
	}
	
	public static DAOConnection getInstance()
	{
		if(instance == null)
		{
			instance = new DAOConnection();
		}
		
		return instance;
	}
	
	public Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
	}
}
