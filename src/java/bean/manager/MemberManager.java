/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.manager;

import bean.entity.Entity;
import bean.entity.Member;
import java.util.List;


/**
 *
 * @author Zach Bolan
 */
public class MemberManager<T extends Member> extends EntityManager {

	public MemberManager() {
		super(new Member());
	}


	public List<Member> search(String query) {
		List<Member> members;
		String sql = "SELECT * FROM member WHERE username LIKE '%";
		sql += query + "%'";
		members = this.query(sql);
		return members;
	}
	
	
}
