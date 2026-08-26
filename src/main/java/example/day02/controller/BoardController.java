package example.day02.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import example.day02.model.dao.BoardDao;
import example.day02.model.dto.BoardDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/*
    컨트롤러에 서블릿(http 프로토콜 사용 가능하게 기능(GET,POST,PUT,DELETE)을 제공하는 클래스)기능 달기
    * 레거시코드는 상속받아 서블릿 구현
    * 스프링은 @controller에 내 서블릿 포함
    // 1. 웹기술 포함할 컨트롤러 클래스 위에 @Controller or 반환타입이 JSON이면 @RestController
    * HTTP content type: http 전송 데이터 타입 명시
    * text/html, application/json(@RestController), form 등등 (DTO 반환은 없다.)
    // 2. 해당 메소드 마다의 URL정의
        * URL 정의시 http://127.0.0.1:8080(도메인) 이후 경로(path/url) 정의, 중복없이 아무거나
        1.  @PostMapping("/URL"): HTTP 메소드중에 POST 메소드 매핑/연결/대응 어노테이션 
*/

@Controller
public class BoardController {
    //[*] MVC패턴 흐름의 dao 싱글톤 호출
    private BoardDao bd = BoardDao.getInstance();
    // [1] 등록 Controller
    @PostMapping("/board/save")   
    public boolean save( BoardDto boardDto ){
        boolean result = bd.save( boardDto ); // view에게 전달받은 매개변수을 dao에게전달
        return result; // dao에게 받은 결과을 view 반환
    }

    // [2] 
    @GetMapping("/board/findAll")
     public ArrayList<BoardDto> findAll( ){
        ArrayList<BoardDto> result = bd.findAll();
        return result;
    }
    

}
