package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cafe24.mysite.domain.Board;
import com.cafe24.mysite.domain.Pager;
import com.cafe24.mysite.domain.User;
import com.cafe24.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
@SessionAttributes("authUser") //authUser
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(Model model,  Pager pager) {

		List<Board> list = boardService.getAllBoardList(pager);
		pager = boardService.getPager();
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		return "board/list";
	}
	
	@RequestMapping("/{no}")
	public String view(Model model, @PathVariable("no") long no) {
		
		boardService.updateReadCount(no);
		Board board = boardService.getOneBoard(no);
		
		model.addAttribute("vo", board);
		model.addAttribute("commentList", board.getComments());
		return "board/view";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.GET)	
	public String write(@ModelAttribute User user) {
		return "board/write"; //check
	}
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(
			@ModelAttribute("authUser") User user,
			@ModelAttribute Board board) {
		board.setUser(user);
		boardService.writeBoard(board);  //해당 회원이 쓴 글 삽입

		return "redirect:/board";
	}
	
	
	@RequestMapping(value="/modify/{no}", method = RequestMethod.GET)
	public String modify(
			@ModelAttribute("authUser") User authUser,
			@PathVariable ("no") long no,
			Model model) {
		
		Board board = boardService.getOneBoard(no);
		
		if(authUser == null) {
			return "user/login";
		}
		
		model.addAttribute("vo", board);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Board board) {
		boardService.modifyBoard(board);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/delete/{no}", method = RequestMethod.GET)
	public String delete(
			@ModelAttribute("authUser") User user,
			@PathVariable long no) {		
		boardService.deleteBoard(no);
		return "redirect:/board";
	}
}
