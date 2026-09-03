package example.Practice3;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="movie")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieEntity extends BaseTime{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer movieid;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 100)
    private String director;
    @Column(nullable = false, length = 100)
    private String releasedate; 
    @Column(columnDefinition = "double default '0' not null ", insertable = true , updatable = true)
    private Double rating;
}
