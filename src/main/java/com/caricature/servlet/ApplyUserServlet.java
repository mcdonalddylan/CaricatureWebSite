package com.caricature.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caricature.model.User;
import com.caricature.repo.UserDAO;

@WebServlet(name = "apply", urlPatterns = {"/apply"}, loadOnStartup = 0)
public class ApplyUserServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("html/newUser.html").forward(req, resp);
	}
	
	//allows for logging in to take place
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("html/newUser.html").forward(req, resp);
	}
}
