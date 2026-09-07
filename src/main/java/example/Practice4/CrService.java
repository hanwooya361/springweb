package example.Practice4;

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
public class CrService {
    @Autowired private CourseRepository courseRepository;
    @Autowired private EnrollRepository enrollRepository;
    @Autowired private StudentRepository studentRepository;

    // 등록1
    public boolean cAdd(CourseDto courseDto){
        CourseEntity courseEntity = courseDto.toEntity();
        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        if(savedCourseEntity.getCourseId() >= 1){return true;}
        return false;
    }
    // 등록2
    public boolean sAdd(StudentDto studentDto){
        StudentEntity studentEntity = studentDto.toEntity();
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        if(savedStudentEntity.getStudentId()>=1){return true;}
        return false;
    }
    // 등록3
    public boolean eAdd(EnrollDto enrollDto){
        EnrollEntity enrollEntity = enrollDto.toEntity();
        EnrollEntity savedEnrollEntity = enrollRepository.save(enrollEntity);
        if(savedEnrollEntity.getEnrollId()>=1){return true;}
        return false;
    }
    // 조회1
    public EnrollEntity eView(int enrollId){
        Optional<EnrollEntity> optional = enrollRepository.findById(enrollId);
        if(optional.isPresent()){
            EnrollEntity entity = optional.get();
            return entity;
        }
        return null;
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
    // 삭제
    public boolean studentDelete(int studentId){
        studentRepository.deleteById(studentId);
        return true;
    }
}
