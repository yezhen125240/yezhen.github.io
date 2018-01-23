package com.yz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util 
{
	Connection conn = null;
	
	/*
	 * 连接数据库
	 */
	public Connection ConnMySql()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * 关闭数据库连接
	 */
	public void CloseJdbc()
	{
		try 
		{
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}


}
