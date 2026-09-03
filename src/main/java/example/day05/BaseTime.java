package example.day05;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@MappedSuperclass   // 엔티티 상속
@EntityListeners(AuditingEntityListener.class)  // 엔티티의 생명주기 이벤트 감지 구현체
public class BaseTime {
    // 1. 레코드 생성시점
    @CreatedDate    // 현재 일시 자동 기록
    private LocalDateTime createDate;
    // 2. 레코드 변경시점
    @LastModifiedDate   // 변경 일시 자동 변경
    private LocalDateTime updateDate;
}
