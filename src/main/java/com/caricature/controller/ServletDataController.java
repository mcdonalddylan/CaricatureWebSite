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
	
	public boolean sendUserData(HttpServletResponse resp, HttpServletRequest req)
	{
		resp.setContentType("text/json");
		
		HttpSession session = req.getSession();
		User user = new User(0,null,null,null,null,null,0);
		
		if(session.getAttribute("userId") != null)
		{
			user.setId((Integer) session.getAttribute("userId"));
			user.setUsername((String) session.getAttribute("username"));
			user.setFirstName((String) session.getAttribute("firstName"));
			user.setLastName((String) session.getAttribute("lastName"));
			user.setEmail((String) session.getAttribute("userEmail"));
			user.setRole((String) session.getAttribute("userRole"));
		}
		
		//if a user has logged in...
		if (session.getAttribute("userId") != null)
		{
			try {
				resp.getWriter().println(new ObjectMapper().writeValueAsString(user));
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		//if not then output generic user data...
		else
		{
			try {
				User tempUser = new User();
				resp.getWriter().println(new ObjectMapper().writeValueAsString(tempUser));
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
	}
	
	public boolean sendUserReimData(HttpServletResponse resp, HttpServletRequest req)
	{
		resp.setContentType("text/json");
		HttpSession session = req.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		List<Reimbursement> testList = rDAO.getAll(userId);
		if(testList.isEmpty() == false)
		{
			try {
				resp.getWriter().println(new ObjectMapper().writeValueAsString(testList));
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			List<Reimbursement> rList = new ArrayList<>();
			Reimbursement tempReim = new Reimbursement(0,null,null,null,null,0,0,0,0);
			rList.add(tempReim);
			try {
				resp.getWriter().println(new ObjectMapper().writeValueAsString(rList));
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}	
	}
	
	public boolean sendAllReimData(HttpServletResponse resp)
	{
		resp.setContentType("text/json");

		List<Reimbursement> rList = rDAO.getAll();
		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(rList));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean rejectReim(HttpServletResponse resp, HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		int userId = (Integer)session.getAttribute("userId");
		System.out.println("resolver id in session: " + userId);
		
		String reimString = req.getParameter("id");
		int reimId = Integer.parseInt(reimString);
		System.out.println("reim reject id: " + reimId);
		
		if(rDAO.update(reimId, userId, 2)) //2 is the status id for rejection
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean approveReim(HttpServletResponse resp, HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		int userId = (Integer)session.getAttribute("userId");
		System.out.println("resolver id in session: " + userId);
		
		String reimString = req.getParameter("id");
		int reimId = Integer.parseInt(reimString);
		System.out.println("reim approved id: " + reimId);
		
		if(rDAO.update(reimId, userId, 1)) //1 is the status id for approval
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean removeReim(HttpServletResponse resp, HttpServletRequest req)
	{
		String reimString = req.getParameter("id");
		int reimId = Integer.parseInt(reimString);
		System.out.println("reim removed id: " + reimId);
		
		if(rDAO.delete(reimId))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
