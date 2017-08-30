package com.zhiyou100.video.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhiyou100.video.model.Status;
import com.zhiyou100.video.model.User;
import com.zhiyou100.video.service.UserService;
import com.zhiyou100.video.util.MD5Utils;
import com.zhiyou100.video.util.MailUtil;
import com.zhiyou100.video.util.TransToJson;

@Controller
@RequestMapping("/front/user")
public class UserController {
	
	@Autowired
	UserService us;
	
	TransToJson tt = new TransToJson();
	//初始化的主界面
	@RequestMapping("/index.action")
	public String uIndex(){
		return "front/index";
	}
	
	//登录
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
				int id = user.getId();
				se.setAttribute("user", user);
				se.setAttribute("id",id);
				status.setSuccess(true);
				String json = tt.transToJson(status);
				return json;
			}
		}
	
	//个人中心
	@RequestMapping("/person.action")
	public String person(){
		return "front/user/index";
	}
	//修改资料
	@RequestMapping(value="/profile.action",method=RequestMethod.GET)
	public String profileJsp(){
		return "front/user/profile";
	}
	
	//更新资料
	@RequestMapping(value="/profile.action",method=RequestMethod.POST)
	public String profile(User u,HttpSession se){
		int id = u.getId();
		u.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		us.updateUser(u);
		User user = us.findUserById(id);
		se.setAttribute("user", user);
		return "redirect:person.action";
	}
	//上传头像
	@RequestMapping(value="/avatar.action",method=RequestMethod.GET)
	public String avatarJsp(){
		return "front/user/avatar";
	}
	//头像图片上传
	@RequestMapping(value="/avatar.action",method=RequestMethod.POST)
	public String avatar(MultipartFile headUrl,HttpSession se) throws IllegalStateException, IOException{
		//图片命名
		String str = UUID.randomUUID().toString().replaceAll("-", "");
		String ext = FilenameUtils.getExtension(headUrl.getOriginalFilename());
		String fileName = str+"."+ext;
		//图片路径
		String path = "E:\\upload";
		//上传图片
		headUrl.transferTo(new File(path+"\\"+fileName));
		//更新登录用户的信息
		int id = (int) se.getAttribute("id");
		User u = new User();
		u.setHeadUrl(fileName);
		u.setId(id);
		us.updateUser(u);
		User user = us.findUserById(id);
		se.setAttribute("user", user);
		return "front/user/avatar";
	}
	//修改密码
	@RequestMapping(value="/password.action",method=RequestMethod.GET)
	public String passwordJsp(){
		return "front/user/password";
	}
	//检查老密码
	@RequestMapping(value="/checkPwd.action",method=RequestMethod.POST)
	@ResponseBody
	public String checkPwd(String oldPassword) throws JsonProcessingException{
		String pwd = MD5Utils.getMD5(oldPassword);
		List<User> list = us.findUserByPwd(pwd);
		Status status = new Status();
		if(!list.isEmpty()){
			status.setSuccess(true);
			String json = tt.transToJson(status);
			return json;
		}else{
			status.setMessage("密码错误!");
			String json = tt.transToJson(status);
			return json;
		}
	}
	//设置新密码
	@RequestMapping(value="/password.action",method=RequestMethod.POST)
	public String password(){
		return "front/user/password";
	}
	//注册检测
	@RequestMapping(value="/checkRegist.action",method=RequestMethod.POST)
	@ResponseBody
	public String regist(User u,HttpSession se) throws JsonProcessingException{
		List<User> list = us.findEmailByMail(u.getEmail());
		Status status = new Status();
		if(list.isEmpty()){
			u.setInsertTime(new Timestamp(System.currentTimeMillis()));
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
	
	//忘记密码
	@RequestMapping(value="/forgetpwd.action",method=RequestMethod.GET)
	public String forgetpwd(){
		return "front/user/forget_pwd";
	}
	
	//发送验证码
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
	
	//验证码验证
	@RequestMapping(value="/verify.action",method=RequestMethod.POST)
	@ResponseBody
	public String verify(String email,String captcha) throws JsonProcessingException{
		Status status = new Status();
		List<User> list = us.findEmailByMail(email);
		if(list.get(0).getCaptcha().equals(captcha)){
			status.setSuccess(true);
			String json = tt.transToJson(status);
			return json;
		}else{
			status.setMessage("请重新输入");
			String json = tt.transToJson(status);
			return json;
		}
	}
	
	//跳转到重置页面
	@RequestMapping(value="/forgetpwd.action",method=RequestMethod.POST)
	public String modifi(String email,String captcha,Model md){
		md.addAttribute("email", email);
		md.addAttribute("captcha", captcha);
		return "front/user/reset_pwd";
	}
	
	//重置密码
	@RequestMapping("/resetpwd.action")
	public String reset(User u,HttpSession se){
		us.updateUserSelect(u);
		List<User> list = us.findEmailByMail(u.getEmail());
		User user = list.get(0);
		se.setAttribute("user", user);
		return "front/index";
	}
	//退出
	@RequestMapping("/logout.action")
	public String loginOut(HttpSession se){
		se.invalidate();
		return "redirect:index.action";
	}
}
