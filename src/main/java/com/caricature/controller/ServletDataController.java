package com.caricature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.caricature.repo.ReimburseDAO;
import com.caricature.repo.UserDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.caricature.model.Reimbursement;
import com.caricature.model.User;

public class ServletDataController {
	
	//basically the session user
	private static ServletDataController instance = null;
	private User sessionUser = null;
	private ReimburseDAO rDAO = new ReimburseDAO();
	
	public ServletDataController() {
		super();
	}
	public static ServletDataController getInstance()
	{
		if (instance == null)
			instance = new ServletDataController();
		
		return instance;
	}

	public void setUser(User newUser)
	{
		sessionUser = newUser;
	}
	
	public void sendUserData(HttpServletResponse resp, HttpServletRequest req)
	{
		resp.setContentType("text/json");
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute("userId"));
		System.out.println(session.getAttribute("username"));
		System.out.println(session.getAttribute("firstName"));
		System.out.println(session.getAttribute("lastName"));
		//if a user has logged in...
		if (session.getAttribute("userId") != null)
		{
			try {
				resp.getWriter().println(new ObjectMapper().writeValueAsString(sessionUser));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//if not then output generic user data...
		else
		{
			try {
				User tempUser = new User();
				resp.getWriter().println(new ObjectMapper().writeValueAsString(tempUser));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void sendUserReimData(HttpServletResponse resp, HttpServletRequest req)
	{
		resp.setContentType("text/json");
		HttpSession session = req.getSession();
		
		if(session.getAttribute("userId") != null)
		{
			List<Reimbursement> rList = rDAO.getAll(sessionUser.getId());
			try {
				resp.getWriter().println(new ObjectMapper().writeValueAsString(rList));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			List<Reimbursement> rList = new ArrayList<>();
			Reimbursement tempReim = new Reimbursement(0,0,null,null,null,null,0,0,0,0);
			rList.add(tempReim);
			try {
				resp.getWriter().println(new ObjectMapper().writeValueAsString(rList));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void sendAllReimData(HttpServletResponse resp)
	{
		resp.setContentType("text/json");

		List<Reimbursement> rList = rDAO.getAll();
		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(rList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
