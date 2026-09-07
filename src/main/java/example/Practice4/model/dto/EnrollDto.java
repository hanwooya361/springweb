package example.Practice4.model.dto;

import java.time.LocalDateTime;

import example.Practice4.model.entity.EnrollEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollDto {
    private Integer enrollId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // jpa에서 entity로 fk 사용하지만 입력받을 경우 fk번호로 받음
    private Integer courseId;
    private Integer studentId;
    private String courseName;
    private String studentName;

    public EnrollEntity toEntity(){
        return EnrollEntity.builder()
        .status(this.status)
        // 학생fk, 과정fk은 서비스에서
        .build();
    }

    public static EnrollDto from(EnrollEntity enrollEntity){
        return EnrollDto.builder()
        .enrollId(enrollEntity.getEnrollId())
        .status(enrollEntity.getStatus())
        // 과정엔티티 내 과정명 조회
        .courseName(enrollEntity.getCourseEntity().getCourseName())
        // 학생엔티티 내 학생명 조회
        .studentName(enrollEntity.getStudentEntity().getStudentName())
        .createdAt(enrollEntity.getCreatedAt())
        .updatedAt(enrollEntity.getUpdatedAt())
        .build();
    }
}