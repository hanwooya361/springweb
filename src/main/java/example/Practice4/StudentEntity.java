package example.Practice4;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentEntity extends BaseTime{
        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Integer studentId;
}
