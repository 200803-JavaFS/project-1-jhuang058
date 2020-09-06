package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbDTO;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementController {
	
	private static ReimbursementService rs = new ReimbursementService();
	private static ObjectMapper om = new ObjectMapper();

	public void getReimbursement(HttpServletResponse res, int id) throws IOException {
		Reimbursement r = rs.findById(id);
		if (r == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(r);
			res.getWriter().println(json);
		}
		
	}
	
	public void getReimbByAuthor(HttpServletResponse res, int id) throws IOException {
		List<Reimbursement> list = rs.findByAuthor(id);
		
		res.setStatus(200);
		String json = om.writeValueAsString(list);
		res.getWriter().println(json);
		
	}

	public void getAllReimbursements(HttpServletResponse res) throws IOException {
		List<Reimbursement> all = rs.findAll();
		res.getWriter().println(om.writeValueAsString(all));
		res.setStatus(200);
		
	}

	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		//public ReimbDTO(double amount, String description, (String receipt), String author, int type)
		//public ReimbDTO(double amount, Timestamp submitted, String resolved, String description, String receipt,
		//String author, String resolver, int status, int type)
		ReimbDTO rd = om.readValue(body, ReimbDTO.class);
		rd.submitted = new Timestamp(System.currentTimeMillis());
		rd.status = 1;
		
		if (rs.addReimbursement(rd)) {
			res.setStatus(201);
			res.getWriter().println("Reimbursement was submitted");
		} else {
			res.setStatus(403);
		}
		
	}

	public void getReimbByStatus(HttpServletResponse res, int id) throws IOException {
		List<Reimbursement> list = rs.findByStatus(id);
		
		res.setStatus(200);
		String json = om.writeValueAsString(list);
		res.getWriter().println(json);
		
	}

	public void updateReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		ReimbDTO rd = om.readValue(body, ReimbDTO.class);
		
		if (rs.updateReimbursement(rd)) {
			res.setStatus(201);
			res.getWriter().println("Reimbursement was submitted");
		} else {
			res.setStatus(403);
		}
		
	}

}
