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
public class Vote extends Entity {
	
	private int		voteOwner = 0;
	private int		voteFor = 0;

	
	public Vote() {
		super();
		
		properties.put("vote_owner", voteOwner);
		properties.put("vote_for", voteFor);
	}
	
	public int getVoteOwner() {
		return voteOwner;
	}

	public void setVoteOwner(int voteOwner) {
		this.voteOwner = voteOwner;
		this.properties.put("vote_owner", voteOwner);
	}

	public int getVoteFor() {
		return voteFor;
	}

	public void setVoteFor(int voteFor) {
		this.voteFor = voteFor;
		this.properties.put("vote_for", voteFor);
	}


	@Override
	public Vote create(ResultSet result) {
		Vote vote = new Vote();
		try {
			vote.setId(result.getInt("id"));
			vote.setVoteFor(result.getInt("vote_for"));
			vote.setVoteOwner(result.getInt("vote_owner"));
		} catch(SQLException e) {}
		return vote;
	}

	@Override
	public String getTableName() {
		return "vote";
	}
	
	
}
