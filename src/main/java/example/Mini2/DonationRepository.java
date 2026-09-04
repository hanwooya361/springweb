package example.Mini2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<DonationEntity, Integer>{
    
}
