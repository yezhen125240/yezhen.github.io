package com.yz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util 
{
	Connection conn = null;
	
	/*
	 * �������ݿ�
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
	 * �ر����ݿ�����
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
