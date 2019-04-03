package com.cognizant.service;

import java.util.List;

import com.cognizant.entity.ManagerRequest;

public interface ManagerRequestService {
	
	public List<ManagerRequest> getAllPendingRequests();
	public int getManagerPendingCount();
	public int getManagerApprovedCount();
	public boolean ApproveManagerRequest(int requestId, String managerId);
	public boolean RejectManagerRequest(int requestId, String managerId);

}
