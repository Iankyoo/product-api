package com.iankyoo.product_api.service;

import com.iankyoo.product_api.dto.CreateProductRequest;
import com.iankyoo.product_api.dto.ProductResponse;
import com.iankyoo.product_api.dto.UpdateProductRequest;
import com.iankyoo.product_api.entity.Product;
import com.iankyoo.product_api.exception.ProductNotFoundException;
import com.iankyoo.product_api.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getCreatedAt()
        );
    }

    public Page<ProductResponse> findAll(Pageable pageable){
        return repository.findAll(pageable)
                .map(this::toResponse);
    }

    public ProductResponse findById(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return toResponse(product);
    }

    public Page<ProductResponse> findByName(String name, Pageable pageable){
        return repository.findByNameContainingIgnoreCase(name, pageable)
                .map(this::toResponse);
    }

    public ProductResponse createProduct(CreateProductRequest request){
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .category(request.category())
                .description(request.description())
                .build();
        return toResponse(product);
    }

    public void delete(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        repository.delete(product);
    }

    public ProductResponse update(Long id, UpdateProductRequest request){
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setCategory(request.category());
        Product saved = repository.save(product);
        return toResponse(saved);
    }
}
