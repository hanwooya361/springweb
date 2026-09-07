package example.Practice4;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice4.model.dto.CourseDto;
import example.Practice4.model.dto.EnrollDto;
import example.Practice4.model.dto.StudentDto;
import example.Practice4.model.entity.EnrollEntity;

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
    public EnrollEntity eView(@RequestParam (name="enrollId") int enrollId){
        return crService.eView(enrollId);
    }
    // 조회2
    @GetMapping("/courseid")
    public List<CourseDto> findAll(){
        return crService.findAll();
    }
    // 삭제
    @DeleteMapping("/studentid")
    public boolean studentDelete(@RequestParam (name="studentId")int studentId){
        return crService.studentDelete(studentId);
    }
}
