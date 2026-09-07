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

    // 등록1
    public boolean cAdd(CourseDto courseDto){
        CourseEntity courseEntity = courseDto.toEntity();
        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        if(savedCourseEntity.getCourseId() >= 1){return true;}
        return false;
    }
    
    // 조회2
    public List<CourseDto> findAll(){
        List<CourseEntity> entities = courseRepository.findAll();
        List<CourseDto> list = new ArrayList<>();
        entities.forEach((entity)->{
            CourseDto dto = CourseDto.from(entity);
            list.add(dto);
        });
        return list;
    }
    
}
