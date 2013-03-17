package com.netdist.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User {
	private String UserName;
	private String Password;
	private String Sex;
	private String UserKey;
	private static MysqlConnector conn = new MysqlConnector();
	public User(){
		this.UserName = null;
		this.UserKey = null;
		this.Password = null;
		this.Sex = null;
	}
	
	public User(String UserName,String Password){
		this.UserName = UserName;
		this.Password = Password;
		getUserInfo();
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password){
		this.Password = Password;
	}


	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getUserKey() {
		return UserKey;
	}

	private void getUserInfo(){
		String sqlcommand = "select * from User where username = \""+this.UserName+"\" and password = \""+this.Password+"\"";
		ResultSet rs = null;
		rs = conn.SelectCommand(sqlcommand);
	
		try {
			if(rs.next()){
				this.UserName = rs.getString("UserName");
				this.Password = rs.getString("Password");
				this.UserKey = rs.getString("UserKey");
				this.Sex = rs.getString("Sex");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
	
	private String makeUserKey(){
		String myinfo = null;
		byte[] temp = null;
		try {
			MessageDigest alga = java.security.MessageDigest.getInstance("SHA-1");
			myinfo = this.getUserName();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			myinfo += df.format(new Date());
			System.out.println(myinfo);
			alga.update(myinfo.getBytes());
			temp = alga.digest();
			return temp.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return null;
	}
	
	public void addUserinfo(){
		
	}

}
