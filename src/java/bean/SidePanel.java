/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.entity.Member;
import bean.entity.Vote;
import bean.manager.EntityManager;
import bean.manager.MemberManager;
import bean.manager.VoteManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Zach Bolan
 */
public class SidePanel {

	public List<Member> getAllTopMembers() {
		EntityManager<Member> memberManager = new MemberManager();
		EntityManager<Vote> voteManager = new VoteManager();
		List<Member> members;
		Map<Member, Integer> map = new HashMap<>();	// Member => Number Votes

		// Populate the map and sort
		members = memberManager.find();
		for (Member member : members) {
			List<Vote> votes = voteManager.find("vote_for", String.valueOf(member.getId()));
			map.put(member, votes.size());
		}
		return sort(map);
	}
	
	
	public List<Member> getSearchResults(String query) {
		List<Member> members;
		MemberManager<Member> memberManager = new MemberManager();
		EntityManager<Vote> voteManager = new VoteManager();
		Map<Member, Integer> map = new HashMap<>();
		members = memberManager.search(query);
		
		// Now we sort the list based on the number of Votes
		for (Member member : members) {
			List<Vote> votes = voteManager.find("vote_for", String.valueOf(member.getId()));
			map.put(member, votes.size());
		}
		return sort(map);
	}
	
	
	/**
	 * Sorts an HashMap is descending order by each key's value and returns the
	 * sorted list containing the keys.
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return 
	 */
	private <K, V extends Comparable<? super V>> List<K> sort(Map<K, V> map) {
		
		List<K> result = new ArrayList<>();
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		
		for (Map.Entry<K, V> entry : list) {
			result.add(entry.getKey());
		}
		return result;
	}

}
