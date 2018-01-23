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
	 * 保存用户信息
	 */
	private void saveInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		 Connection conn = util.ConnMySql();//连接数据库
		 boolean bool = userDao.saveInfo(req, resp, conn);//保存用户信息
		 
		 if(bool == false)
		 {
			msg = "保存成功,返回首页";
			url = "jsp/userInfo/save-queryInfo.jsp";
			//System.out.println("保存成功");
			
			ResultSet rs = userDao.usNameOrusPass(req, resp, conn);//根据账户密码查询出刚保存的信息
			List<UserInfo> userList = userDao.querySave(rs);//封装数据库查询的信息
			
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
			 msg = "保存失败";
			 url = "jsp/userInfo/save-queryInfo.jsp";
			 //System.out.println("保存失败");
			 
			 req.setAttribute("msg",msg);
			 req.getRequestDispatcher(url).forward(req, resp);
		 }
	}
	

	/*
	 * 登录验证
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
				
		//验证账户密码
		try 
		{
			Connection conn = util.ConnMySql();//连接数据库
			ResultSet rs = userDao.usNameOrusPass(req, resp, conn);//根据账户密码查询用户匹配
			
			if(rs.next())
			{
				msg = "欢迎登录";
				url = "jsp/main.jsp";
				req.setAttribute("msg",msg);
				req.getRequestDispatcher(url).forward(req, resp);
			}
			else if(!rs.next())
			{
				msg = "账户或密码错误,请重新输入";
				url = "index.jsp";		
				req.setAttribute("msg",msg);
				req.getRequestDispatcher(url).forward(req, resp);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//关闭数据库连接
		util.CloseJdbc();
		
	}

}
