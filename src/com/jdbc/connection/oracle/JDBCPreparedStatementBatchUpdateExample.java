package com.jdbc.connection.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPreparedStatementBatchUpdateExample {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "jdbc";
	private static final String DB_PASSWORD = "jdbc";
	// private static final DateFormat DATE_FORMAT = new
	// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String[] args) {

		try {

			batchInsertRecordIntoTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}

	private static void batchInsertRecordIntoTable() throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO DBUSER1" + " ( USER_ID,USERNAME, CREATED_BY, CREATED_DATE) VALUES"
				+ " (?,?,?,?)";

		try {

			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			dbConnection.setAutoCommit(false);

			preparedStatement.setInt(1, 101);
			preparedStatement.setString(2, "Rohan101");
			preparedStatement.setString(3, "system");
			preparedStatement.setTimestamp(4, getCurrentTimeStamp());

			preparedStatement.setInt(1, 102);
			preparedStatement.setString(2, "Rohan102");
			preparedStatement.setString(3, "system");
			preparedStatement.setTimestamp(4, getCurrentTimeStamp());

			preparedStatement.setInt(1, 103);
			preparedStatement.setString(2, "Rohan103");
			preparedStatement.setString(3, "system");
			preparedStatement.setTimestamp(4, getCurrentTimeStamp());

			preparedStatement.addBatch();

			// execute insert SQL statement
			preparedStatement.executeBatch();

			dbConnection.commit();

			System.out.println("Record are inserted into DBUSER1 table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			dbConnection.rollback();
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
