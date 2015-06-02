/*
 * An encapsulation of a Connection object. In future iterations of the system,
 * this class should actually implement the Connection interface.
 */
package db;

import java.sql.Connection;

/**
 *
 * @author Zach Bolan
 */
public class DBConnection {
	
	private Connection		conn;
	private boolean			inUse = false;

	
	
	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	
	
	
	
}
