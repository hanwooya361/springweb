package example.totalpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;
 	
@SpringBootApplication // 스프링부트 실행
@EnableJpaAuditing
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class );
    }
}