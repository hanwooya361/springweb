package example.Practice4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice4.model.dto.CourseDto;
import example.Practice4.model.dto.EnrollDto;
import example.Practice4.model.dto.StudentDto;
import example.Practice4.model.entity.CourseEntity;
import example.Practice4.model.entity.EnrollEntity;
import example.Practice4.model.entity.StudentEntity;
import example.Practice4.model.repository.CourseRepository;
import example.Practice4.model.repository.EnrollRepository;
import example.Practice4.model.repository.StudentRepository;

@Service 
public class CourseService {
    @Autowired private CourseRepository courseRepository;

    // 과정 등록
    public boolean cAdd(CourseDto courseDto){
        // 1. dto -> entity
        CourseEntity courseEntity = courseDto.toEntity();
        // 2. entity 저장
        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        // 3. 등록된 entity에 pk가 존재하면 성공
        if(savedCourseEntity.getCourseId() >= 1){return true;}
        return false;
    }

    // 2. 전체조회
    public List<CourseDto> findAll( ){
        // 1. findAll 전체조회
        List<CourseEntity> courseEntities = courseRepository.findAll(); 
        // 2. 전체조회 Entity --> dto 변환
        List<CourseDto> courseDtos = new ArrayList<>();
        courseEntities.forEach( (courseEntity) -> { // 2-1: 하나씩 과정엔티티 꺼내서 
            CourseDto courseDto = CourseDto.from(courseEntity); // 2-2: 과정엔티티 -> 과정dto 변환
            // *** 과정DTO에 학생목록 추가!!! ***
            // * 현재 과정(course) ---> 수강기록(enroll)들을 반복하여 --> 수강기록 --> 학생(student)
            courseEntity.getEnrollList().forEach( (enroll) -> {
                StudentDto studentDto = StudentDto.from( enroll.getStudentEntity() );
                courseDto.getStudentDtos().add(studentDto);
            });
            courseDtos.add(courseDto); // 2-3 변환된 dto 리스트에 저장
        });
        // 3.
        return courseDtos;
    }
    
}
