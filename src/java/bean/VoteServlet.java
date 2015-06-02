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
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zach Bolan
 */
@WebServlet(urlPatterns = {"/DoVote"}, name = "/DoVote/")
public class VoteServlet extends HttpServlet {



	/**
	 * Handles the HTTP <code>Post</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ballotOwner = (String) request.getSession().getAttribute("user");
		String ballotFor = request.getParameter("voteFor");
		
		EntityManager<Member> memberManager = new MemberManager();
		EntityManager<Vote> voteManager = new VoteManager();
		Member voteOwner, voteFor;
		Vote vote;
		
		voteOwner = memberManager.findOne("username", ballotOwner);
		voteFor =	memberManager.findOne("username", ballotFor);
		
		if (voteOwner != null && voteFor != null && voteOwner != voteFor) {
			vote = voteManager.findOne("vote_owner", Integer.toString(voteOwner.getId()));
			vote.setVoteFor(voteFor.getId());
			voteManager.update(vote);
		}
		response.sendRedirect("profile.jsp?user="+ballotFor);
	}


}
