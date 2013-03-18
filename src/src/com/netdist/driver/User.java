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
		return this.UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password){
		this.Password = Password;
	}


	public String getSex() {
		return this.Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getUserKey() {
		return this.UserKey;
	}

	public void getUserInfo(){
		String sqlcommand = "select * from users where name = \""+this.UserName+"\" and password = \""+this.Password+"\"";
		ResultSet rs = null;
		rs = conn.SelectCommand(sqlcommand);
	
		try {
			if(rs.next()){
				this.UserName = rs.getString("name");
				this.Password = rs.getString("password");
				this.UserKey = rs.getString("user_key");
				this.Sex = rs.getString("sex");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}finally{
			if (rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String makeUserKey(){
		String myinfo = null;
		String temp = null;
		try {
			MessageDigest alga = java.security.MessageDigest.getInstance("SHA-1");
			myinfo = this.getUserName();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			myinfo += df.format(new Date());
			alga.update(myinfo.getBytes());
			temp = byte2hex(alga.digest());
			return temp.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return null;
	}
	
	public boolean addUserinfo(){
		String sqlcommand = "insert into users (name,password,sex,user_key) value (\""+this.UserName+"\",\""+this.Password+"\",\""+this.Sex+"\",\"";
		this.UserKey = makeUserKey();
		sqlcommand +=this.UserKey+"\")";
		System.out.println(sqlcommand);
		if(conn.InsertCommand(sqlcommand)){
			return true;	
		}else 
			return false;
		
		
	}
	
	public boolean updateUserPassword(){
		String sqlcommand = "update users set password = \""+this.Password+"\" where name = '"+this.getUserName()+"'";
		if (conn.UpdateCommand(sqlcommand)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean updateUserSex(){
		String sqlcommand = "update users set sex = \""+this.Sex+"\" where name = '"+this.getUserName()+"'";
		if (conn.UpdateCommand(sqlcommand)){
			return true;
		}else{
			return false;
		}
	}
	
	public String byte2hex(byte[] b) //二行制转字符串 
    { 
     String hs=""; 
     String stmp=""; 
     for (int n=0;n<b.length;n++) 
      { 
       stmp=(java.lang.Integer.toHexString(b[n] & 0XFF)); 
       if (stmp.length()==1) hs=hs+"0"+stmp; 
       else hs=hs+stmp; 
       if (n<b.length-1)  hs=hs+""; 
      } 
     return hs.toUpperCase(); 
    } 
}
