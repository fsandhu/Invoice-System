package com.cinco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

	public static Connection newConnection() {

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (InstantiationException e) {
					System.out.println("InstantiationException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					System.out.println("IllegalAccessException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				} catch (ClassNotFoundException e) {
					System.out.println("ClassNotFoundException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				}

				Connection conn = null;

				try {
					conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
				} catch (SQLException e) {
					System.out.println("SQLException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				}

				return conn;
	}

	public static void closeConnection (Connection conn) {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
