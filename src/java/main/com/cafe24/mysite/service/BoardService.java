package com.cafe24.mysite.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.domain.Board;
import com.cafe24.mysite.domain.Comment;
import com.cafe24.mysite.domain.Pager;
import com.cafe24.mysite.repository.BoardRepository;
import com.cafe24.mysite.repository.BoardRepositoryPersistence;
import com.cafe24.mysite.repository.CommentRepositoryPersistence;

@Service
@Transactional
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardRepositoryPersistence boardRepositoryPersistence;
	
	@Autowired
	private CommentRepositoryPersistence commentRepositoryPersistence;
	
	private Pager pager;
	
	
	public List<Board> getAllBoardList(Pager pager) {
		
		if(pager.getWord() == null || pager.getWord().equals("")) {
			pager.setWord(null);
		}
		
		pager.setPage(pager.getPage());
		pager.setTotalCount(boardRepositoryPersistence.getTotalCount(pager));
		pager.calculate(pager.getPage());
		this.pager = pager;
		return boardRepositoryPersistence.getAllList(pager);
	}

	public Pager getPager() {
		return this.pager;
	}
	

	public void updateReadCount(long boardNo) {
		boardRepositoryPersistence.updateReadCount(boardNo);
	}

	public Board getOneBoard(long boardNo) {
		return boardRepository.findOne(boardNo);
	}

	public List<Comment> getCommentList(long boardNo) {
		return commentRepositoryPersistence.getAllList(boardNo).getComments();
	}
	
	public void writeBoard(Board board) {
		boardRepositoryPersistence.updateArrangeList(board); //댓글 깊이에 따른 재 정렬
		
		board.setNo(0);
		board.setRegDate(new Date());
		
		if(board.getParentNo() == 0) {
			long maxNo = boardRepositoryPersistence.GetNo();
			board.setGroupNo(maxNo + 1);
			boardRepository.save(board); //정렬 완료 후 게시글 등록하기
		} else {
			board.setDepth(board.getDepth() + 1);
			boardRepositoryPersistence.insert(board);
		}
		System.out.println("update.... " + board);
		
	}

	public void modifyBoard(Board board) {
		boardRepositoryPersistence.update(board); //수정
	}

	public void deleteBoard(long boardNo) {
		boardRepository.delete(boardNo);
	}

}