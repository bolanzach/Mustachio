/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.entity.Image;
import bean.entity.Member;
import bean.entity.Vote;
import bean.manager.EntityManager;
import bean.manager.ImageManager;
import bean.manager.MemberManager;
import bean.manager.VoteManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import util.RecentEntityComparator;

/**
 *
 * @author Zach Bolan
 */
public class Home {
	
	
	
	public List<Image> getNewestImages() {
            System.out.println("HOME");
		List<Image> images;
		EntityManager<Image> imageManager = new ImageManager();
		
		images = imageManager.find();
		Comparator<Image> comparator = new RecentEntityComparator<>();
		Collections.sort(images, comparator);
		return images;
	}
	
	public Member getMember(int id) {
		EntityManager<Member> manager = new MemberManager();
		Member member = manager.findOne("id", Integer.toString(id));
		return member;
	}
	
	public Member getMember(String username) {
		EntityManager<Member> manager = new MemberManager();
		Member member = manager.findOne("username", username);
		return member;
	}
	
	public int getMemberVotes(String username) {
		EntityManager<Member> memberManager = new MemberManager();
		EntityManager<Vote> voteManager = new VoteManager();
		Member member = memberManager.findOne("username", username);
		return voteManager.find("vote_for", Integer.toString(member.getId())).size();
	}
	
	public List<Image> getImages(Member member){
		EntityManager<Image> manager = new ImageManager();
		return manager.find("member_id", Integer.toString(member.getId()));
	}
	
	
}
