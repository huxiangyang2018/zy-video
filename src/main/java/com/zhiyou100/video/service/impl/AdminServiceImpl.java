package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.AdminMapper;
import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.model.AdminExample;
import com.zhiyou100.video.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper am;
	
	
	@Override
	public Admin login(Admin a) {
		AdminExample  ae = new AdminExample();
		ae.createCriteria().andLoginNameEqualTo(a.getLoginName()).andLoginPwdEqualTo(a.getLoginPwd());
		
		List<Admin> list = am.selectByExample(ae);
		if(list.size()>0){
			Admin admin2 = list.get(0);
			return admin2;
		}else{
			return null;
		}
	}

}
