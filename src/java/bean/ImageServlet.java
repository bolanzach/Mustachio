/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.entity.Image;
import bean.manager.EntityManager;
import bean.manager.ImageManager;
import db.DBAccess;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zach Bolan
 */
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String imgId = request.getPathInfo().substring(1);
		
		if (imgId != null) {
			EntityManager<Image> imageManager = new ImageManager();
			Image img = imageManager.findOne("id", imgId);
			byte[] data = img.getData();
                        for (int i=0; i<data.length; i++) {
                            System.out.println(data[i]);
                        }
                          
			response.setContentType("image/jpeg");
			response.setContentLength(data.length);
			response.getOutputStream().write(data);
                        //response.getOutputStream().close();
		}
	}




}
