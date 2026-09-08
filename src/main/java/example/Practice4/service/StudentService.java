package example.Practice4.service;

import java.util.Optional;

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
    public boolean studentDelete(Integer studentId){
        // 1. 학생번호로 학생엔티티 찾기
        Optional<StudentEntity> optional = studentRepository.findById(studentId);
        // 2. 만약 엔티티 존재하면
        if(optional.isPresent()){
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }
    
}
