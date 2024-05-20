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
@RestController
@RequiredArgsConstructor
public class BoardController {
    //@RequiredArgsConstructor 쓸 때는, private final로 선언해야 주입 정상적으로 해줍니다. final 빼먹으면 인식 못함
    private final BoardService boardService; // Controller -> Serivce -> Repository 구조니까 Cont는 Service를 주입받고, Service는 Repository를 주입받는  형태

    @PostMapping("/api/createBoard") // URL 주소
    public Boolean createBoard(@RequestParam String title, @RequestParam String content) {
        boardService.createBoard(title, content);
        return true;
    }

    @GetMapping("/api/getBoard")
    public Board getBoard(@RequestParam Long id) {//사용자에게 게시글 번호를 받아서, 조회
        Board board = boardService.getBoard(id);
        return board;
    }
    //외부 기준으로 봐야해
    // 외부 기준에서 조회다 = GET
    // 외부 기준에서, 디비의 상태를 바꾼다 = POST
    @PostMapping("/api/updateBoard")
    public Boolean updateBoard(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        //컨트롤러는 로직 이런거 몰라
        // 그냥 요청 받고, 서비스 실행하고, 결과 반환
        boardService.updateBoard(id, title, content);
        return true;
    }

    @PostMapping("/api/deleteBoard")
    public Boolean deleteBoard(@RequestParam Long id) {
        boardService.deleteBoard(id);
        return true;
    }
}
