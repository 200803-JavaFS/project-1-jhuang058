package com.revature.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginControllerr;
import com.revature.controllers.ReimbursementController;

public class MasterServlett extends HttpServlet {
	
	private static LoginControllerr lc = new LoginControllerr();
	private static ReimbursementController rc = new ReimbursementController();

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

		// URI = reimbursement/1
		try {
			switch (portions[0]) {
			case "reimbursement":
				if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
					if (req.getMethod().equals("GET")) {
						if (portions.length == 2) {
							if (portions[1].equals("pending")) {
								rc.getReimbByStatus(res, 1);
							} else if (portions[1].equals("approved")) {
								rc.getReimbByStatus(res, 2);	
							} else if (portions[1].equals("denied")) {
								rc.getReimbByStatus(res, 3);
							}else {
								int id = Integer.parseInt(portions[1]);
								rc.getReimbByAuthor(res, id);
							}
						} else if (portions.length == 1) {
							rc.getAllReimbursements(res);
						}
					} else if (req.getMethod().equals("POST")) {
							rc.addReimbursement(req, res);
					}
				} else {
					res.setStatus(403);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
			case "update":
				rc.updateReimbursement(req, res);
				break;
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
		
		doGet(req, res);
		
	}

}
