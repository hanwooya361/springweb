package example.Practice4;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/school")
public class CrController {
    @Autowired private CrService crService;

    // 등록1
    @PostMapping("/course")
    public boolean cAdd(@RequestBody CourseDto courseDto){
        return crService.cAdd(courseDto);
    }
    // 등록2
    @PostMapping("/student")
    public boolean sAdd(@RequestBody StudentDto studentDto){
        return crService.sAdd(studentDto);
    }
    // 등록3
    @PostMapping("/enroll")
    public boolean eAdd(@RequestBody EnrollDto enrollDto){
        return crService.eAdd(enrollDto);
    }
    // 조회1
    @GetMapping("/enrollid")
    public List<EnrollDto> eView(){
        return crService.eView();
    }
    // 조회2
    @GetMapping("/courseid")
    public 
    // 삭제
    @DeleteMapping("/studentid")
    public 
}
