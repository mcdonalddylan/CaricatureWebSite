package com.test.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.caricature.model.User;
import com.caricature.util.DAOConnection;

public class DAOTest {

	private DAOConnection conDAO;
	
	//@Before
	public DAOTest()
	{
		conDAO = new DAOConnection();
	}
	
	@Test
	public void testGetUser()
	{
		User user = new User();
		//assertEquals();
	}
	
	@Test
	public void testConnection() throws SQLException
	{
		assertNotNull(conDAO.getConnection());
	}
}
