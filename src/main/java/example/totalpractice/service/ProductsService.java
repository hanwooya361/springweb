package example.totalpractice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.totalpractice.model.dto.ProductResponseDto;
import example.totalpractice.model.entity.ProductsEntity;
import example.totalpractice.model.repository.ProductsRepository;
import example.totalpractice.model.dto.CategoryDto;
import example.totalpractice.model.dto.ProductDto;
import example.totalpractice.model.entity.CategoryEntity;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    // 1. 제품 조회
    public List<ProductResponseDto> productFindAll() {
        List<ProductsEntity> productsEntities = productsRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        productsEntities.forEach((productsEntity) -> {
            ProductResponseDto productResponseDto = ProductResponseDto.from(productsEntity);
            productResponseDto.setCno(productsEntity.getCategoryEntity().getCno());
            productResponseDto.setCategoryname(productsEntity.getCategoryEntity().getName());
            productResponseDtos.add(productResponseDto);
        });
        return productResponseDtos;
    }

    // 2. 제품 등록
    public ProductDto productSave(ProductDto productDto) {
        ProductsEntity productsEntity = productDto.toEntity();
        ProductsEntity savedEntity = productsRepository.save(productsEntity);
        if (savedEntity.getBno() >= 1)
            return productDto;
        return null;
    }

    // 3. 제품 수정
    public boolean productUpdate(ProductDto productDto) {
        Optional<ProductsEntity> optional = productsRepository.findById(productDto.getBno());
        if (optional.isPresent()) {
            ProductsEntity productsEntity = optional.get();
            productsEntity.setName(productDto.getName());
            productsEntity.setPrice(productDto.getPrice());
            productsEntity.getCategoryEntity().setCno(productDto.getCno());
            productsRepository.save(productsEntity);
            return true;
        }
        return false;
    }

    // 4. 제품 삭제
    public boolean productDelete(Integer bno) {
        Optional<ProductsEntity> optional = productsRepository.findById(bno);
        if (optional.isPresent()) {
            ProductsEntity productsEntity = optional.get();
            productsRepository.delete(productsEntity);
            return true;
        }
        return false;
    }
}