package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.LoginServicee;

public class LoginControllerr {

	private static LoginServicee ls = new LoginServicee();
	private static ObjectMapper om = new ObjectMapper();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException, NoSuchAlgorithmException {

			BufferedReader reader = req.getReader();
			
			StringBuilder sb = new StringBuilder();
			
			String line = reader.readLine();
			
			while (line != null) {
				sb.append(line);
				line=reader.readLine();
			}
			
			String body = new String(sb);
			
			User u = om.readValue(body, User.class); //readValue() takes JSON data and put it in an object you specify
			
			if (ls.login(u) != null) {
				User user = ls.login(u);
				HttpSession ses = req.getSession(); // creates a new session
				ses.setAttribute("user", u);
				ses.setAttribute("loggedin", true);
				res.setStatus(200);
				res.getWriter().println(om.writeValueAsString(user));
			} else {
				HttpSession ses = req.getSession(false);
				if (ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login failed");
			}
		
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession ses = req.getSession(false);

		if (ses != null) {
			User u = (User) ses.getAttribute("user");
			res.setStatus(200);
			res.getWriter().println(u.getUsername() + " has logged out successfully");
		} else {
			res.setStatus(400);
			res.getWriter().println("You must be logged in to logout!");
		}
	}

}
