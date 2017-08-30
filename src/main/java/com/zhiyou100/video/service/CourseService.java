package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.Course;

public interface CourseService {

	List<Course> findAllCourse();

	void addCourse(Course c);

	Course findCourseById(Integer id);

	void updateCourse(Course c);

	void deleteCourse(Integer id);

	List<Course> findFrontCourse(Integer subjectId);

}
