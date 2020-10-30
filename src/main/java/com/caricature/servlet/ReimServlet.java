package com.caricature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplyReimServlet
 */
@WebServlet(name = "reimburse", urlPatterns = {"/reimburse"}, loadOnStartup = 0)
public class ReimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//System.out.println("The users role: " + req.getSession().getAttribute("userRole").toString());
		//System.out.println("Is the user actually a manager?: " + req.getSession().getAttribute("userRole").equals("Manager"));
		
		if(req.getSession().getAttribute("userRole").equals("Employee"))
		{
			req.getRequestDispatcher("html/reimburseEmp.html").forward(req, resp);
		}
		else if (req.getSession().getAttribute("userRole").equals("System"))
		{
			req.getRequestDispatcher("html/reimburseMan.html").forward(req, resp);
		}
		else if (req.getSession().getAttribute("userRole").equals("Manager"))
		{
			req.getRequestDispatcher("html/reimburseMan.html").forward(req, resp);
		}
		else
		{
			req.getRequestDispatcher("html/index.html").forward(req, resp);
		}
		
	}

}
