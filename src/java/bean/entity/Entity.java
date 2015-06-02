/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.entity;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Zach Bolan
 */
public abstract class Entity {
	
	protected int id;
	protected Map<String, Object> properties;
	
	
	public Entity() {
		properties = new HashMap<>();
		properties.put("id", id);
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * Returns the name of this Entity
	 * @return 
	 */
	public abstract String getTableName();
	
	/**
	 * Creates and returns a new instance of this Entity object
	 * @param result
	 * @return 
	 */
	public abstract Entity create(ResultSet result);
	
	/**
	 * Returns a Map containing the keys and values of the properties of this
	 * Entity object.
	 * @return 
	 */
	public Map getProperties() {
		return properties;
	}
	
}
