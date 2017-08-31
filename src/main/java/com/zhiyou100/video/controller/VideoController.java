package com.zhiyou100.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.CourseService;
import com.zhiyou100.video.service.SpeakerService;
import com.zhiyou100.video.service.VideoService;
import com.zhiyou100.video.util.Page;

@Controller
@RequestMapping("/admin")
public class VideoController {

	@Autowired
	VideoService vs;
	@Autowired
	SpeakerService ss;
	@Autowired
	CourseService cs;
	//查询搜索分页
	@RequestMapping("/video/videoManage.action")
	public String videoList(@RequestParam(defaultValue="1") Integer page, Video v,Model md){
		
		v.setPage(page);
		@SuppressWarnings("rawtypes")
		Page p = vs.findVideos(v);
		
//		List<Video> vList = vs.findVideos(v);
		List<Speaker> sList = ss.findSpeakers();
		List<Course> cList = cs.findAllCourse();
		
		md.addAttribute("speakerName", v.getSpeakerName());
		md.addAttribute("courseName", v.getCourseName());
		md.addAttribute("sList", sList);
		md.addAttribute("page", p);
		md.addAttribute("cList", cList);
		return "admin/video/videoManage";
		
	}
	//添加
	@RequestMapping(value="/video/addVideo.action",method=RequestMethod.GET)
	public String addVideo(Model md){
		List<Speaker> sList = ss.findSpeakers();
		List<Course> cList = cs.findAllCourse();
		md.addAttribute("sList", sList);
		md.addAttribute("cList", cList);
		return "admin/video/addVideo";
	}
	@RequestMapping(value="/video/addVideo.action",method=RequestMethod.POST)
	public String addVideo2(Video v){
		vs.addVideo(v);
		return "redirect:/video/videoManage.action";
	}
	
	//删除
	@RequestMapping("/video/deleteVideo.action")
	public String deleteVideo(Integer id){
		vs.deleteVideo(id);
		return "redirect:/video/videoManage.action";
	}
		
	//修改
	@RequestMapping(value="/video/updateVideo.action",method=RequestMethod.GET)
	public String updateVideo(Integer id,Model md){
		List<Speaker> sList = ss.findSpeakers();
		List<Course> cList = cs.findAllCourse();
		md.addAttribute("sList", sList);
		md.addAttribute("cList", cList);
		Video video = vs.findVideoById(id);
		md.addAttribute("video",video);
		return "admin/video/updateVideo";
	}
	@RequestMapping(value="/video/updateVideo.action",method=RequestMethod.POST)
	public String updateVideo2(Video v){
		vs.updateVideo(v);
		return "redirect:/video/videoManage.action";
	}
	
	//批量删除
	@RequestMapping("/video/deleteVideos.action")
	public String deleteVideos(@RequestParam(defaultValue="0") Integer checkbox[]){
		for(int i:checkbox){
			System.out.println(i);
			vs.deleteVideo(i);
		}
		return "redirect:/video/videoManage.action";
	}
	
}
