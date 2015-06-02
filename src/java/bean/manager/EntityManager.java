/**
 * <p>An abstract class for handling low-level database transactions. This class
 * contains generic methods for adding to, selecting from, and editing Entities
 * within a database. Because the EntityManager is abstract, each Entity that is
 * represented as an object within Java should have a corresponding Manager 
 * class that extends this class. These Manager classes should then be used
 * to manipulate Entity objects.</p>
 * 
 * <p>This class makes use of generics to help make higher-level code easier to
 * write and read by removing the programmer's need to cast objects. While using
 * generics with the EntityManager is not required it is recommended.</p>
 * 
 * <p>The use of DBConnectionPool is deprecated.</p>
 */
package bean.manager;

import bean.entity.Entity;
import db.DBAccess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zach Bolan
 * @param <T>
 */
public abstract class EntityManager<T extends Entity> {

	protected T			entity;
	private DBAccess	db;

	// MySQL Parameters ...that we don't use
	private final String url = "jdbc:mysql://localhost/mustachio";
	private final String user = "root";
	private final String pass = "pass";
	
	

	public EntityManager(Entity entity) {
		this.entity = (T) entity;
		db = DBAccess.getInstance();
	}

	/**
	 * Connects to the JDBC and creates a DBConnection Pool for managing
	 * connections with a MySQL database.
	 *
	 * @return True on a succussful connection, false on failure
	 */
	/*public boolean connect() {
	 if (!isConnected()) {
	 pool = DBConnectionPool.getInstance();
	 connected = pool.create(10, url, user, pass);
	 }
	 return connected;
	 }*/
	
	
	
	public void add(Entity incoming) {
		String sql = "INSERT INTO " + entity.getTableName() + "(";
		String keys = "";
		String values = "";

		Iterator it = incoming.getProperties().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (!pair.getKey().equals("id")) {
				keys += pair.getKey().toString() + ", ";
				values += "'" + pair.getValue().toString() + "'" + ", ";
			}
		}

		keys = keys.substring(0, keys.length() - 2);
		values = values.substring(0, values.length() - 2);
		sql += keys + ") VALUES (";
		sql += values + ")";
		System.out.println(sql);
		command(sql);
	}

	
	public List<T> find() {
		String sql = "SELECT * FROM " + entity.getTableName();
		return query(sql);
	}

	
	public List<T> find(String key, String value) {
		String sql = "SELECT * FROM " + entity.getTableName();
		sql += " WHERE " + key + "=" + "'" + value + "'";
		return query(sql);
	}

	
	/**
	 * Returns a single Entity object that resulted from a "SELECT" query. The
	 * underlying query only selects a single row from the database and it is
	 * not guaranteed how the database makes this selection. How the database is
	 * ordered and organized is significant in this process. A single object is
	 * built from a List of ResultSet objects and thus potential errors could
	 * arise from this process. If the event of an error or empty result, this
	 * method returns null.
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public T findOne(String key, String value) {
		String sql = "SELECT * FROM " + entity.getTableName();
		sql += " WHERE " + key + "=" + "'" + value + "'";
		sql += " LIMIT 1";
		List<T> list = query(sql);

		if (!list.isEmpty()) {
			if (list.get(0) != null) {
				return list.get(0);
			}
		}
		return null;
	}
	
	
	public boolean update(T incoming) {
		boolean success = false;
		String sql = "UPDATE " + incoming.getTableName() + " SET ";
		
		Iterator it = incoming.getProperties().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (!pair.getKey().equals("id")) {
				sql += pair.getKey().toString() + "='";
				sql += pair.getValue().toString() + "', ";
			}
		}

		sql = sql.substring(0, sql.length() - 2);
		sql += " WHERE id='" + incoming.getId() + "'";
		return command(sql);
	}

	
	/**
	 * Executes a generic SQL query passed in as a String and returns a List of
	 * Entity objects that were queried. If errors are present or an exception
	 * occurs this method returns null.
	 *
	 * @param query
	 * @return
	 */
	public List<T> query(String query) {
            try {
                Connection con = db.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                List<Entity> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(entity.create(rs));
                }
                con.close();
                return (List<T>) result;
            } 
            catch (Exception e) {
                System.out.println(e);
            }
            return null;
	}

	
	/**
	 * Executes a generic SQL Command that is passed in as a String and returns
	 * whether or not the command executed without errors.
	 *
	 * @param query
	 * @return False if encountered errors or exceptions, true on success
	 */
	public boolean command(String query) {
		boolean success = false;
		try {
			Connection con = db.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate(query);
			con.close();
			success = false;
		} 
		catch (Exception e) {}
		return success;
	}
	
	public Connection getConnection() {
		return db.getConnection();
	}
	

	/**
	 * Close all connections to the MySQL database.
	 */
	/*public void closeConnection() {
	 pool.destroy();
	 pool = null;
	 connected = false;
	 }*/
	/*public boolean isConnected() {
	 return connected;
	 }*/
}
