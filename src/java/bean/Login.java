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
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zach
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean valid = false;
		
		// Request and Session variables
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		session.setAttribute("user", null);

		EntityManager<Member> memberManager = new MemberManager();
		List<Member> members;
		String redirect = "login.jsp";

		// Validate input
		if (username.length() > 0 && password.length() > 0) {
			String regex = "^[a-zA-Z0-9!@#%&]*$";
			if (Pattern.matches(regex, password)) {
				valid = true;
			}
		}
		
		if (valid) {
			members = memberManager.find("username", username);
			
			// Check if the account is already created
			if (request.getParameter("login") != null) {
				for (Member member : members) {
					
					// Login
					if (member.getPassword().equals(password)) {
						session.setAttribute("user", username);
						redirect = "index.jsp";
					}
				}

			// Create a new Member
			} else if (request.getParameter("register") != null) {
				if (members.isEmpty()) {
					// new Member
					Member member = new Member();
					member.setUsername(username);
					member.setPassword(password);
					memberManager.add(member);
					// new Vote
					Vote vote = new Vote();
					member = memberManager.findOne("username", username);
					int id = member.getId();
					System.out.println("***"+id);
					vote.setVoteOwner(id);
					EntityManager<Vote> voteManager = new VoteManager();
					System.out.println("vote manager");
					voteManager.add(vote);
					
					session.setAttribute("user", username);
					redirect = "index.jsp";
				}
			}
		}
		response.sendRedirect(redirect);
	}

}
