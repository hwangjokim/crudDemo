package com.example.demo.board.controller;


import com.example.demo.board.domain.Board;
import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//MVC
// Model View Controller
// Model : 로직을 담고있는 코드들 (Repository, Service)
// View : 리턴값 (JSON)
// Controller: Model과 View를 연결해주는 중간 다리 (Controller)
//Controller는 Return이 HTML, RestController는 Return JSON. 우리는 JSON 리턴할거니까 REstController
// HTML <h2>제목</h2> -> 프론트 시켰어
// JSON @RestController
@RestController
@RequiredArgsConstructor
public class BoardController {
    //@RequiredArgsConstructor 쓸 때는, private final로 선언해야 주입 정상적으로 해줍니다. final 빼먹으면 인식 못함
    private final BoardService boardService; // Controller -> Serivce -> Repository 구조니까 Cont는 Service를 주입받고, Service는 Repository를 주입받는  형태


    //Controller는 실제 유저(프론트)가 호출하는 부분이 모여있어
    // Controller는 유저에게 입력을 받고, 응답을 돌려주는 일을 해
    // 그래서 Controller엔 핵심 로직이 들어가지 않아. 그건 서비스의 일이니까
    // 그저 title, content 받아
    // 그다음 service에있는 method를 호출해
    // 안에서 무슨일이 일어나는지 Controller는 몰라. 그냥 돼.
    // 내 아이피 주소의 예약어
    // 127.0.0.1
    // localhost => 내 아이피 주소로 갑니다
    // naver.com => 도메인
    // naver.com => 중간에 이게 아이피로 바뀌어
    // :8080을 쓴다. 8080은 웹통신할때 쓰는 표준포트

    // 진짜 요약 : localhost:8080이 우리 주소다.


    @PostMapping("/api/board") // URL 주소
    public Boolean createBoard(@RequestParam String title, @RequestParam String content) {
        boardService.createBoard(title, content);
        return true;
    }

    @GetMapping("/api/board")
    public Board getBoard(@RequestParam Long id) {//사용자에게 게시글 번호를 받아서, 조회
        Board board = boardService.getBoard(id);
        return board;
    }
    //외부 기준으로 봐야해
    // 외부 기준에서 조회다 = GET
    // 외부 기준에서, 디비의 상태를 바꾼다 = POST
    // CRUD Create Read Update Delete
    // 보통 기본적인 API는 저 네개의 작업을 해
    // 일정 : CRUD
    // JpaRepository 기본 내장 메서드들 -> CRUD (많이 쓰니까)
    // save, delete, findBy, 업데이트는 엔티티의 필드값만 바꿔도 자동으로 이루어진다
    // Create, Delete, Read,

    @PutMapping("/api/board") // 행위가 아닌, 자원을 쓰고 무슨 일을 할지는 HTTP Method로 구분
    public Boolean updateBoard(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        //컨트롤러는 로직 이런거 몰라
        // 그냥 요청 받고, 서비스 실행하고, 결과 반환
        boardService.updateBoard(id, title, content);
        return true;
    }

    @DeleteMapping("/api/board")
    public Boolean deleteBoard(@RequestParam Long id) {
        boardService.deleteBoard(id);
        return true;
    }

}
