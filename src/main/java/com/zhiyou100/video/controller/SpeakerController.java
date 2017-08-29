package com.zhiyou100.video.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.SpeakerVO;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.SpeakerService;
import com.zhiyou100.video.util.Page;

@Controller
public class SpeakerController {

	@Autowired
	SpeakerService ss;
	
	@RequestMapping("/speaker/speakerManage.action")
	public ModelAndView speakerList(SpeakerVO sv,HttpServletRequest req,HttpServletResponse res){
		String speakerName = req.getParameter("speakerName");
		String speakerJob = req.getParameter("speakerJob");
		if(speakerName == null || speakerJob == null){
			speakerName = "";
			speakerJob = "";
		}
		int currentPage = req.getParameter("page")==null? 1:Integer.parseInt(req.getParameter("page"));
		sv.setCurrentPage(currentPage);
		sv.setSpeakerJob(speakerJob);
		sv.setSpeakerName(speakerName);
		req.setAttribute("speakerName", speakerName);
		req.setAttribute("speakerJob", speakerJob);
		Page page = ss.findSpeaker(sv);
		req.setAttribute("page",page);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/speaker/speakerManage");	
		return mv;
	}
	
	//添加
		@RequestMapping(value="/speaker/addSpeaker.action",method=RequestMethod.GET)
		public String addSpeaker(Model md){
			
			return "admin/speaker/addSpeaker";
		}
		@RequestMapping(value="/speaker/addSpeaker.action",method=RequestMethod.POST)
		public String addSpeaker2(Speaker s){
			ss.addSpeaker(s);
			return "redirect:/speaker/speakerManage.action";
		}
	
		//修改
		@RequestMapping(value="/speaker/updateSpeaker.action",method=RequestMethod.GET)
		public String updateSpeaker(Integer id,Model md){
			Speaker speaker = ss.findSpeakerById(id);
			md.addAttribute("speaker",speaker);
			return "/admin/speaker/updateSpeaker";
		}
		@RequestMapping(value="/speaker/updateSpeaker.action",method=RequestMethod.POST)
		public String updateSpeaker2(Speaker s){
			ss.updateSpeaker(s);
			return "redirect:/speaker/speakerManage.action";
		}
		
		//删除
		@RequestMapping("/speaker/deleteSpeaker.action")
		public String deleteVideo(Integer id){
			ss.deleteSpeaker(id);
			return "redirect:/speaker/speakerManage.action";
		}
		
		
		
		
		
		
}
