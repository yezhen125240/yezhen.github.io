package com.yz.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.dao.UserInfoDao;
import com.yz.entity.UserInfo;
import com.yz.util.Util;

public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = 6361428596469463123L;

	UserInfoDao userDao = new UserInfoDao();
	Util util = new Util();
	String msg = "";
	String url = "";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		String falg = req.getParameter("falg");
		//System.out.println(falg);
		
		if(falg.equals("login"))
		{
			login(req,resp);
		}
		else if(falg.equals("save"))
		{
			saveInfo(req,resp);
		}
		
	}
	

	/*
	 * �����û���Ϣ
	 */
	private void saveInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		 Connection conn = util.ConnMySql();//�������ݿ�
		 boolean bool = userDao.saveInfo(req, resp, conn);//�����û���Ϣ
		 
		 if(bool == false)
		 {
			msg = "����ɹ�,������ҳ";
			url = "jsp/userInfo/save-queryInfo.jsp";
			//System.out.println("����ɹ�");
			
			ResultSet rs = userDao.usNameOrusPass(req, resp, conn);//�����˻������ѯ���ձ������Ϣ
			List<UserInfo> userList = userDao.querySave(rs);//��װ���ݿ��ѯ����Ϣ
			
			for(UserInfo u:userList)
			{
				System.out.println(u.getUserName());
				System.out.println(u.getUserPass());
				System.out.println(u.getAge());
				System.out.println(u.getSex());
			}
			req.setAttribute("msg",msg);
			req.setAttribute("userList",userList);
			req.getRequestDispatcher(url).forward(req, resp);
		 }	
		 else
		 {
			 msg = "����ʧ��";
			 url = "jsp/userInfo/save-queryInfo.jsp";
			 //System.out.println("����ʧ��");
			 
			 req.setAttribute("msg",msg);
			 req.getRequestDispatcher(url).forward(req, resp);
		 }
	}
	

	/*
	 * ��¼��֤
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
				
		//��֤�˻�����
		try 
		{
			Connection conn = util.ConnMySql();//�������ݿ�
			ResultSet rs = userDao.usNameOrusPass(req, resp, conn);//�����˻������ѯ�û�ƥ��
			
			if(rs.next())
			{
				msg = "��ӭ��¼";
				url = "jsp/main.jsp";
				req.setAttribute("msg",msg);
				req.getRequestDispatcher(url).forward(req, resp);
			}
			else if(!rs.next())
			{
				msg = "�˻����������,����������";
				url = "index.jsp";		
				req.setAttribute("msg",msg);
				req.getRequestDispatcher(url).forward(req, resp);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//�ر����ݿ�����
		util.CloseJdbc();
		
	}

}
