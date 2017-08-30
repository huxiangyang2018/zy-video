package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.UserMapper;
import com.zhiyou100.video.model.User;
import com.zhiyou100.video.model.UserExample;
import com.zhiyou100.video.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper um;

	@Override
	public List<User> findEmailByMail(String mail) {
		UserExample ue = new UserExample();
		ue.createCriteria().andEmailEqualTo(mail);
		List<User> list = um.selectByExample(ue);
		return list;
	}

	@Override
	public void addUser(User u) {
		um.insertSelective(u);
	}

	@Override
	public List<User> userLogin(User u) {
		UserExample ue = new UserExample();
		ue.createCriteria().andEmailEqualTo(u.getEmail()).andPasswordEqualTo(u.getPassword());
		List<User> list = um.selectByExample(ue);
		return list;
	}

	@Override
	public void updateUser(User user) {
		um.updateByPrimaryKeySelective(user);
	}

	@Override
	public void updateUserSelect(User u) {
		UserExample ue = new UserExample();
		ue.createCriteria().andEmailEqualTo(u.getEmail());
		um.updateByExampleSelective(u, ue);
	}

	@Override
	public List<User> findUserByPwd(String pwd) {
		UserExample ue = new UserExample();
		ue.createCriteria().andPasswordEqualTo(pwd);
		return um.selectByExample(ue);
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return um.selectByPrimaryKey(id);
	}
}
