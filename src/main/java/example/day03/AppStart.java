package example.day03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  
// 1. 내장톰켓지원(@EnableAutoConfiguration) 
// 2. IOC/DI 컴포넌트 스캔(@ComponentScan)
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);  // 클래스명.class 리플렉션
        // 실행 --> http://localhost:8080/ 
    }
}
