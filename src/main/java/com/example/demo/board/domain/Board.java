package com.example.demo.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key는 디비가 알아서 만들어준다
    private Long id;

    private String title;
    private String content;

    @Builder
    public Board(String title, String content) { // 그래서 생성자도 아아디 입력 X
        this.title = title;
        this.content = content;
    }

    public void updateBoard(String title, String content){
        this.title = title;
        this.content = content;
    }
}

//의도를 몰라서 Setter 금지
// 1. 초기화 용도
// 2. 업데이트 용도
//board.set~~~()
// 생성 직후 초기화인가? 아니면 업데이트 하고싶은건가? 뭐지?


