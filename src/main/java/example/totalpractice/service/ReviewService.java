package example.totalpractice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.totalpractice.model.dto.ReviewsDto;
import example.totalpractice.model.entity.ReviewsEntity;
import example.totalpractice.model.entity.ProductsEntity;
import example.totalpractice.model.repository.ReviewRepository;
import example.totalpractice.model.repository.ProductsRepository;

@Service
public class ReviewService {

    @Autowired private ReviewRepository reviewRepository;
    @Autowired private ProductsRepository productsRepository;

    public List<ReviewsDto> getreviews(Integer bno){
    List<ReviewsEntity> reviewEntities = reviewRepository.findAll();
    List<ReviewsDto> reviewDtos = new ArrayList<>();
        reviewEntities.forEach((reviewEntity) -> {
            if (reviewEntity.getProductEntity().getBno().equals(bno)) {
                ReviewsDto reviewDto = ReviewsDto.from(reviewEntity);
                reviewDtos.add(reviewDto);
            }
    });
    return reviewDtos;
}

    public boolean createreview(ReviewsDto reviewDto) {
        ProductsEntity productEntity = productsRepository.findById(reviewDto.getBno()).orElse(null);
        if (productEntity == null) return false;

        ReviewsEntity reviewEntity = reviewDto.toEntity(productEntity);
        ReviewsEntity savedEntity = reviewRepository.save(reviewEntity);

        if (savedEntity.getRno() >= 1) return true;
        return false;
    }

    public boolean deletereview(Integer rno) {
        reviewRepository.deleteById(rno);
        return true;
    }
}