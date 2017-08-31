package com.zhiyou100.video.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	AdminService as;
	
	
	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public String userLogin(){
		return "admin/index";
	}
	
	
	@RequestMapping(value="/index.action",method=RequestMethod.POST)
	public String adminlogin(Admin av,HttpServletRequest req,HttpServletResponse res){
		Admin a =as.login(av);
		System.out.println(a);
		if(a == null){
			req.setAttribute("errorMessage", "用户名密码不匹配");
			return "admin/index";
		}else {
			req.getSession().setAttribute("admin", a);
			return "admin/video";
		}
	}
	
}
