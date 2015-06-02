/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Zach Bolan
 */
public class Image extends Entity {

	byte[]			data = null;
	String			alt = "";
	Integer			owner = 0;
	String			mime = "";
	
	
	public Image() {
		properties.put("data", data);
		properties.put("alt", alt);
		properties.put("member_id", owner);
		properties.put("mime", mime);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
		this.properties.put("data", data);
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
		this.properties.put("alt", alt);
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
		this.properties.put("member_id", owner);
	}
	
	public String getMime() {
		return mime;
	}
	
	public void setMime(String mime) {
		this.mime = mime;
		this.properties.put("mime", mime);
	}
	
	
	
	
	@Override
	public String getTableName() {
		return "image";
	}

	@Override
	public Entity create(ResultSet result) {
		Image image = new Image();
		try {
			image.setId(result.getInt("id"));
			image.setData(result.getBytes("data"));
			image.setAlt(result.getString("alt"));
			image.setOwner(result.getInt("member_id"));
			image.setMime(result.getString("mime"));
		} catch(SQLException e) {}
		return image;
	}
	
}
