package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.User;

public interface UserService {

	List<User> findEmailByMail(String string);

	void addUser(User u);

	List<User> userLogin(User u);

	void updateUser(User user);

	void updateUserSelect(User u);

	List<User> findUserByPwd(String pwd,int id);

	User findUserById(int id);

}
