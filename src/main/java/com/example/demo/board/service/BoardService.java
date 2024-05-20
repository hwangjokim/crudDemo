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

    public void updateBoard(Long id , String title, String content) { //어던 게시글 업데이트 하는지 알아야하니까
        Board board = getBoard(id); // DB에서 뽑아온 친구
        //그러면 스프링이 이 값을 기억하고 있어 뽑아올 때
        //근데 이 updateBoard 함수가 끝났는데, 값이 달라졌다? DB에 자동으로 업데이트를 해
        board.updateBoard(title, content);
        // 얘는 업데이트인데 디비에 아무것도 안하네? Repository 가지고 update 안하네?
        // 스프링에는 변경 감지를 해줘
        // 디비에서 가져온 애의 필드를 바꾸면, 자동으로 스프링이 업데이트 쿼리를 디비에 날려줘
        // 그래서 값만바꿔줘도 업데이트가 이루어져
    }

    public void deleteBoard(Long id) {
        Board board = getBoard(id);
        boardRepository.delete(board);
    }

}
