package com.jdbc.connection.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPreparedStatementDeleteExample {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "jdbc";
	private static final String DB_PASSWORD = "jdbc";

	public static void main(String[] args) {

		try {

			deleteRecordToTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}

	private static void deleteRecordToTable() throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteTableSQL = "DELETE FROM DBUSER1 WHERE USER_ID = ?";

		try {

			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteTableSQL);

			preparedStatement.setInt(1, 11);

			// execute delete SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is deleted from DBUSER1 table!");

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

}
