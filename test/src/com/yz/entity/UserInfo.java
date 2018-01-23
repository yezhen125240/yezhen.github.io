package com.yz.entity;

public class UserInfo 
{
	private int userId;
	private String userName;
	private String userPass;
	private String age;
	private String sex;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() 
	{
		return this.getUserName() +"\t"+ this.getUserPass() +"\t"+ this.getAge() +"\t"+ this.getSex();
	}
}
