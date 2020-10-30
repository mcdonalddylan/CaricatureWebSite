package com.caricature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caricature.model.User;
import com.caricature.repo.UserDAO;

@WebServlet(name = "createUser", urlPatterns = {"/createUser"}, loadOnStartup = 0)
public class CreateUserServlet extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDAO uDAO = new UserDAO();
		String username = req.getParameter("user-entry");
		String password = req.getParameter("pass-entry");
		String email = req.getParameter("email-entry");
		String firstName = req.getParameter("first-entry");
		String lastName = req.getParameter("last-entry");
		
		if(uDAO.isUser(username) == false)
		{
			User user = new User(0,username,password,firstName,lastName,email,1);
			uDAO.create(user);
			
		}
		else
		{
			System.out.println("username already exists");
		}
	}
}