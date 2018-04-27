package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cafe24.mysite.domain.Comment;
import com.cafe24.mysite.domain.User;
import com.cafe24.mysite.service.CommentService;

@Controller
@RequestMapping("/board/comment")
@SessionAttributes("authUser")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public Comment write(
			@ModelAttribute("authUser") User user,
			@ModelAttribute Comment comment) {
		System.out.println("comment = " + comment);
		comment.setUser(user);
		commentService.writeComment(comment);
		return comment;
	}
}
