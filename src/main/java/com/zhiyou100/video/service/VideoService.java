package com.zhiyou100.video.service;



import java.util.List;

import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.util.Page;

public interface VideoService {


	Page findVideos(Video v);

	void addVideo(Video v);

	void deleteVideo(Integer id);

	Video findVideoById(Integer id);

	void updateVideo(Video v);

	List<Video> findAvgCourse();

	List<Video> finAllVideos();

	Video findVideoByVideoId(Integer videoId);

	List<Video> finAllVideosByCId(Integer cId);

	int findSIdByCid(Integer courseId);

	

}
