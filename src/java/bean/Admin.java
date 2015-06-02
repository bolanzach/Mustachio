/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.entity.Image;
import bean.entity.Member;
import bean.manager.EntityManager;
import bean.manager.ImageManager;
import bean.manager.MemberManager;
import java.util.List;

/**
 *
 * @author Zach Bolan
 */
public class Admin {
	
	
	public List<Member> getAllMembers() {
		EntityManager<Member> manager = new MemberManager();
		return manager.find();
	}
	
	public boolean isAdmin(Member member) {
		if (member.isIsAdmin() == 1) {
			return true;
		}
		return false;
	}
	
	public boolean isFlagged(Member member) {
		if (member.isIsFlagged() == 1) {
			return true;
		}
		return false;
	}
	
	public List<Image> getImages(Member member) {
		EntityManager<Image> manager = new ImageManager();
		return manager.find("member_id", Integer.toString(member.getId()));
	}
	
	
	
	
}
