package com.revature.daos;

import java.util.List;

import com.revature.models.ReimbDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;

public interface IReimbDAO {

	Reimbursement findById(int id);

	List<Reimbursement> findAll();

	ReimbursementStatus findStatus(int id);

	ReimbursementType findType(int id);

	boolean addReimbursement(Reimbursement r);

	List<Reimbursement> findByAuthor(int id);

	List<Reimbursement> findByStatus(int id);

	boolean updateReimbursement(Reimbursement r);

}
