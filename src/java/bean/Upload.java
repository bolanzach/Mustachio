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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Zach Bolan
 */
@WebServlet(name = "Upload", urlPatterns = {"/Upload"})
@MultipartConfig(maxFileSize = 16177215)	// For uploading Files
public class Upload extends HttpServlet {


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
		
		ImageManager<Image> manager = new ImageManager();
		EntityManager<Member> memberManager = new MemberManager();
		Image image;
		String user = (String) request.getSession().getAttribute("user");
		Member member = memberManager.findOne("username", user);
		InputStream inputStream;
		Part filePart = request.getPart("file");
		String redirect =  "uploadPhoto.jsp";
		
		if (filePart != null) {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int read;
			byte[] data = new byte[16384];
			inputStream = filePart.getInputStream();
			while ((read = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, read);
			}
			buffer.flush();
			
			image = new Image();
			image.setData(data);
			image.setAlt("");
			image.setOwner(member.getId());
			image.setMime(filePart.getContentType());
			if (manager.add(image)) redirect = "index.jsp";
		}
		response.sendRedirect(redirect);
	}


}
