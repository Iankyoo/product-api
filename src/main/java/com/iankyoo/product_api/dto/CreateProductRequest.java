package com.iankyoo.product_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "name is required")
        String name,
        String description,
        @Positive(message = "price must be positive")
        @NotNull(message = "price is required")
        BigDecimal price,
        @NotBlank(message = "category is required")
        String category) {
}
