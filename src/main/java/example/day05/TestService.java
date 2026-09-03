package example.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TestService {
    @Autowired private TestRepository testRepository;

    // 1. 전체조회
    public List<TestDto> 전체조회(){
        // 1.  모든 엔티티 조회
        List<TestEntity> entities = testRepository.findAll();
        // 2. 모든 엔티티 -> DTO 변환
        // 빈 리스트 생성
        List<TestDto> list = new ArrayList<>();
        // 모든 엔티티 반복해 모든 DTO 변환해서 새로운 리스트에 저장
        // 리스트객체.forEach((반복변수)->{})
        entities.forEach((entity)->{
            // - 리스트내 하나씩 entity(반복변수)에 대입 반복
            // - TestDto내 entity -> dto 변환함수 : from
            TestDto dto = TestDto.from(entity);
            // - 변환 결과 새로운 리스트에 담기
            list.add(dto);
        });
        return list;
    }

    // 2. 등록
    public boolean 저장(TestDto testDto){
        // 1. dto -> 엔티티 변환함수: toEntity 함수
        TestEntity testEntity = testDto.toEntity();
        // 2. entity save 저장
        TestEntity savedEntity = testRepository.save(testEntity);
        // 3. 
        if(savedEntity.getNo() >= 1){return true;}
        return false;
    }

    // 3. 수정
    @Transactional
    public boolean 수정(TestDto testDto){
        // 1. 수정할 엔티티 찾기. pk
        Optional<TestEntity> optional = testRepository.findById(testDto.getNo());
        // 2. 찾은 엔티티가 존재하면 
        if(optional.isPresent()){
            // 3. 엔티티 꺼내기
            TestEntity entity = optional.get();
            // 4. setter 메소드 이용한 수정
            entity.setPrice(testDto.getPrice());
            entity.setDescri(testDto.getDescri());
            return true;
        }
        return false;
    }

}
