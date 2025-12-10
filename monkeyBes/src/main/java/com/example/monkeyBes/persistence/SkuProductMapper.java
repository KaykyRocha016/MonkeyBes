package com.example.monkeyBes.persistence;


import com.example.monkeyBes.persistence.model.SkuProduct;
import com.example.monkeyBes.presentation.dto.SkuProductDTO;
import org.springframework.stereotype.Component;

@Component
public class SkuProductMapper {

    public SkuProductDTO toDTO(SkuProduct product) {
        if (product == null) return null;

        return SkuProductDTO.builder()
                .id(product.getId())
                .skuCode(product.getSkuCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStock() != null ? product.getStock().getQuantity() : null)
                .build();
    }
}
