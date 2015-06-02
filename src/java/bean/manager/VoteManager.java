/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.manager;

import bean.entity.Entity;
import bean.entity.Vote;
import java.util.List;

/**
 *
 * @author Zach Bolan
 */
public class VoteManager extends EntityManager {

	public VoteManager() {
		super(new Vote());
	}
	
	
	public List<Vote> groupVotes() {
		List<Vote> votes;
		String sql = "SELECT * FROM " + entity.getTableName();
		sql += " ";
		votes = this.query(null);
		return votes;
	}

	

	
}
