package com.jdbc.connection.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStatementCreateExample {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_USER = "jdbc";
	private static final String DB_PASSWORD = "jdbc";

	public static void main(String[] args) {

		try {

			createDbUserTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}

	private static void createDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String createTableSQL = "CREATE TABLE DBUSER( " + "USER_ID NUMBER(5) NOT NULL, "
				+ "USERNAME VARCHAR(20) NOT NULL, " + "CREATED_BY VARCHAR(20) NOT NULL, "
				+ "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) " + ")";

		try {

			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(createTableSQL);

			// execute the SQL stetement
			statement.execute(createTableSQL);

			System.out.println("Table \"dbuser\" is created!");
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
