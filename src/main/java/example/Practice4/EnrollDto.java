package example.Practice4;

import java.time.LocalDateTime;

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

    public EnrollEntity toEntity(){
        return EnrollEntity.builder()
        .status(this.status)
        .build();
    }

    public static EnrollDto from(EnrollEntity enrollEntity){
        return EnrollDto.builder()
        .enrollId(enrollEntity.getEnrollId())
        .status(enrollEntity.getStatus())
        .createdAt(enrollEntity.getCreatedAt())
        .updatedAt(enrollEntity.getUpdatedAt())
        .build();
    }
}