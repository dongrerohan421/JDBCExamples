package com.jdbc.connection.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JDBCStatementInsertExample {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "jdbc";
	private static final String DB_PASSWORD = "jdbc";
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String[] args) {

		try {

			insertRecordIntoDbUserTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}

	private static void insertRecordIntoDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO DBUSER " + "( USER_ID, USERNAME, CREATED_BY, CREATED_DATE ) " + "VALUES "
				+ "( 1, 'Rohan', 'system', " + "to_date('" + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

		try {

			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into DBUSER table!");

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

	private static String getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return DATE_FORMAT.format(today.getTime());
	}

}
