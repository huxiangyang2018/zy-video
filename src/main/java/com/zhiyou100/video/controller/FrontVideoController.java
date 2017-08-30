package com.zhiyou100.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.SubjectService;
import com.zhiyou100.video.service.VideoService;

@Controller
@RequestMapping("/front/video")
public class FrontVideoController {

	@Autowired
	SubjectService ss;
	
	@Autowired
	VideoService vs;
	
	@RequestMapping("/index.action")
	public String index(Integer subjectId,Integer videoId,Model md){
		md.addAttribute("videoId", videoId);
		Subject sub = ss.findSubjectById(subjectId);
		md.addAttribute("subject", sub);
		return "front/video/index";
	}
	
	@RequestMapping("/videoData.action")
	public String videoData(Integer videoId,Model md){
		Video video = vs.findVideoByVideoId(videoId);
		int sId = vs.findSIdByCid(video.getCourseId());
		md.addAttribute("subjectId", sId);
		List<Video> videoList = vs.finAllVideosByCId(video.getCourseId());
		md.addAttribute("video", video);
		md.addAttribute("videoList", videoList);
		return "front/video/content";
	}
	
	@RequestMapping("/state.action")
	public void state(@RequestParam(defaultValue="0")Integer videoId){
		Video video = vs.findVideoById(videoId);
		video.setVideoPlayTimes(video.getVideoPlayTimes()+1);
		vs.updateVideo(video);
	}
}
