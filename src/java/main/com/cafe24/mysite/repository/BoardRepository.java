package com.cafe24.mysite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
