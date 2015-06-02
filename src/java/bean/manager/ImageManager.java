/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.manager;

import bean.entity.Entity;
import bean.entity.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Zach Bolan
 */
public class ImageManager<T extends Image> extends EntityManager {

	public ImageManager() {
		super(new Image());
	}
	
	/**
	 * Images require special adding technique
	 * @param incoming
	 */
	public boolean add(Image incoming) {
		boolean success = false;
		try {
			String sql = "INSERT INTO image (data, alt, member_id, mime)";
			sql += " VALUES (?,?,?,?)";
			Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBytes(1, incoming.getData());
			statement.setString(2, incoming.getAlt());
			statement.setInt(3, incoming.getOwner());
			statement.setString(4, incoming.getMime());
			statement.executeUpdate();
			success = true;
		} catch(Exception e){}
		return success;
	}
	
}
