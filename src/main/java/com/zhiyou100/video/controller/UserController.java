package com.zhiyou100.video.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhiyou100.video.model.Status;
import com.zhiyou100.video.model.User;
import com.zhiyou100.video.service.UserService;
import com.zhiyou100.video.util.MailUtil;
import com.zhiyou100.video.util.TransToJson;

@Controller
@RequestMapping("/front/user")
public class UserController {
	
	@Autowired
	UserService us;
	
	TransToJson tt = new TransToJson();

	@RequestMapping("/index.action")
	public String uIndex(){
		return "front/index";
	}
	
	@RequestMapping(value="/checkRegist.action",method=RequestMethod.POST)
	@ResponseBody
	public String regist(User u,HttpSession se) throws JsonProcessingException{
		List<User> list = us.findEmailByMail(u.getEmail());
		Status status = new Status();
		if(list.isEmpty()){
			us.addUser(u);
			se.setAttribute("user", u);
			status.setSuccess(true);
			String json = tt.transToJson(status);
			return json;
		}
		else{
			status.setMessage("邮箱已存在!");
			String json = tt.transToJson(status);
			return json;
		}
	}
	
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	@ResponseBody
	public String login(User u,HttpSession se) throws JsonProcessingException{
		List<User> list = us.userLogin(u);
		Status status = new Status();
		if(list.isEmpty()){
			status.setMessage("用户和密码不匹配");
			String json = tt.transToJson(status);
			return json;
		}else{
			User user = list.get(0);
			se.setAttribute("user", user);
			status.setSuccess(true);
			String json = tt.transToJson(status);
			return json;
		}
	}
	
	
	@RequestMapping(value="/forgetpwd.action",method=RequestMethod.GET)
	public String forgetpwd(){
		return "front/user/forget_pwd";
	}
	
	
	@RequestMapping(value="/forgetpwd.action",method=RequestMethod.POST)
	public String modifi(){
		return "front/user/reset_pwd";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/sendemail.action")
	@ResponseBody
	public String sendEmail(String email) throws Exception{
		Status status = new Status();
		List<User> list = us.findEmailByMail(email);
		if(list.isEmpty()){
			status.setMessage("邮箱不正确");
			String json = tt.transToJson(status);
			return json;
		}else{
			String code = UUID.randomUUID().toString().substring(0, 4);
			list.get(0).setCaptcha(code);
			us.updateUser(list.get(0));
			MailUtil mail = new MailUtil();
			mail.send(email, "验证码", code);
			status.setSuccess(true);
			String json = tt.transToJson(status);
			return json;
		}
	}
	
	
}
