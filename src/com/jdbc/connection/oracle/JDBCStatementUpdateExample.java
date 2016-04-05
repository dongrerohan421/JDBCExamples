package com.jdbc.connection.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStatementUpdateExample {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "jdbc";
	private static final String DB_PASSWORD = "jdbc";

	public static void main(String[] args) {

		try {

			updateRecordIntoDbUserTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}

	private static void updateRecordIntoDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String updateTableSQL = "UPDATE DBUSER" + " SET USERNAME = 'Rohan_New' " + " WHERE USER_ID = 1";

		try {

			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(updateTableSQL);

			// execute update SQL statement
			statement.executeUpdate(updateTableSQL);

			System.out.println("Record is updated into DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

}
