package com.jdbc.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	//Connection객체를 생성해주는 기능
	public static Connection getConnection() {
		Connection conn=null;
		String path=JDBCTemplate.class.getResource("/driver.properties").getPath();
		System.out.println(path);
		try {
			Properties driver = new Properties();
			driver.load(new FileReader(path));
			Class.forName(driver.getProperty("drivername"));
			conn = DriverManager.getConnection(driver.getProperty("url"),driver.getProperty("user"),driver.getProperty("pw"));
			conn.setAutoCommit(false);
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//Connection, Statement, Result객체를 반환해주는 기능
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try { 
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//트랜잭션을 처리하는 기능
	public static void commit(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) {
				conn.commit();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//Static으로 선언해 관리한다
	
}
















