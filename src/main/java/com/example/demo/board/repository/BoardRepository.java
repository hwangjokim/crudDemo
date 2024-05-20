package com.example.demo.board.repository;

import com.example.demo.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //조회, 생성, 수정, 삭제
}
