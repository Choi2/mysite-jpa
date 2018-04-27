package com.cafe24.mysite.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.domain.User;

@Repository
public class UserRepositoryPersistence {
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean update(User user) {
		em.persist(user);
		return true;
	}
	
}
