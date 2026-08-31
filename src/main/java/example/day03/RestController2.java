package example.day03;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @Component   // 1. 스프링 컨테이너에 객체(빈) 등록
// @Controller  // 2. HTTP 서블릿 지원 + @Component
@RestController // 3. 응답content-type을 application/json 자동 설정(@ResponseBody) + @Controller
// 활용: VIEW(HTML) -> @Controller , JSON(값) -> @RestController
@RequestMapping("/day03")   // 클래스내 메소드들의 공통url 정의
public class RestController2 {
    // 1. 해당 클래스가 @RestController이면 @ResponseBody 생략 가능
    @GetMapping("/task5") // 중복없는 url 정의
    public String task5(){
        return "서버에게 받은 메세지"; // content-type: text/plain;
    }
    //------------요청 매개변수-------------//
    // 2. @RequestParam란? 요청 content-type: form 또는 쿼리스트링의 매개변수 매핑/연결
    @GetMapping("/task6")    // 클래스내 동일한 url에 대해선 @RequestMapping에서 정의
    public int task6(@RequestParam String name, @RequestParam int age){
        System.out.println(name);   System.out.println(age);
        return 6;
    }
    // 3.
    @GetMapping("/task7")
    public int task7(String name, // @RequestParam 생략 가능
        @RequestParam(name="age")int age,  // @RequestParam(name="매핑할매개변수명")
        @RequestParam(required = false, defaultValue = "10") int count   
        // @RequestParam(required="필수여부", defaultValue="기본값")
    ){
        System.out.println(name); System.out.println(age); System.out.println(count);
        return 7;
    }
    // 4.
    @DeleteMapping("/task8")
    public int task8(@RequestParam Map<String,Object> map){ // Map이용시 한번에 쿼리스트링 받을수있음, @RequestParam로 받아야함
        System.out.println(map);
        return 8;
    }
    // 5.
    @DeleteMapping("/task9")
    public int tsak9(@ModelAttribute ExamDto examDto){  // DTO는 @ModelAttribute로 받아야함
        System.out.println(examDto);
        return 9;
    }
}
