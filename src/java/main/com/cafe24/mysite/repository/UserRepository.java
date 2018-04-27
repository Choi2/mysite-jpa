package com.cafe24.mysite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findAllByEmailAndPassword(String email, String password); 	
}
