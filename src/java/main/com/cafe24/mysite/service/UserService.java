package com.cafe24.mysite.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.domain.User;
import com.cafe24.mysite.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void join(User user) {
		user.setJoinDate(new Date());
		userRepository.save(user);
	}

	public User getUser(User user) {
		return userRepository.findAllByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	public boolean modifyUser(User user) {
		userRepository.save(user);
		return true;
	}
}