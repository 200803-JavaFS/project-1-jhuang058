package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	private static final Logger log = LogManager.getLogger(ReimbursementService.class);
	private static IReimbDAO rDao = new ReimbDAO();
	private static IUserDAO uDao = new UserDAO();

	public Reimbursement findById(int id) {
		log.info("finding reimbursement with id "+id);
		return rDao.findById(id);
	}
	
	public List<Reimbursement> findByAuthor(int id) {
		log.info("finding reimbursements with author id "+id);
		return rDao.findByAuthor(id);
	}

	public List<Reimbursement> findAll() {
		log.info("finding all reimbursements");
		return rDao.findAll();
	}

	public boolean addReimbursement(ReimbDTO rd) {
		log.info("adding reimbursement to database");
		Reimbursement r;

		User author = uDao.findById(rd.author);
		ReimbursementStatus status = rDao.findStatus(1);
		ReimbursementType type = rDao.findType(rd.type);
		r = new Reimbursement(rd.amount, rd.submitted, null, rd.description, null, author, null, status, type);
		
		return rDao.addReimbursement(r);
	}

	public List<Reimbursement> findByStatus(int id) {
		log.info("finding reimbursements of status id "+id);
		return rDao.findByStatus(id);
	}

	public boolean updateReimbursement(ReimbDTO rd) {
		log.info("updating reimbursement");
		
		Reimbursement r = rDao.findById(rd.id);
		
		r.setStatus(rDao.findStatus(rd.status));
		r.setResolver(uDao.findById(rd.resolver));
		r.setResolved(new Timestamp(System.currentTimeMillis()));
		
		return rDao.updateReimbursement(r);
	}

}
