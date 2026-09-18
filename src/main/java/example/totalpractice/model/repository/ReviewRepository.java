package example.totalpractice.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.totalpractice.model.entity.ReviewsEntity;

@Repository 
public interface ReviewRepository extends JpaRepository <ReviewsEntity , Integer > {
}