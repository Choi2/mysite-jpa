package com.cafe24.mysite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.domain.Guestbook;

@Repository
public interface GuestbookRepository extends JpaRepository<Guestbook, Long>{
	
	List<Guestbook> findAllByOrderByRegDateDesc();
}
