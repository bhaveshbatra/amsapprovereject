package com.cognizant.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.ManagerRequest;
@Repository
public class ManagerRequestDAOImpl implements ManagerRequestDAO {
	 
	private SessionFactory factory;
	

	@Override
	public List<ManagerRequest> getAllPendingRequests() {
		// TODO Auto-generated method stub
		Session session=factory.openSession();
		Query query=session.createQuery("from AdminRequest a where a.status='PENDING'");
		List<ManagerRequest> managerRequests=query.list();
		return managerRequests;
	}
	@Override
	public int getManagerPendingCount() {
		Session session=factory.openSession();
		Query query=session.createQuery("SELECT PENDINGCOUNT FROM REQUESTTRACKER");
		BigDecimal count=(BigDecimal)query.uniqueResult();
		// TODO Auto-generated method stub
		return count.intValue();
	}

	@Override
	public int getManagerApprovedCount() {
		// TODO Auto-generated method stub
		Session session=factory.openSession();
		Query query=session.createQuery("Select APPROVEDCOUNT from REQUESTTRACKER");
		BigDecimal count=(BigDecimal)query.uniqueResult();
		return count.intValue();
	}


	@Override
	public boolean approveManagerRequest(int requestId, String managerId) {
		// TODO Auto-generated method stub
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		transaction.begin();
		Query query =session.createQuery("update ManagerRequest a set a.status=:status"
				+ " where a.requestId=:requestId and a.managerId=:managerId");
		query.setParameter("status", "approved");
		query.setParameter("requestId", "requestId");
		query.setParameter("managerId", "managerId");
		int approveStatus=query.executeUpdate();
		transaction.commit();
		if(approveStatus>0)
		
			{
			return true;
			}
		else
		{
			return false;	
		}
		
	}

	@Override
	public boolean rejectManagerRequest(int requestId, String managerId) {
		// TODO Auto-generated method stub
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		transaction.begin();
		Query query =session.createQuery("update ManagerRequest a set a.status=:status"
				+ " where a.requestId=:requestId and a.managerId=:managerId");
		query.setParameter("status", "rejected");
		query.setParameter("requestId", "requestId");
		query.setParameter("managerId", "managerId");
		int rejectStatus=query.executeUpdate();
		transaction.commit();
		if(rejectStatus>0)
		return true;
		else 
			return false;
	}

	
	@Override
	public int getManagerRejectedCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
