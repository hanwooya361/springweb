package example.Mini2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "transfusion")
@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class TranfusionEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer request_id;
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private MemberEntity memberEntity;
    @Column(length = 20)
    private String request_type;
    @Column(length = 30)
    private String patient_name;
    @Column(length = 100)
    private String hospital_name;
    @Column(length = 5)
    private String blood_type;
    private Integer requested_quantity;
    private LocalDate deadline;
    @Column( columnDefinition = "varchar(100) default '대기중' not null")
    private String status;
    @Column(columnDefinition = "DATE")
    private String created_at;
    @OneToMany(mappedBy = "tranfusionEntity")
    @ToString.Exclude
    @Builder.Default
    private List<MatchingEntity> matchingList = new ArrayList<>();
}