package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.CourseMapper;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseMapper cm;


	@Override
	public List<Course> findAllCourse() {
		return cm.findAllCourse();
	}

	@Override
	public void addCourse(Course c) {
		cm.insertSelective(c);
		
	}

	@Override
	public Course findCourseById(Integer id) {
		// TODO Auto-generated method stub
		return cm.selectByPrimaryKey(id);
	}

	@Override
	public void updateCourse(Course c) {
		cm.updateByPrimaryKeySelective(c);
		
	}

	@Override
	public void deleteCourse(Integer id) {
		cm.deleteByPrimaryKey(id);
		
	}

}
