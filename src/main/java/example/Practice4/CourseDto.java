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
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CourseEntity toEntity(){
        return CourseEntity.builder()
        .courseName(this.courseName)
        .build();
    }

    public static CourseDto from(CourseEntity courseEntity){
        return CourseDto.builder()
        .courseId(courseEntity.getCourseId())
        .courseName(courseEntity.getCourseName())
        .createdAt(courseEntity.getCreatedAt())
        .updatedAt(courseEntity.getUpdatedAt())
        .build();
    } 
     
}
