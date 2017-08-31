package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.SpeakerMapper;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.SpeakerVO;
import com.zhiyou100.video.service.SpeakerService;
import com.zhiyou100.video.util.Page;

@Service
public class SpeakerServiceImpl implements SpeakerService {

	@Autowired
	SpeakerMapper sm;

	@SuppressWarnings("rawtypes")
	@Override
	public Page findSpeaker(SpeakerVO sv) {
		
		Page<Speaker> page = new Page<>();
		page.setTotal(sm.findSpeakerCount(sv));
		page.setSize(5);
		page.setPage(sv.getCurrentPage());
		sv.setCurrentPage((sv.getCurrentPage()-1)*5);
		page.setRows(sm.findAllSpeaker(sv));
		return page;		
	}

	@Override
	public List<Speaker> findSpeakers() {
		
		return sm.selectByExample(null);
	}

	@Override
	public void addSpeaker(Speaker s) {
		sm.insertSelective(s);
		
	}

	@Override
	public Speaker findSpeakerById(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectByPrimaryKey(id);
	}

	@Override
	public void updateSpeaker(Speaker s) {
		sm.updateByPrimaryKeySelective(s);
		
	}

	@Override
	public void deleteSpeaker(Integer id) {
		sm.deleteByPrimaryKey(id);
		
	}
	
	
	
}
