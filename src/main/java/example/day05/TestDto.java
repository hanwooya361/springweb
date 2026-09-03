package example.day05;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDto {
    // 엔티티와 동일하게 멤버변수 구성: 기능별로 DTO 구성 ex] 등록DTO, 조회DTO, 수정DTO 등
    private Integer no;
    private String name;
    private String descri;
    private Integer price;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // DTO --> 엔티티 함수(Entity 보호), 주로 save/update목적
    // this란? 해당 메소드 호출한 인스턴스 가리킴
    public TestEntity toEntity(){
        return TestEntity.builder() // 빌더패턴이란? new 대신 객체생성 메소드 하는 방식
        .name(this.name)
        .descri(this.descri)
        .price(this.price)
        .build();
    }
    
    // 엔티티 --> DTO 함수, from(Entity entity) 주로 find
    // static? 인스턴스없이 호출가능한 메소드/변수
    // static에는 this 없을까? 인스턴스없이 사용하는 메소드
    public static TestDto from(TestEntity testEntity){
        return TestDto.builder()    // 빌더ㅓ 시작, 순서/개수 상관없이 자유롭게 객체생성가능
        .no(testEntity.getNo())
        .name(testEntity.getName())
        .descri(testEntity.getDescri())
        .price(testEntity.getPrice())
        .createDate(testEntity.getCreateDate())
        .updateDate(testEntity.getUpdateDate())
        .build();
    }

}
