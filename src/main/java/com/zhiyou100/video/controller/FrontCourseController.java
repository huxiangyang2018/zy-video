package com.zhiyou100.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.CourseService;
import com.zhiyou100.video.service.SubjectService;

@Controller
@RequestMapping("/front/course")
public class FrontCourseController {

	@Autowired
	SubjectService ss;
	@Autowired
	CourseService cs;
	//进入课程列表
	@RequestMapping("/index.action")
	public String index(Integer subjectId,Model md){
		Subject sub = ss.findSubjectById(subjectId);
		md.addAttribute("subject", sub);
		List<Course> course = cs.findFrontCourse(subjectId);
		md.addAttribute("courses", course);
		return "front/course/index";
	}
}
