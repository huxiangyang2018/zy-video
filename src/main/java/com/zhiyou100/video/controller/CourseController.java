package com.zhiyou100.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.CourseService;
import com.zhiyou100.video.service.SubjectService;



@Controller
@RequestMapping("/admin")
public class CourseController {

	@Autowired
	CourseService cs;
	@Autowired
	SubjectService ss;
	
	//查询
	@RequestMapping("/course/courseManage.action")
	public String courseList(Model mv){
		List<Course> cList = cs.findAllCourse();
		mv.addAttribute("cList", cList);
		return "/admin/course/courseManage";
	}
	
	//添加
	@RequestMapping(value="/course/addCourse.action",method=RequestMethod.GET)
	public String addCourse(Model md){
		List<Subject> sbList = ss.findAllSubject();
		md.addAttribute("sbList", sbList);
		return "/admin/course/addCourse";
	}
	@RequestMapping(value="/course/addCourse.action",method=RequestMethod.POST)
	public String addCourse2(Course c){
		cs.addCourse(c);
		return "redirect:/course/courseManage.action";
	}
	
	//修改
	@RequestMapping(value="/course/updateCourse.action",method=RequestMethod.GET)
	public String updateCourse(Integer id,Model md){
		List<Subject> sbList = ss.findAllSubject();
		md.addAttribute("sbList", sbList);
		Course course = cs.findCourseById(id);
		md.addAttribute("course",course);
		return "/admin/course/updateCourse";
	}
	@RequestMapping(value="/course/updateCourse.action",method=RequestMethod.POST)
	public String updateCourse2(Course c){
		cs.updateCourse(c);
		return "redirect:/course/courseManage.action";
	}
	
	//删除
	@RequestMapping("/course/deleteCourse.action")
	public String deleteCourse(Integer id){
		cs.deleteCourse(id);
		return "redirect:/course/courseManage.action";
	}
	
	
}
