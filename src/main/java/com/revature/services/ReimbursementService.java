package com.revature.services;

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

}
