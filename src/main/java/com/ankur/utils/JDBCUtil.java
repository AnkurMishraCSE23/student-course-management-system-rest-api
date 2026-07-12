package com.ankur.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil
{
	static
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private static final String url = "jdbc:mysql://localhost:3306/studentandcourses";
    private static final String user = "root";
    private static final String password = "@mysqlGan2";
    
    public static Connection getConnection() throws SQLException
    {
    	return DriverManager.getConnection(url, user, password);
    }
}
