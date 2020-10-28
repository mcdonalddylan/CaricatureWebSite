package com.caricature.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.caricature.controller.ServletDataController;
import com.caricature.model.User;
import com.caricature.repo.UserDAO;

@WebServlet(name = "login", urlPatterns = {"/login"}, loadOnStartup = 0)
public class LoginServlet extends HttpServlet {

	static Logger logger = Logger.getLogger(LoginServlet.class);
	
	private UserDAO userDAO = null;
	
	//protected static Logger log = Logger.getLogger(HomeServlet.class);
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	//allows for logging in to take place
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if(username != "" && password != "")
		{
			if(userDAO.isUser(username))
			{
				String newPass = password;
				logger.info("Username was found!");
				
				//convert password to hashcode
				try {
					
					MessageDigest md = MessageDigest.getInstance("MD5");
					byte[] hashPass = md.digest(password.getBytes());
					newPass = hexString(hashPass);
					
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
				User user = userDAO.get(username, newPass);
				if(user != null)
				{
					//user found so you can actually log the user in
					ServletDataController.getInstance().setUser(user);
					//ServletDataController.getInstance().sendUserData(resp);
					HttpSession session = req.getSession();
					session.setAttribute("userId", user.getId());
					session.setAttribute("userEmail", user.getEmail());
					session.setAttribute("username", username);
					session.setAttribute("lastName", user.getLastName());
					session.setAttribute("firstName", user.getFirstName());
					session.setAttribute("userRole", user.getRole());
					
					logger.info("USER: " + user.getFirstName() + " LOGGED IN!");
					
					//reload the page
					req.getRequestDispatcher("html/index.html").forward(req, resp);
					//req.getRequestDispatcher(req.getRequestURI()).forward(req, resp);
				}
				else
				{
					//print onto the error paragraph that the user wasn't found
					logger.error("passwords did not match");
					HttpSession session = req.getSession();
					session.setAttribute("userId", null);
					session.setAttribute("username", null);
					session.setAttribute("lastName", null);
					session.setAttribute("firstName", null);
					session.setAttribute("userRole", null);
					session.setAttribute("userEmail", null);
					
					req.getRequestDispatcher("html/index.html").forward(req, resp);
				}
			}
			else
			{
				//username not found
				logger.error("username not found.");
				HttpSession session = req.getSession();
				session.setAttribute("userId", null);
				session.setAttribute("username", null);
				session.setAttribute("lastName", null);
				session.setAttribute("firstName", null);
				session.setAttribute("userRole", null);
				session.setAttribute("userEmail", null);
				
				req.getRequestDispatcher("html/index.html").forward(req, resp);
			}
		}
		else
		{
			//one or both of the fields were not even filled out
			logger.error("please enter something into both fields.");
			HttpSession session = req.getSession();
			session.setAttribute("userId", null);
			session.setAttribute("username", null);
			session.setAttribute("lastName", null);
			session.setAttribute("firstName", null);
			session.setAttribute("userRole", null);
			session.setAttribute("userEmail", null);
			
			req.getRequestDispatcher("html/index.html").forward(req, resp);
		}
		
	}
	
	
	//function found at: https://stackoverflow.com/questions/1033947/mysql-md5-and-java-md5-not-equal
	//meant to encode the hashed password into base 16 to match the password in the db.
	public static String hexString( byte[] bytes ) 
	{
	  StringBuffer sb = new StringBuffer();
	  for( int i=0; i<bytes.length; i++ )     
	  {
	     byte b = bytes[ i ];
	     String hex = Integer.toHexString((int) 0x00FF & b);
	     if (hex.length() == 1) 
	     {
	        sb.append("0");
	     }
	     sb.append( hex );
	  }
	  return sb.toString();
	}
}
