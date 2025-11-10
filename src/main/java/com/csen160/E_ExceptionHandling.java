package com.csen160;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class E_ExceptionHandling {
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

			// Execute query
			String sql = "SELECT FG FROM COFFEES WHERE SUP_ID = 50A";
			System.out.println("\"" + sql + "\";\n");

			// "table COFFEES (COF_NAME VARCHAR(32), SUP_ID INTEGER, PRICE FLOAT, SALES INTEGER, TOTAL INTEGER)"

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Coffee Name: " + rs.getString(1) + "\tSupplier ID: " + rs.getInt(2) + "\tPrice: "
						+ rs.getFloat(3) + "\tSales: " + rs.getInt(4) + "\tTotal: " + rs.getInt(5));
			}

			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			while (sqle != null) {
				System.out.println("Message:  " + sqle.getMessage());
				System.out.println("SQLState: " + sqle.getSQLState());
				System.out.println("Vendor Error: " + sqle.getErrorCode());
				sqle.printStackTrace();
				sqle = sqle.getNextException();
			}
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