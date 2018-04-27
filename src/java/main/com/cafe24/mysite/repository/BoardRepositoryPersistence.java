package com.cafe24.mysite.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.domain.Board;
import com.cafe24.mysite.domain.Pager;

@Repository
public class BoardRepositoryPersistence {
	@PersistenceContext
	private EntityManager em;

	public Long getTotalCount(Pager pager) {
		
		String sql = "SELECT COUNT(b.no) FROM Board b ";
		Query query = em.createQuery(sql);
		
		if(pager.getWord() != null) {
			sql = sql + "WHERE b.title LIKE :title ";
			query.setParameter("title", pager.getWord());
		} 
		sql = sql + "ORDER BY b.regDate desc";	
		return (Long) query.getSingleResult();
	} //게시판 전체 갯수

	public List<Board> getAllList(Pager pager) {
		TypedQuery<Board> query =
		          em.createQuery( "SELECT b FROM Board b ORDER BY b.groupNo DESC, orderNo DESC ", Board.class);

		query.setFirstResult( (int) ((pager.getPage()- 1) * 5));
		query.setMaxResults( 5 );

		return query.getResultList();
	} //게시판 리스트 출력

	public void updateReadCount(long boardNo) {
		Board board = em.find(Board.class, boardNo);
		board.setReadCount(board.getReadCount() + 1);
	}

	public int updateArrangeList(Board board) {
		Query query =
		          em.createQuery( 
		        		  "UPDATE "
		        		  + "Board b SET b.orderNo = b.orderNo + 1 "
		        		  + "WHERE b.groupNo = :groupNo "
		        		  + "AND   b.orderNo >= :orderNo");
		query.setParameter("groupNo", board.getGroupNo());
		query.setParameter("orderNo", board.getOrderNo());
		return query.executeUpdate();
	}

	public void update(Board board) {
		Board vo = em.find(Board.class, board.getNo());
		vo.setTitle(board.getTitle());
		vo.setContent(board.getContent());
		em.persist(vo);
	}

	public long GetNo() {
		String sql = "SELECT MAX(b.no) FROM Board b ";
		Query query = em.createQuery(sql);
		Object no = (Long) query.getSingleResult();
		if(no == null) {
			return 0L;
		}
		
		return (long) no;
	}

	public void insert(Board board) {
		em.persist(board);
	} 

}
