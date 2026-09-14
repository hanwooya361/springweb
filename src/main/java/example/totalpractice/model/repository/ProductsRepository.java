package example.totalpractice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.totalpractice.model.entity.ProductsEntity;

@Repository
public interface ProductsRepository
        extends JpaRepository<ProductsEntity, Integer> {

}