package com.caricature.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.caricature.controller.ServletDataController;
import com.caricature.model.User;

@WebServlet(name = "logout", urlPatterns = {"/logout"}, loadOnStartup = 0)
public class LogoutServlet extends HttpServlet {
	
	//allows for logging in to take place
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
