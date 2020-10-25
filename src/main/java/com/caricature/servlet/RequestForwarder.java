package com.caricature.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caricature.controller.ServletDataController;

public class RequestForwarder {

	public void data(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		switch(req.getRequestURI()) {
		case "/Caricature/user.json":
			ServletDataController.getInstance().sendUserData(resp, req);
			break;
		case "/Caricature/userReim.json":
			ServletDataController.getInstance().sendUserReimData(resp, req);
			break;
		case "/Caricature/allReim.json":
			ServletDataController.getInstance().sendAllReimData(resp);
			break;
		}
	}
}
