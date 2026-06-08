package com.iankyoo.product_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @NotBlank(message = "name is required")
        String name,
        String description,
        @NotNull(message = "price is required")
        @Positive(message = "price must be positive")
        BigDecimal price,
        @NotBlank(message = "category is required")
        String category
) {}
