package com.test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.caricature.controller.ServletDataController;
import com.caricature.model.User;
import com.caricature.repo.ReimburseDAO;
import com.caricature.repo.UserDAO;
import com.caricature.util.DAOConnection;

public class ControllerTest {

	private ServletDataController servData;
	
	//@Before
	public ControllerTest()
	{
		servData = new ServletDataController();
	}
	
	/*
	@Test
	public void testSendUser() throws SQLException
	{
		assertFalse(servData.sendUserData(null, null));
	}
	*/
}
