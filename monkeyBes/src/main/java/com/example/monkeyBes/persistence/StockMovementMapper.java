package com.example.monkeyBes.persistence;

import com.example.monkeyBes.persistence.model.StockMovement;
import com.example.monkeyBes.presentation.dto.StockMovementDTO;
import org.springframework.stereotype.Component;

@Component
public class StockMovementMapper {

    public StockMovementDTO toDTO(StockMovement movement) {
        if (movement == null) return null;

        return StockMovementDTO.builder()
                .id(movement.getId())
                .productId(movement.getProduct() != null ? movement.getProduct().getId() : null)
                .productName(movement.getProduct() != null ? movement.getProduct().getName() : null)
                .productSkuCode(movement.getProduct() != null ? movement.getProduct().getSkuCode() : null)
                .type(movement.getType())
                .quantity(movement.getQuantity())
                .stockAfterMovement(movement.getStockAfterMovement())
                .reason(movement.getReason())
                .createdAt(movement.getCreatedAt())
                .build();
    }
}
