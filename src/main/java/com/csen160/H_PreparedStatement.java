package com.csen160;

import java.sql.*;

public class H_PreparedStatement {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

	// Database credentials
	static final String USER = "postgres";
	static final String PASS = "Admin";

	public static void main(String[] args) {
		Connection        conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.prepareStatement("UPDATE COFFEES Set price = 50.00 where cof_name like ?");
			stmt.setString(1, "%Amaretto%");
			
			int i = stmt.executeUpdate();
			System.out.println("Rows updated " + i);
						
			stmt.close();
			conn.close();				
		} catch (SQLException se) {
			se.printStackTrace(); // Handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // Handle errors for Class.forName
		} 
	}
}
