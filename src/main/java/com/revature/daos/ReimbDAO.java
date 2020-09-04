package com.revature.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbDAO implements IReimbDAO {
	
	IUserDAO uDao = new UserDAO();

	@Override
	public Reimbursement findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		Reimbursement r = ses.get(Reimbursement.class, id);
		return r;
	}

	@Override
	public List<Reimbursement> findAll() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement").list();
		return list;
	}

	@Override
	public ReimbursementStatus findStatus(int id) {
		Session ses = HibernateUtil.getSession();
		
		ReimbursementStatus status = ses.get(ReimbursementStatus.class, id);
		return status;
	}

	@Override
	public ReimbursementType findType(int id) {
		Session ses = HibernateUtil.getSession();
		
		ReimbursementType type = ses.get(ReimbursementType.class, id);
		return type;
	}

	@Override
	public boolean addReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.save(r);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Reimbursement> findByAuthor(int id) {
		Session ses = HibernateUtil.getSession();
		
		User u = uDao.findById(id);
		Query query = ses.createQuery("FROM Reimbursement where author=:author",Reimbursement.class);
		query.setParameter("author", u);
		
		return query.getResultList();
	}

}
