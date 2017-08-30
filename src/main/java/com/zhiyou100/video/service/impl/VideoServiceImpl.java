package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.VideoMapper;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.VideoService;
import com.zhiyou100.video.util.Page;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoMapper vm;
	@Override
	public Page findVideos(Video v) {
		Page page = new Page();
		page.setPage(v.getPage());
		page.setTotal(vm.findAllVideoCount(v));	
		v.setPage((v.getPage()-1)*5);
		List<Video> list = vm.findAllVideo(v);
		page.setRows(list);	
		page.setSize(5);
		
		return page;
	}
	@Override
	public void addVideo(Video v) {
		vm.insertSelective(v);
		
	}
	@Override
	public void deleteVideo(Integer id) {
		vm.deleteByPrimaryKey(id);
		
	}
	@Override
	public Video findVideoById(Integer id) {
		return vm.selectByPrimaryKey(id);
		
	}
	@Override
	public void updateVideo(Video v) {
		vm.updateByPrimaryKeySelective(v);
	}
	@Override
	public List<Video> findAvgCourse() {
		// TODO Auto-generated method stub
		return vm.findAvgCourse();
	}
	@Override
	public List<Video> finAllVideos() {
		return vm.selectByExample(null);
	}
	@Override
	public Video findVideoByVideoId(Integer videoId) {
		// TODO Auto-generated method stub
		return vm.findVideoByVideoId(videoId);
	}
	@Override
	public List<Video> finAllVideosByCId(Integer cId) {
		return vm.finAllVideosByCId(cId);
	}
	@Override
	public int findSIdByCid(Integer courseId) {
		return vm.findSid(courseId);
	}
	
	
	
	
}
