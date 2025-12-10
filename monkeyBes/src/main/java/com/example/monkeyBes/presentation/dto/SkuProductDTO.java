package com.example.monkeyBes.presentation.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkuProductDTO {
    private UUID id;
    private String skuCode;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity; // Apenas a quantidade, sem o objeto completo
}
