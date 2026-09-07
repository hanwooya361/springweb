package example.Practice4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice4.model.dto.StudentDto;
import example.Practice4.model.entity.StudentEntity;
import example.Practice4.service.StudentService;

@RestController 
@RequestMapping("/api/student")
public class StudentController {
    @Autowired private StudentService studentService;

    // 등록2
    @PostMapping("")
    public boolean sAdd(@RequestBody StudentDto studentDto){
        return studentService.sAdd(studentDto);
    }
    // 삭제
    @DeleteMapping("")
    public boolean studentDelete(@RequestParam (name="studentId")int studentId){
        return studentService.studentDelete(studentId);
    }
}
