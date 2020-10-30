package com.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.caricature.model.Reimbursement;
import com.caricature.model.User;
import com.caricature.repo.ReimburseDAO;
import com.caricature.repo.UserDAO;
import com.caricature.servlet.LoginServlet;
import com.caricature.util.DAOConnection;

public class DAOTest {

	private DAOConnection conDAO;
	private ReimburseDAO rDAO;
	private UserDAO uDAO;
	private LoginServlet lServ;
	
	
	//@Before
	public DAOTest()
	{
		conDAO = new DAOConnection();
		rDAO = new ReimburseDAO();
		uDAO = new UserDAO();
		lServ = new LoginServlet();
	}
	
	@Test
	public void testConnection() throws SQLException
	{
		assertNotNull(conDAO.getConnection());
	}
	
	@Test
	public void testUserDAO() throws SQLException
	{
		User tempU = new User(6969,"bingy","wingy", "chingy","dodingy","ringy@smingy.chungo", 3);
		assertTrue(uDAO.create(tempU));
		assertTrue(uDAO.isUser("bingy"));
		assertTrue(uDAO.delete(28));
		
		String newPass = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashPass = md.digest("secure".getBytes());
			newPass = lServ.hexString(hashPass);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		User tempU2 = new User(9,"sys", newPass,"Butt","King","totallylegit@dreambutts.com", 3);
		User tempU3 = uDAO.get("sys","secure");
		User tempU4 = uDAO.get(9);
		assertEquals(tempU2, tempU3);
		assertEquals(tempU2, tempU4);
	}
	
	
	
	@Test
	public void testReimDAO() throws SQLException
	{
		LocalDateTime now = LocalDateTime.now();
		Reimbursement tempR = new Reimbursement(500,now,null,"idk",null,9,11,3,1);
		assertTrue(rDAO.create(tempR));
		assertTrue(rDAO.update(9, 9, 2));
		assertTrue(rDAO.delete(9));
		
	}
	
	//Reimbursement tempR2 = rDAO.get(40);
	//assertEquals(tempR, tempR2);
	

}
