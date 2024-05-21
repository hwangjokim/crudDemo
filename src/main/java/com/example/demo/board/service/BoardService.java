package com.example.demo.board.service;


import com.example.demo.board.domain.Board;
import com.example.demo.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//싱글톤
// 우리가 객체를 만들면 new ~~
// new 할 때 마다 객체가 하나씩 생기는데
// 싱글톤은 객체를 딱 하나만 만들어놓고 돌려서 쓰는 형식 싱글톤
// 객체 자체가 상태(값, 필드) 를 들고있는 경우가 많은데
// 우리가 만든 Repo, Service, Controller는 딱 함수만 있어
// 필드가 없고, 뭔가 기능을 수행하는 함수만 있어서
// 굳이 객체를 새로 매번 만들 필요가 없어. 어짜피 다 같은 기능을 해
//그래서 싱글톤을 쓰는데, 이 하나만 만드는 객체는 누가 만드냐
// Spring 이 만들어줘. 자동으로 .
//우ㅡ리는 이렇게 스프링이 만들어준 싱글톤 객체를
// "주입" 받는다고 표현해
// 우리가 직접 new 하지 않고, 스프링에게 받아서 쓰는거지
//이게 의존성 주입

@Service
@Transactional // 무지성으로 붙여도됨
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    // deleteSchedule이란 api를 만들었어
    // 얘는, 세션 정보(sid)랑, 지울 일정 번호를 받아 컨트롤러에서

    //컨트롤러에서, sid를 가지고 로그인 된 유저인지 검사하겠지?
    // 세션스토리지에 있네? -> 통과.

    // service.deleteSchedule(user, targetScheduleId);// 현재 로그인된 유저, 지울 일정 번호

    // scheduleRespository.findByUserAndId(user, scheduleId); =>
    //DB에서, 현재 유저가 썼으며, && 일정 번호가 id인걸 찾는거야

    // 만약 현재 유저가 만든 일정이 아니라면? DB에 없겠지 당연하게
    // throw new RuntimeException("니가만든 일정 아님");
    // Primary Key


    // Controller

    // Service

    // Repository

    // Database




    public void createBoard(String title, String content) {
        //우리는 이제 글 데이터를 데이터베이스에 저장할거야
        // 우리가 DB 표를 대신하는 역할이 클래스로 Domain이였어
        // DB엔 표처럼 데이터가 저장되지만, 그거 한 줄 한 줄을 Domain class로 표현해
        // 우리가 디비에 데이터를 넣든, 꺼내오든, 실제 db의 표형태가 아니라, 엔티티(도메인)에 담아서 넣고 빼고 하는 것
        Board board = Board.builder().title(title).content(content).build(); //파라미터로 받은 title, content를 가지고 도메인 클래스로 바꿔줌
        boardRepository.save(board); // Repository는 DB랑 소통하는 친구니까, 얘를 통해서 DB에 저장함
    }

    public Board getBoard(Long id){
        //고유 식별자인, Primary Key를 받아서 조회.
        Board board = boardRepository.findById(id).get();
        return board;
    }

    public void deleteBoard(Long id) {
        Board board = getBoard(id);
        boardRepository.delete(board);
    }

    public void updateBoard(Long id, String title, String content) {
        Board board = boardRepository.findById(id).get();
        board.updateBoard(title, content);

        //JPA에는, 변경 감지가 있어
        //조회(findBy) 시점의 도메인 내부 값이랑, 이 메서드가 끝날때 값이랑 다르면
        //자동으로 DB에 업데이트를해. 따라서 Repository를 거치지 않아

    }

}
