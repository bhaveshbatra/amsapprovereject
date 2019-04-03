package com.cognizant.dao;

import java.util.List;

import com.cognizant.entity.ManagerRequest;

public interface ManagerRequestDAO {
	
	public List<ManagerRequest> getAllPendingRequests();
	public boolean approveManagerRequest(int requestId, String managerId);
	public boolean rejectManagerRequest(int requestId, String managerId);
	public int getManagerPendingCount();
	public int getManagerApprovedCount();
	public int getManagerRejectedCount();
	

}
