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
 * @author Zach
 */
public class Member extends Entity {
	
	private String			username = "";
	private String			password = "";
	private int				profile_pic = 0;
	private String			bio = "";
	private int				isAdmin = 0;	// 0=false, 1=true
	private int				isFlagged = 0;  // 0=false, 1-true
	
	
	public Member() {
		super();
		properties.put("username", username);
		properties.put("password", password);
		properties.put("profile_picture_id", profile_pic);
		properties.put("bio", bio);
		properties.put("is_admin", isAdmin);
		properties.put("is_flagged", isFlagged);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		this.properties.put("username", username);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.properties.put("password", password);
	}

	public int getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(int profile_pic) {
		this.profile_pic = profile_pic;
		this.properties.put("profile_picture_id", profile_pic);
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
		this.properties.put("bio", bio);
	}

	public int isIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
		this.properties.put("is_admin", isAdmin);
	}
	
	public void setIsFlagged(int flagged) {
		this.isFlagged = flagged;
		this.properties.put("is_flagged", flagged);
	}
	
	public int isIsFlagged() {
		return isFlagged;
	}

	@Override
	public Member create(ResultSet result) {
		Member member = new Member();
		try {
			member.setId(result.getInt("id"));
			member.setUsername(result.getString("username"));
			member.setPassword(result.getString("password"));
			member.setBio(result.getString("bio"));
			member.setProfile_pic(result.getInt("profile_picture_id"));
			member.setIsAdmin(result.getInt("is_admin"));
		} catch(SQLException e) {}
		return member;
	}

	@Override
	public String getTableName() {
		return "member";
	}


	
	
	
	
}
