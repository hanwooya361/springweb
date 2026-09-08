package example.Practice4.controller;

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
import example.Practice4.service.CourseService;

@RestController 
@RequestMapping("/api/course")
public class CourseController {
    @Autowired private CourseService CourseService;

    // 과정 등록
    @PostMapping("")
    public boolean cAdd(@RequestBody CourseDto courseDto){
        return CourseService.cAdd(courseDto);
    }
    
    // 과정 전체조회
    @GetMapping("")
    public List<CourseDto> findAll(){
        return CourseService.findAll();
    }
    
}
