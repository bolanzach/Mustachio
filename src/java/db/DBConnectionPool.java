/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zach Bolan
 */
public class DBConnectionPool {

	private static boolean			open		= false;
	private static DBConnectionPool instance	= null;
	private List<DBConnection>		connections;


	protected DBConnectionPool() {
		connections = new ArrayList<>();
	}

	/**
	 * Using the singleton pattern, returns a common single instance of the
	 * DBConnectionPool.
	 *
	 * @return Single instance of DBConnectionPool
	 */
	public synchronized static DBConnectionPool getInstance() {
		if (instance == null) {
			instance = new DBConnectionPool();
		}
		return instance;
	}

	/**
	 * Creates a Connection Pool and returns whether or not the connection was
	 * made succesfully or not. If this pool is already connected then this 
	 * method returns false, as there can only be one open pool at a time.
	 *
	 * @param size The number of DBConnections to be made
	 * @param url Url of the database
	 * @param user Authorized username for the database
	 * @param pass The user's password
	 * @return true on success, false otherwise
	 */
	public boolean create(int size, String url, String user, String pass) {
		boolean success = false;
		
		if (!open) {
			for (int i = 0; i < size; i++) {
				try {
					System.out.println("Creating Pool");
					DBConnection connection = new DBConnection();
					Class.forName("com.mysql.jdbc.Driver");
					connection.setConnection(DriverManager.getConnection(url, user, pass));
					connections.add(connection);
					open = true;
					success = true;
				} catch (Exception e) {
					success = false;
				}
			}
		}
		return success;
	}

	/**
	 * Returns an available DBConnection from this pool, making it unavailable
	 * for checking out until it has been checked back in.
	 *
	 * @return DBConnection object
	 */
	public DBConnection checkout() {
		for (DBConnection connection : connections) {
			if (!connection.isInUse()) {
				connection.setInUse(true);
				return connection;
			}
		}
		return null;
	}

	/**
	 * Receives a DBConnection object and returns it back into the pool, making
	 * the DBConnection available for checkout in the future.
	 *
	 * @param incoming
	 */
	public void checkin(DBConnection incoming) {
		for (DBConnection connection : connections) {
			if (connection == incoming) {
				connection.setInUse(false);
			}
		}
	}

	/**
	 * Clears this pool, closing all DBConnections and setting all values to
	 * null.
	 */
	public void destroy() {
		try {
			for (DBConnection connection : connections) {
				connection.getConnection().close();
			}
			this.connections = null;
			instance = null;
			open = false;
		} catch (SQLException e) {
		}
	}

	private void addConnection() {

	}

}
