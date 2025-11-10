package com.csen160;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */
public class L_MetaData2 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

	// Database credentials
	static final String USER = "postgres";
	static final String PASS = "Admin";

	/**
	 * Standard main function.
	 * @param args input params
	 */
	public static void main(String[] args) {
		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Get MetaData
			// ------------------------------------------------------------------------------------------------
			DatabaseMetaData databaseMetaData = conn.getMetaData();

			int majorVersion = databaseMetaData.getDatabaseMajorVersion();
			int minorVersion = databaseMetaData.getDatabaseMinorVersion();

			String productName = databaseMetaData.getDatabaseProductName();
			String productVersion = databaseMetaData.getDatabaseProductVersion();

			int driverMajorVersion = databaseMetaData.getDriverMajorVersion();
			int driverMinorVersion = databaseMetaData.getDriverMinorVersion();

			System.out.println("majorVersion=" + majorVersion + " minorVersion=" + minorVersion);
			System.out.println("productName=" + productName + " productVersion=" + productVersion);
			System.out.println("driverMajorVersion=" + driverMajorVersion + " driverMinorVersion=" + driverMinorVersion);
			System.out.println();

			// Listing Tables
			// ------------------------------------------------------------------------------------------------
			String catalog = null;
			String schemaPattern = null;
			String tableNamePattern = null;
			String[] types = null;

			ResultSet result = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);

			for (int i = 0; result.next() && i < 100; i++) {
				String tableName = result.getString(3);
				System.out.println("tableName=" + tableName);
			}

			// Listing Columns in a Table
			// ------------------------------------------------------------
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet columns = metaData.getColumns(null, null, "pg_statistic_ext_data_stxoid_inh_index", null);
			// Printing the column name and size
			while (columns.next()) {
				System.out.print("Column name and size: " + columns.getString("COLUMN_NAME"));
				System.out.print("(" + columns.getInt("COLUMN_SIZE") + ")");
				System.out.println(" ");
				System.out.println("Ordinal position: " + columns.getInt("ORDINAL_POSITION"));
				System.out.println("Catalog: " + columns.getString("TABLE_CAT"));
				System.out.println("Data type (integer value): " + columns.getInt("DATA_TYPE"));
				System.out.println("Data type name: " + columns.getString("TYPE_NAME"));
				System.out.println(" ");
			}
			System.out.println();

		} catch (SQLException se) {
			se.printStackTrace(); // Handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // Handle errors for Class.forName
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
