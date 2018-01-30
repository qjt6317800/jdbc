package com.qujia.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static final String URL="jdbc:mysql://localhost:3306/test_01";
	private static final String USER="root";
	private static final String PASSWORD="6317800";
	private static Connection conn=null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        //2.获得数据库的连接

       conn =  DriverManager.getConnection(URL,USER,PASSWORD);

       return conn;

    }
	public static void main(String[] args) throws Exception  {
//		//1.加载驱动程序
//		Class.forName("com.mysql.jdbc.Driver");
//		//2.获得数据连接
//		conn= DriverManager.getConnection(URL,USER,PASSWORD);
		DBUtil db=new DBUtil();
		db.getConnection();
		//3通过数据库的连接操作数据库，实现增删该查
		Statement stmt= conn.createStatement();
		ResultSet rs=stmt.executeQuery("select user_name,age from imooc_goddess");
		while(rs.next()) {
			System.out.println(rs.getString("user_name")+","+rs.getInt("age"));
		}
	}
}
