package com.sdudoc.lucene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	// 必须参数
	public static final String DRIVER = "com.mysql.jdbc.Driver"; // 驱动名称
	public static final String URL = "jdbc:mysql://localhost:3306/sdudoc"; // 数据库地址
	public static final String DBNAME = "root"; // 用户名
	public static final String PASSWORD = "root"; // 密码

	/**
	 * 获取数据库连接
	 * 
	 * @return con
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, DBNAME, PASSWORD);
		return con;
	}

}
