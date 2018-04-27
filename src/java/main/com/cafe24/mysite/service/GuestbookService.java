package com.cafe24.mysite.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.domain.Guestbook;
import com.cafe24.mysite.repository.GuestbookRepository;

@Service
@Transactional //안해주면 'no transational entityManger'가 뜬다.
public class GuestbookService {
	@Autowired
	private  GuestbookRepository guestbookRepository;

	public List<Guestbook> getMessageList() {
		//return guestbookRepository.findAll();
		return guestbookRepository.findAllByOrderByRegDateDesc();
	}

	public void insertMessage(Guestbook guestbook) {
		guestbook.setRegDate(new Date());
		guestbookRepository.save(guestbook);
	}

	public void deleteMessage(Guestbook guestbook) {
		guestbookRepository.delete(guestbook);
	}
}
