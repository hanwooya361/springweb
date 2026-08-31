package example.day03;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

import org.springframework.web.bind.annotation.RequestParam;


// @Component //[ 싱글톤대신], 스프링 컨테이너에 해당 클래스의 객체 등록
@Controller // [서블릿 대신], HTTP 통신 지원하는 서블릿 제공 + @Component
public class RestController1 {
    // 1. http://localhost:8080/day03/task1
    @GetMapping(value = "/day03/task1") // HTTP 요청: URL 매핑/연결
    @ResponseBody   // HTTP 응답: JSON 타입 변환 - HTTP content-type
    public int task1() {
        System.out.println("RestController1.task1()");  // soutm+엔터: 현재메소드명
        return 10;  // Content-Type:	application/json
    }

    // 2. 
    @GetMapping("/day03/task2")
    @ResponseBody
    public String task2() {
        System.out.println("RestController1.task2()");
        return "안녕";  // content-type: text/plain
    }
    
    // 3.
    @GetMapping("/day03/task3")
    @ResponseBody
    public Map<String,Object> task3() { // map 컬렉션 프레임워크 [{key,value}, {key,value}]
        Map<String,Object> map = new HashMap<>();
        map.put("유재석", 100);
        map.put("강호동", 90);
        return map; // Content-Type:	application/json
    }

    // 4.
    @GetMapping("/day03/task4")
    @ResponseBody
    public ExamDto task4() {
        ExamDto dto = new ExamDto();    dto.setName("유재석");  dto.setAge(10);
        return dto; // Content-Type:	application/json
    }
        
}
// dto
@Data
class ExamDto{String name; int age;}