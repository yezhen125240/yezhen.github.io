package com.yz.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.entity.UserInfo;
import com.yz.util.Util;

public class UserInfoDao 
{
	Util util = new Util();
	
	/*
	 * ��װҳ�����ݣ�������
	 */
	public UserInfo getUserInfo (HttpServletRequest req, HttpServletResponse resp)
	{
		//ȡ��ҳ�洫����������
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		String age 		= req.getParameter("age");
		String sex		= req.getParameter("sex");
		//System.out.println(age);
		
		//��װ����
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setUserPass(userPass);
		userInfo.setAge(age);
		userInfo.setSex(sex);
		
		return userInfo;
	}

	/*
	 * �����˻������ѯ�û�
	 */
	public ResultSet usNameOrusPass (HttpServletRequest req, HttpServletResponse resp,Connection conn)
	{
		ResultSet rs = null;
				
		//ȡ��ҳ������
		UserInfo userInfo = new UserInfoDao().getUserInfo(req, resp);
		String userName = userInfo.getUserName();		
		String userPass = userInfo.getUserPass();
		//System.out.println(userName +"\t"+ userPass);
		
		//SELECT * FROM user_info WHERE 1 = 1 AND userName = 'yz' AND userPass = '5'
		String loginSql = "SELECT * FROM user_info WHERE 1 = 1 AND userName = '" +userName+ "' AND userPass = '" +userPass+ "';";
		
		//�������ݿ�
		try 
		{
			Statement sta = conn.createStatement();
			rs = sta.executeQuery(loginSql);
			//System.out.println(loginSql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rs;
		
	}

	/*
	 * �����û���Ϣ�������Ƿ񱣴�ɹ�(falseΪ�ɹ�)
	 */
	public boolean saveInfo(HttpServletRequest req, HttpServletResponse resp,Connection conn)
	{
		//ȡ��ҳ������
		UserInfo userInfo = new UserInfoDao().getUserInfo(req, resp);
		String userName = userInfo.getUserName();		
		String userPass = userInfo.getUserPass();
		String age		= userInfo.getAge();
		String sex		= userInfo.getSex();
		//System.out.println(userName +"\t"+ userPass +"\t"+ age +"\t"+ sex);
		
		//INSERT INTO user_info (userName,userPass,age,sex) VALUE ('yz','5','24','��')
		String SaveSql = "INSERT INTO user_info (userName,userPass,age,sex) VALUE ('" 
							+userName+ "','" +userPass+ "','" +age+ "','" +sex+ "');";

		boolean bool = true;
		try 
		{
			Statement sta = conn.createStatement();
			bool = sta.execute(SaveSql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		 
		return bool;
	}

	/*
	 * ��ѯ���ձ�����û���Ϣ
	 */
	public List<UserInfo> querySave(ResultSet rs)
	{
		//��ѯ���������Ϣ
		UserInfo userInfo = new UserInfo();
		List<UserInfo> userList = new ArrayList<UserInfo>();
		try 
		{
			while(rs.next())
			{
				String userName = rs.getString("userName");
				//String userPass = rs.getString("userPass");
				String age 		= rs.getString("age");
				String sex		= rs.getString("sex");
				
				userInfo.setUserName(userName);
				userInfo.setUserPass("xxxxxx");
				userInfo.setAge(age);
				userInfo.setSex(sex);
				
				userList.add(userInfo);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return userList;
	}
}
