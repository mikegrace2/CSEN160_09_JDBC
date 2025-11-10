package com.csen160;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class G_PreparedStatement {
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
			stmt = conn.prepareStatement("Select * from COFFEES where cof_name like ?");
			
			// Sets the value of the parameter, title, with the position
			// of the parameter and its value
			stmt.setString(1, "%Amaretto%");
						
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"Coffee Name: "   + rs.getString(1) + 
						"\tSupplier ID: " + rs.getInt(2)    + 
						"\tPrice: "       + rs.getFloat(3)  + 
						"\tSales: "       + rs.getInt(4)    + 
						"\tTotal: "       + rs.getInt(5));
			}
			
			stmt.close();
			conn.close();				
		} catch (SQLException se) {
			se.printStackTrace(); // Handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // Handle errors for Class.forName
		} 
	}
}
