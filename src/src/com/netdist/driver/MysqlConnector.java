package com.netdist.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnector {

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/test";
	private String user = "root";
	private String password = "redhat";
	private static Connection conn = null;
	private static Statement statement = null;
	public MysqlConnector() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user, password);
			statement = conn.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
	public ResultSet SelectCommand(String sql){
		ResultSet rs = null;
		try {
			if (statement == null ||conn == null){
				return null;
			}
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return rs;
		
	}
	
	public boolean InsertCommand(String sql){
		boolean status = false;
		try{
		
			status = statement.execute(sql);
			status = true;
		}catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		return status;	
	}
	
	public boolean UpdateCommand(String sql){
		boolean status = false;
		try{
			statement.execute(sql);
			status = true;
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
		return status;
	}
	public void CloseConnection(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
