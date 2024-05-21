package com.example.demo.board.repository;

import com.example.demo.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {


    // select * from board where id = 아이디 and content = 내용

    @Query("select b from Board b where b.id = :id and b.content = :content")
    // 결국 디비랑 소통하려면, 위 쿼리가 진짜 SQL 바뀌어야 하잖아
    // 바꿔주는 라이브러리를 진짜 SQL이랑, 자바 문법이랑 바꿔주는 ORM 이라고 하거든 보편적으로
    // 자바에서는 JPA야. 이름이야
    //

    Optional<Board> findByIdAndContent(Long id, String content);
}
