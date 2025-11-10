package com.csen160;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class A_SimpleExample {
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

			// Create Table
			String[] sqlStmts = {
					"CREATE TABLE CUSTOMER(ID INTEGER NOT NULL PRIMARY KEY, FIRSTNAME VARCHAR(255),"
					+" LASTNAME VARCHAR(255), STREET VARCHAR(255), CITY VARCHAR(255))",
					"INSERT INTO CUSTOMER VALUES(0, 'Laura', 'Steel', '429 Seventh Av.', 'Dallas')",
					"INSERT INTO CUSTOMER VALUES(1, 'Suzanne', 'King', '366 - 20th Ave.', 'Olten')",
					"INSERT INTO CUSTOMER VALUES(2, 'Anne', 'Miller', '20 Upland Pl.', 'Lyon')" };

			for (String sql : sqlStmts) {
				stmt.executeUpdate(sql);
			}
			
			System.out.println("Tabelle/data created!");

			// Execute query
			String sql = "SELECT * FROM CUSTOMER";
			System.out.println("\"" + sql + "\";");

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + "\tfirstname: " + rs.getString(2) + "\tlastname: "
						+ rs.getString(3) + "\tstreet: " + rs.getString(4) + "\tcity: " + rs.getString(5));
			}
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