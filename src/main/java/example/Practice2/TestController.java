package example.Practice2;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    // @Autowired private TestService testService;
    private final TestService testService;

    // 등록
    @PostMapping("")
    public boolean testWrite(@RequestBody TestEntity entity){
        return testService.testWrite(entity);
    }

    // 전체조회
    @GetMapping("")
    public List<TestEntity> testPrint(){
        return testService.testPrint();
    } 

    // 개별조회
    @GetMapping("/detail")
    public Optional<TestEntity> testDetail(int tno){
        return testService.testDetail(tno);
    } 

    /* // 개별조회(교슈님)
    @GetMapping("/detail")
    public TestEntity testDetail(@RequestParam int tno){
        return testService.testDetail(tno);
    }  */

    // 삭제
    @DeleteMapping("")
    public boolean testDelete(@RequestParam(name="tno") int tno){
        return testService.testDelete(tno);
    }

    // 수정
    @PutMapping("")
    public boolean testUpdate(@RequestBody TestEntity entity){
        return testService.testUpdate(entity);
    }
}
