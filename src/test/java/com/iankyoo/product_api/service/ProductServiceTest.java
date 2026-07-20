package com.iankyoo.product_api.service;

import com.iankyoo.product_api.dto.ProductResponse;
import com.iankyoo.product_api.entity.Product;
import com.iankyoo.product_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void findAll_shouldReturnMappedPage(){
        Product product = new Product(1L, "testName", "testDescription", "test", new BigDecimal("100"), LocalDateTime.now());
        Pageable pageable = PageRequest.of(0,10);
        Page<Product> page = new PageImpl<>(List.of(product));

        when(productRepository.findAll(pageable)).thenReturn(page);

        Page<ProductResponse> result = productService.findAll(pageable);

        assertEquals(1, result.getContent().size());
        assertEquals("testName", result.getContent().get(0).name());
        assertEquals("testDescription", result.getContent().get(0).description());
        assertEquals("test", result.getContent().get(0).category());
        assertEquals(0, new BigDecimal("100").compareTo(result.getContent().get(0).price()));
        assertEquals(product.getCreatedAt(), result.getContent().get(0).createdAt());

        verify(productRepository).findAll(pageable);
    }

}
