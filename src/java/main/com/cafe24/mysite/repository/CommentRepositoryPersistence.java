package com.cafe24.mysite.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.domain.Board;

@Repository
public class CommentRepositoryPersistence {
	@PersistenceContext
	private EntityManager em;

	public Board getAllList(long boardNo) {
		Query query =
		          em.createQuery( "SELECT b FROM Board b WHERE b.no = :boardNo");

		query.setParameter("boardNo", boardNo);
		return (Board) query.getSingleResult();
	}
}
