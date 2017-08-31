package com.zhiyou100.video.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.View;
import com.zhiyou100.video.service.VideoService;

@Controller
@RequestMapping("/admin")
public class CountController {

	@Autowired
	VideoService vs;

	
	@RequestMapping("/count/countManage.action")
	public String courseList2(){
		return "admin/count/countManage";
		
	}
	
	
	
	
	/*@RequestMapping("/count/countManage.action")
	public String courseList(Model mv){
		List<Video> vList = vs.findAvgCourse();
		System.out.println(vList);
		mv.addAttribute("vList", vList);
		return "count/countManage";
	}*/
	@SuppressWarnings("unchecked")
	@RequestMapping("/count/countManag.action")
	@ResponseBody
	public String courseList(Model mv) throws Exception{
		List<Video> vList = vs.findAvgCourse();
		 @SuppressWarnings("rawtypes")
		List li = new LinkedList();
			for(Video v: vList){
				View vi = new View();
				vi.setId(v.getVideoPlayTimes());
				vi.setName(v.getCourseName());
				li.add(vi);
			}
			//System.out.println(li);
			ObjectMapper mapper = new ObjectMapper();    
			String json = mapper.writeValueAsString(li); 
			//System.out.println(json);
			return json;
	}
	
	
	
	
	
}
