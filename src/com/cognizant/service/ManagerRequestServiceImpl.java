package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dao.ManagerRequestDAO;
import com.cognizant.entity.ManagerRequest;
@Service
public class ManagerRequestServiceImpl implements ManagerRequestService {
	
	@Autowired
	private ManagerRequestDAO managerRequestDAO;

	@Override
	public List<ManagerRequest> getAllPendingRequests() {
		// TODO Auto-generated method stub
		return managerRequestDAO.getAllPendingRequests();
	}

	@Override
	public int getManagerPendingCount() {
		// TODO Auto-generated method stub
		return managerRequestDAO.getManagerPendingCount();
	}

	@Override
	public int getManagerApprovedCount() {
		// TODO Auto-generated method stub
		return managerRequestDAO.getManagerApprovedCount();
	}

	@Override
	public boolean ApproveManagerRequest(int requestId, String managerId) {
		// TODO Auto-generated method stub
		return managerRequestDAO.approveManagerRequest(requestId, managerId);
		
	}

	@Override
	public boolean RejectManagerRequest(int requestId, String managerId) {
		// TODO Auto-generated method stub
		return managerRequestDAO.rejectManagerRequest(requestId, managerId);
	}

}
