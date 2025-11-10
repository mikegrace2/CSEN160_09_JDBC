package com.csen160;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class C_InsertRows {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

	// Database credentials
	static final String USER = "postgres";
	static final String PASS = "Admin";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			stmt.executeUpdate("INSERT INTO COFFEES VALUES('Amaretto', 49, 9.99, 0, 0)");
			stmt.executeUpdate("INSERT INTO COFFEES VALUES('Hazelnut', 49, 9.99, 0, 0)");
			stmt.executeUpdate("INSERT INTO COFFEES VALUES('Amaretto_decaf', 49, 10.99, 0, 0)");
			stmt.executeUpdate("INSERT INTO COFFEES VALUES('Hazelnut_decaf', 49, 10.99, 0, 0)");

		} catch (SQLException se) {
			se.printStackTrace(); // Handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // Handle errors for Class.forName
		} finally {
			try {
				stmt.close();
				conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
