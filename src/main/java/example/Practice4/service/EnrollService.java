package example.Practice4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice4.model.dto.EnrollDto;
import example.Practice4.model.entity.EnrollEntity;
import example.Practice4.model.repository.EnrollRepository;

@Service 
public class EnrollService {
    @Autowired private EnrollRepository enrollRepository;

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
}
