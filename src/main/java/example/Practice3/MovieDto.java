package example.Practice3;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    private Integer movieid;
    private String title;
    private String director;
    private String releasedate; 
    private Double rating;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    public MovieEntity toEntity(){
        return MovieEntity.builder()
        .title(this.title)
        .director(this.director)
        .releasedate(this.releasedate)
        .rating(this.rating)
        .build();
    }
    

    public static MovieDto from(MovieEntity movieEntity){
        return MovieDto.builder()
        .movieid(movieEntity.getMovieid())
        .director(movieEntity.getDirector())
        .title(movieEntity.getTitle())
        .releasedate(movieEntity.getReleasedate())
        .rating(movieEntity.getRating())
        .createdDate(movieEntity.getCreateDate())
        .updateDate(movieEntity.getUpdateDate())
        .build();
    }
}
