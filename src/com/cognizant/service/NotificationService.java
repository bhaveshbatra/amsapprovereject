package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cognizant.dao.ManagerRequestDAO;

@Service
public class NotificationService {
	
	@Autowired
	private ManagerRequestDAO managerRequestDAO;
	public static int pendingRequestsCount=0;
	
	@Scheduled(fixedRate=2000)
	public void checkPendingRequests(){
		NotificationService.pendingRequestsCount=managerRequestDAO.getManagerPendingCount();
		
	}

}
