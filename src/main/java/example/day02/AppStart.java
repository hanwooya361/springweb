package example.day02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링: 프레임워크 이므로 다양한 도구와 틀 제공 받음
// @ 어노테이션: 코드에 추가적인 설명과 의미 부여할때 사용 (라벨/주석)
/// 1. @SpringBootApplication:  1) 내장 톰켓 (자동)세팅 2) 서블릿(controller/컴포넌트) 등록 등등
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // 2. spring 실행, SpringApplication.run(현재클래스명.class);   
        // 클래스.class: 클래스 메타정보(멤버변수/생성자/메소드) 반환 
        // SpringApplication.run(springboot메타정보);
        SpringApplication.run(AppStart.class);
        // 3. 실행 컨트롤+f5, 2개 이상 실행 불가능
        // 4. 실행 확인: http://localhost:8080
    }
}
