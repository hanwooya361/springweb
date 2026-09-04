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
import lombok.Builder;
import lombok.ToString;

@Entity @Table(name = "blood")
public class BloodEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer blood_pack_id;
    @Column(length = 3, nullable = false)
    private String blood_type;
    @Column(nullable = false)
    private LocalDate expiration_date;
    @Column(nullable = false)
    private LocalDate received_date;
    @Column(nullable = true)
    private LocalDate shipment_date;
    @Column(columnDefinition = "varchar(20) default '보관중'")
    private String status;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private DonationEntity donationEntity;

}
