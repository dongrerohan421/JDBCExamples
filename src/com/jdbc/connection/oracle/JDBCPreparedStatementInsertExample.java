package com.jdbc.connection.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPreparedStatementInsertExample {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "jdbc";
	private static final String DB_PASSWORD = "jdbc";
	// private static final DateFormat DATE_FORMAT = new
	// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String[] args) {

		try {

			insertRecordIntoTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}

	private static void insertRecordIntoTable() throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO DBUSER1" + " ( USER_ID,USERNAME, CREATED_BY, CREATED_DATE) VALUES"
				+ " (?,?,?,?)";

		try {

			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, 11);
			preparedStatement.setString(2, "Rohan");
			preparedStatement.setString(3, "system");
			preparedStatement.setTimestamp(4, getCurrentTimeStamp());

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into DBUSER1 table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
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

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
}
