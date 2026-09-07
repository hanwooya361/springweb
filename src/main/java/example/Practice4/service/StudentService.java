package example.Practice4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice4.model.dto.StudentDto;
import example.Practice4.model.entity.StudentEntity;
import example.Practice4.model.repository.StudentRepository;

@Service 
public class StudentService {
    @Autowired private StudentRepository studentRepository;

    // 등록2
    public boolean sAdd(StudentDto studentDto){
        StudentEntity studentEntity = studentDto.toEntity();
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        if(savedStudentEntity.getStudentId()>=1){return true;}
        return false;
    }

    // 삭제
    public boolean studentDelete(int studentId){
        studentRepository.deleteById(studentId);
        return true;
    }
}
