package example.Practice2;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final TestRepository testRepository;

    // 등록
    public boolean testWrite(TestEntity entity){
        TestEntity saved = testRepository.save(entity);
        if(saved.getTno() >= 1) return true;
        return false;
    }

    // 전체조회
    public List<TestEntity> testPrint(){
        return testRepository.findAll();
    }

    // 개별
    public Optional<TestEntity> testDetail(int tno){
        return testRepository.findById(tno);
    }

    /* // 개별(교수님)
    public TestEntity testDetail(int tno){
        Optional<TestEntity> optional = testRepository.findById(tno);
        if(optional.isPresent()){
            TestEntity entity = optional.get();
            return entity;
        }
        return null;
    } */

    // 삭제
    public boolean testDelete(int tno){
        testRepository.deleteById(tno);
        return true;
    }

    // 수정
    public boolean testUpdate(TestEntity entity){
        Optional<TestEntity> optional = testRepository.findById(entity.getTno());
        if(optional.isPresent()){
            TestEntity savedEntity = optional.get();
            savedEntity.setContent(entity.getContent());
            return true;
        }
        return false;
    }
    
}
