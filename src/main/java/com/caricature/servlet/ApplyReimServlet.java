package com.caricature.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.caricature.model.Reimbursement;
import com.caricature.repo.ReimburseDAO;

@WebServlet(name = "applyReim", urlPatterns = {"/applyReim"}, loadOnStartup = 0)
public class ApplyReimServlet extends HttpServlet {

	ReimburseDAO rDAO = new ReimburseDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int typeId = Integer.parseInt(req.getParameter("reim-type"));
		if(typeId == -1)
		{
			System.out.println("None selected");
		}
		
		String desc = req.getParameter("reim-desc");
		float amount = Integer.parseInt(req.getParameter("amount"));
		
		HttpSession session = req.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		Reimbursement reim = new Reimbursement();
		reim.setAmount(amount);
		reim.setSubmitDate(LocalDateTime.now());
		reim.setDescription(desc);
		reim.setAuthorId(userId);
		reim.setResolverId(11);  //this is the temporary "null" user id in the db
		reim.setStatusId(3);
		reim.setTypeId(typeId);
		
		rDAO.create(reim);
		//req.getRequestDispatcher("./reimburse").forward(req, resp);
		resp.sendRedirect("./reimburse");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int typeId = Integer.parseInt(req.getParameter("reim-type"));
		if(typeId == -1)
		{
			System.out.println("None selected");
		}
		
		String desc = req.getParameter("reim-desc");
		float amount = Integer.parseInt(req.getParameter("amount"));
		
		HttpSession session = req.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		Reimbursement reim = new Reimbursement();
		reim.setAmount(amount);
		reim.setSubmitDate(LocalDateTime.now());
		reim.setDescription(desc);
		reim.setAuthorId(userId);
		reim.setResolverId(11);  //this is the temporary "null" user id in the db
		reim.setStatusId(3);
		reim.setTypeId(typeId);
		
		rDAO.create(reim);
		//req.getRequestDispatcher("./reimburse").forward(req, resp);
		resp.sendRedirect("./reimburse");
	}

}
