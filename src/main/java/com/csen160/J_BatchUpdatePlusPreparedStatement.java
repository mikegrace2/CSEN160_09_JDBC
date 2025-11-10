package com.csen160;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class J_BatchUpdatePlusPreparedStatement {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

	// Database credentials
	static final String USER = "postgres";
	static final String PASS = "Admin";

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Statement stmt2 = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// "table COFFEES (COF_NAME VARCHAR(32), SUP_ID INTEGER, PRICE FLOAT, SALES INTEGER, TOTAL INTEGER)"
			
			String sql = "update COFFEES set cof_name = ?, sup_id = ? where price = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, "Hazlenut");
			stmt.setInt   (2, 49);
			stmt.setFloat (3, 10.99f);
			stmt.addBatch ();

			stmt.setString(1, "Amaretto");
			stmt.setInt   (2, 49);
			stmt.setFloat (3, 50.0f);
			stmt.addBatch ();

		    int[] affectedRecords = stmt.executeBatch();

			for (int result : affectedRecords)
				System.out.println("Update: " + result);

			// Check table ------------------------------------------------------------------------------
			stmt2 = conn.createStatement();
			sql = "SELECT * FROM COFFEES";
			System.out.println("\"" + sql + "\";\n");

			ResultSet rs = stmt2.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Coffee Name: " + rs.getString(1) + "\tSupplier ID: " + rs.getInt(2) + "\tPrice: "
						+ rs.getFloat(3) + "\tSales: " + rs.getInt(4) + "\tTotal: " + rs.getInt(5));
			}

		} catch (SQLException se) {
			se.printStackTrace(); // Handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // Handle errors for Class.forName
		} finally {
			try {
				stmt.close();
				stmt2.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
