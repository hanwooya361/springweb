package example.Practice4;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="enroll")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EnrollEntity extends BaseTime{
        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Integer enrollId;
        @Column(nullable = false, length = 10)
        private String status;

        /* @OneToMany(mappedBy = "enrollEntity")
        @ToString.Exclude
        @Builder.Default
        private List<> */

        @ManyToOne
        @JoinColumn(name="courseId")
        private CourseEntity courseEntity;

        @ManyToOne
        @JoinColumn(name="studentId")
        private StudentEntity studentEntity;
}
