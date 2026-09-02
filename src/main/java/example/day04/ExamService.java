package example.day04;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service    // 해당 클래스가 비지니스로직 담당하는 객체(빈) 등록
@RequiredArgsConstructor
public class ExamService {
    // * 리포지토리객체 (주입)불러오기
    private final ExamRepository examRepository;

    // [1] 전채조회
    public List<ExamEntity> 전체조회(){
        // 리포지토리 호출
        // 리포지토리객체.findAll(): (구현체) select sql지원
        // 반환값: 매핑된 테이블의 모든 레코드(엔티티)로 반환
        return examRepository.findAll();
    }

    // [2] 등록
    public boolean 저장(ExamEntity entity){
        // 리포지토리 호출
        // 리포지토리객체.save(저장할entity): insert sql지원
        // 반환값: 영속(매핑/저장)된 엔티티 반환
        ExamEntity saved = examRepository.save(entity);
        // 즉] 만약 save된 엔티티가 pk가 존재하면 저장 성공
        if(saved.getEno() >= 1) return true;
        // pk가 없으면 저장 실패
        return false;
    }

    // [3] 삭제
    public boolean 삭제(int no){
        // 리포지토리 호출
        // 리포지토리객체.deleteById(삭제할PK번호): delete sql지원
        // 반환타입: 없음, 삭제여부 find
        examRepository.deleteById(no);
        return true;
    }

    // [4] 수정: 트랜젝션 필수!
    // @Transactional  // 트랜젝션이란? 여러개 sql 하나의 (논리)단위로 묶음
    // 만약 여러개 sql중 하나라도 sql오류이면 전체 rollback(취소) 모두 성공하면 commit(완료)
    // 활용처: 계좌이체(입금/출금), 회원가입포인트(회원가입/포인트지급): 2개 이상 기능을 하나로 묶음
    public boolean 수정(ExamEntity entity){
        // 1. 영속된 엔티티 조회[PK: 수정할 번호]
        // 리포지토리객체.findById(조회할pk번호): select sql 지원(1개만)
        // 반환타입: Optional<엔티티>
        // Optional 클래스란? 본문(객체) 감싼 클래스(why? null 예외를 안전하게 사용)
            // --> 만약 조회결과 엔티티가 없을때 .getEno() 오류가 발생
            // Optional<객체타입> 변수명; 객체 래핑해 null 검사 지원
            // 목적 -> 안전한 객체 시용
        Optional<ExamEntity> optional = examRepository.findById(entity.getEno());
        // 2. 조회된 결과 엔티티 여부 확인
        if(optional.isPresent()){   // 객체가 있으면 true 없으면 false
            ExamEntity savedEntity = optional.get(); // 래핑된 Optional에서 엔티티 꺼내기
            // 3. 만약 엔티티가 존재하면 수정, update 대신 setter
            savedEntity.setEname(entity.getEname());
            return true;
        }
        return false;
    }

}
