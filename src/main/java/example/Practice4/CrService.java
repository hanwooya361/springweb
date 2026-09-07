package example.Practice4;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    // 조회
    public List<EnrollDto> eView(){
        List<EnrollEntity> entities = enrollRepository.findAll();
        List<EnrollDto> list = new ArrayList<>();
        entities.forEach((entity)->{
            EnrollDto dto = EnrollDto.from(entity);
            list.add(dto);
        });
        return list;
    }

}
