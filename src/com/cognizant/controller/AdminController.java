package com.cognizant.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.entity.Admin;
import com.cognizant.entity.Manager;
import com.cognizant.entity.ManagerRequest;
import com.cognizant.service.AdminLoginService;
import com.cognizant.service.ManagerRequestService;
import com.cognizant.service.NotificationService;


@Controller
public class AdminController {
	@Autowired
	private AdminLoginService adminLoginService;
	
	@Autowired@Qualifier("AdminValidator")
	private Validator adminvalidator;
	
	@Autowired
	private ManagerRequestService managerRequestService;
	
	@Autowired
	private NotificationService notificationService;

	
	@RequestMapping(value ="index.htm")
	public String viewHome(){
		
		return "home";
	}
	
	@RequestMapping(value="register.htm")
	public String viewRegisterAdmin(){
		return "Registration";
	}
	
	@RequestMapping(value ="registrationadmin.htm", method = RequestMethod.POST)
	public ModelAndView getRegistrationValue(@ModelAttribute("admin")Admin admin){
		ModelAndView mv = new ModelAndView();
		boolean persistAdmin=adminLoginService.addAdmin(admin);
		if(persistAdmin)
		{
			mv.addObject("status","Admin Registered");
			mv.setViewName("home");
		}
		else{
			mv.addObject("status","Admin Registration Failed");
		mv.setViewName("Registration");
		}
		return mv;
	}
	
	@RequestMapping(value="login.htm")
	public String LoadLoginPage(){
		return "login";
	}
	
	@RequestMapping(value="doLogin.htm",method=RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("admin") Admin admin)
	{
		
		ModelAndView mv=new ModelAndView();
		boolean checkAdmin=adminLoginService.addUser(admin.getId(),admin.getPassword());
		if(checkAdmin)
		{
			List<Admin> adminList=adminLoginService.getAdminDetails(admin.getId());
			System.out.println("List Passed"+adminList);
			mv.addObject("adminList",adminList);
			mv.setViewName("welcome");
		}
		else
		{
			mv.addObject("status","Login Failed");
			mv.setViewName("notfound");
		}
		return mv;
	}
	
	/*@RequestMapping(value="adminPendingRequests.htm",method=RequestMethod.POST)
	public ModelAndView viewdetails(@ModelAttribute("manager") Manager manager)
	{
		ModelAndView mv=new ModelAndView();
		List<Manager> managerList=adminLoginService.getManagerDetails(manager.getManager_id());
		mv.addObject("managerList",managerList);
		mv.setViewName("managerdetails");
		return mv;
	}*/
	
	@RequestMapping(value="managerPendingRequests.htm",method=RequestMethod.POST)
	public ModelAndView getAllManagerrequests()
	{
		ModelAndView mv=new ModelAndView();
		List<ManagerRequest> managerPendingRequests=managerRequestService.getAllPendingRequests();
		mv.addObject("managerPendingRequests",managerPendingRequests);
		int pendingRequests=NotificationService.pendingRequestsCount;
		mv.addObject("pendingCount",pendingRequests);
		mv.setViewName("managerdetails");
		return mv;
	}
	
	@RequestMapping(value="changeRequestStatus.htm",method=RequestMethod.GET)
	public ModelAndView changeRequestStatus(@RequestParam("requestId")int requestId,@RequestParam("managerId")String managerId,@RequestParam("action")String action){
		ModelAndView mv=new ModelAndView();
		if(action.equals("approve"))
		{
			boolean approveStatus=managerRequestService.ApproveManagerRequest(requestId, managerId);
			if(approveStatus)
				mv.addObject("status","Approved");
		} else
		{
			boolean rejectStatus=managerRequestService.RejectManagerRequest(requestId, managerId);
			if(rejectStatus)
				mv.addObject("status","Rejected");
		}
		List <ManagerRequest> managerPendingRequests=managerRequestService.getAllPendingRequests();
		mv.addObject("managerPendingRequests",managerPendingRequests);
		int pendingRequests=NotificationService.pendingRequestsCount;
		mv.addObject("pendingCount",pendingRequests);
		mv.setViewName("managerdetails");
		return mv;
	}
	
	@ModelAttribute("admin")
	public Admin createAdminObject(){
		Admin admin = new Admin();
		return admin;
	}
	
}