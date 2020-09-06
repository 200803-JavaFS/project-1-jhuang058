package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import com.revature.daos.IReimbDAO;
import com.revature.daos.IUserDAO;
import com.revature.daos.ReimbDAO;
import com.revature.daos.UserDAO;
import com.revature.models.ReimbDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;

public class ReimbursementService {
	
	private static IReimbDAO rDao = new ReimbDAO();
	private static IUserDAO uDao = new UserDAO();

	public Reimbursement findById(int id) {
		return rDao.findById(id);
	}
	
	public List<Reimbursement> findByAuthor(int id) {
		return rDao.findByAuthor(id);
	}

	public List<Reimbursement> findAll() {
		return rDao.findAll();
	}

	public boolean addReimbursement(ReimbDTO rd) {
		Reimbursement r;

		User author = uDao.findById(rd.author);
		ReimbursementStatus status = rDao.findStatus(1);
		ReimbursementType type = rDao.findType(rd.type);
		r = new Reimbursement(rd.amount, rd.submitted, null, rd.description, null, author, null, status, type);
		System.out.println(r);
		
		return rDao.addReimbursement(r);
	}

	public List<Reimbursement> findByStatus(int id) {
		return rDao.findByStatus(id);
	}

	public boolean updateReimbursement(ReimbDTO rd) {
		Reimbursement r = rDao.findById(rd.id);
		
		System.out.println("before update: "+r);
		
		r.setStatus(rDao.findStatus(rd.status));
		r.setResolver(uDao.findById(rd.resolver));
		r.setResolved(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("to be updated: "+r);
		
		return rDao.updateReimbursement(r);
	}

}
