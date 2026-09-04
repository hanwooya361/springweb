package example.day06;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reply")
@NoArgsConstructor@AllArgsConstructor@Builder@Data
public class ReplyEntity {
    @Id
    private Integer rno;
    private String rname;
    // 단방향 참조
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="bno")
    private BoardEntity boardEntity;
}
/*
    - 영속성? 자바는 영구저장이 불가능하므로 DB 매핑/연결해 영속성(영구저장) 표현
        Entity entity = new entity();   // 객체
        repository.save(), repository.findall, repository.findById() 등 결과 영속된 엔티티 반환
        즉] Entity 영속된entity = repository.save(비영속entity)
    - @ManyToOne(cascade = 영속성제약조건, fetch = 불러올시기)
    - CascadeType
        CascadeType.REMOVE: 만일 부모 엔티티가 삭제되면 자식 엔티티도 같이 삭제됨
        CascadeType.MERGE: 만일 부모 엔티티가 수정되면 자식 엔티티도 수정 같이 반영됨
        CascadeType.DETACH: 만일 부모 엔티티가 영속(매핑)해체되면 자식 엔티티도 같이 영속해체됨
        CascadeType.REFRESH: 만일 부모 엔티티가 재호출(갱신)되면 자식 엔티티도 같이 재호출(갱신)됨
        CascadeType.PERSIST: 만일 부모 엔티티가 저장되면 자식 엔티티도 같이 저장됨
        CascadeType.ALL: 위 전부 사용

    - fetch
        - FetchType.LAZY: 해당 엔티티 조회시 참조(자식)엔티티 불러오지 않음 
            장점: 초기로딩 빠름, 재사용성 느림, 필요한 정보만 불러옴<지연로딩>
        - FetchType.EAGER: 해당 엔티티 조회시 참조(자식)엔티티 즉시 불러옴 ,  기본값
            장점: 재사용성 빠르다, 불필요한 정보를 불러옴<성능저하>
*/