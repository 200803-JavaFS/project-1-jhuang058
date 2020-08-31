package com.revature.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginControllerr;

public class MasterServlett extends HttpServlet {
	
	private static LoginControllerr lc = new LoginControllerr();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		// By default tomcat will send back a successful status code if it finds a
		// servlet method.
		// Because all requests will hit this method, we are defaulting to not found and
		// will override for success requests.
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/project0/", "");

		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));

		// URI = avenger/1
		try {
			switch (portions[0]) {
//			case "avenger":
//				if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
//					if (req.getMethod().equals("GET")) {
//						if (portions.length == 2) {
//							int id = Integer.parseInt(portions[1]);
//							ac.getAvenger(res, id);
//						} else if (portions.length == 1) {
//							ac.getAllAvengers(res);
//						}
//					} else if (req.getMethod().equals("POST")) {
//						ac.addAvenger(req, res);
//					}
//				} else {
//					res.setStatus(403);
//					res.getWriter().println("You must be logged in to do that!");
//				}
//				break;
			case "login":
				lc.login(req, res);
				break;
			case "logout":
				lc.logout(req, res);
				break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.getWriter().print("The id you provided is not an integer");
			res.setStatus(400);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("application/json");
		// By default tomcat will send back a successful status code if it finds a
		// servlet method.
		// Because all requests will hit this method, we are defaulting to not found and
		// will override for success requests.
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/project0/", "");

		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));

		// URI = avenger/1
		try {
			switch (portions[0]) {
//			case "avenger":
//				if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
//					if (req.getMethod().equals("GET")) {
//						if (portions.length == 2) {
//							int id = Integer.parseInt(portions[1]);
//							ac.getAvenger(res, id);
//						} else if (portions.length == 1) {
//							ac.getAllAvengers(res);
//						}
//					} else if (req.getMethod().equals("POST")) {
//						ac.addAvenger(req, res);
//					}
//				} else {
//					res.setStatus(403);
//					res.getWriter().println("You must be logged in to do that!");
//				}
//				break;
			case "login":
				lc.login(req, res);
				break;
			case "logout":
				lc.logout(req, res);
				break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.getWriter().print("The id you provided is not an integer");
			res.setStatus(400);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
