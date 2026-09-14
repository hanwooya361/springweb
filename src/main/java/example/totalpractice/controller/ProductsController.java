package example.totalpractice.controller;

import example.totalpractice.model.dto.ProductDto;
import example.totalpractice.model.dto.ProductResponseDto;
import example.totalpractice.service.ProductsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "http://localhost:5173")
@RestController
@RequestMapping("api/products")
public class ProductsController {
    @Autowired private ProductsService productsService; 

    @GetMapping("")
    public List<ProductResponseDto> productFindAll() {
        return productsService.productFindAll();
    }

    @PostMapping("")
    public ProductDto productSave(@RequestBody ProductDto productDto) {
        return productsService.productSave(productDto);
    }

    @PutMapping("")
    public boolean productUpdate(@RequestBody ProductDto productDto) {
        return productsService.productUpdate(productDto);
    }

    @DeleteMapping("")
    public boolean productDelete(
            @RequestParam(name = "bno") Integer bno) {
        return productsService.productDelete(bno);
    }
}