package example.Practice4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrRepository extends JpaRepository<CourseEntity, Integer>{
    
}
