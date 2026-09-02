package example.Practice1.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("")  // http://127.0.0.1:8080/test
    public boolean testWrite(@RequestBody TestDto testDto){
        System.out.println("TestController.testWrite()");
        return true;
    }

    @GetMapping("") // http://127.0.0.1:8080/test
    public ArrayList<TestDto> testPrint(){
        System.out.println("TestController.testPrint()");
        ArrayList<TestDto> result = new ArrayList<>();
        result.add(new TestDto(1, "내용1", "작성자1"));
        result.add(new TestDto(2, "내용2", "작성자2"));
        return result;
    }

    @GetMapping("/detail")  // http://127.0.0.1:8080/test/detail?no=1
    public TestDto tsetDetail(@RequestParam(name="no") int no){
        System.out.println("TestController.tsetDetail()");
        return new TestDto(1, "내용1", "작성자1");
    }

    @PutMapping("") // http://127.0.0.1:8080/test
    public boolean testUpdate(@RequestBody TestDto testDto){
        System.out.println("TestController.testUpdate()");
        return true;
    }

    @DeleteMapping("/{no}")  // http://127.0.0.1:8080/test/1
    public boolean testDelete(@PathVariable(name="no") int no){
        System.out.println("TestController.testDelete()"); 
        return true;
    }
}

@Data// getter, setter, tostring 등
@NoArgsConstructor
@AllArgsConstructor
class TestDto{
    private Integer no;
    private String content;
    private String writer;
}

/*
    - int: 기본타입 +-21억
    - Integer: 참조타입(int 래퍼클래스) + null 저장
    주의할점: http 파싱하는 경우에 int에는 null 저장 불가능
    권장!: 기본타입 대신 래퍼타입 사용하자 (int 대신에 Integer)

    byte    → Byte
    short   → Short
    int     → Integer
    long    → Long
    float   → Float
    double  → Double
    char    → Character
    boolean → Boolean
*/