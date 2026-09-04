package example.Practice4;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="student")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentEntity extends BaseTime{
        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Integer studentId;
        @Column(nullable = false, length = 20)
        private String studentName;

        @OneToMany(mappedBy = "studentEntity")
        @ToString.Exclude
        @Builder.Default
        private List<EnrollEntity> enrollList = new ArrayList<>();
}
