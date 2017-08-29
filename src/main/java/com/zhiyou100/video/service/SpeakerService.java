package com.zhiyou100.video.service;


import java.util.List;

import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.SpeakerVO;
import com.zhiyou100.video.util.Page;

public interface SpeakerService {

	Page findSpeaker(SpeakerVO sv);

	List<Speaker> findSpeakers();

	void addSpeaker(Speaker s);

	Speaker findSpeakerById(Integer id);

	void updateSpeaker(Speaker s);

	void deleteSpeaker(Integer id);

}
