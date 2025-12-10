package com.example.monkeyBes.presentation.dto;


import com.example.monkeyBes.persistence.model.StockMovement;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementRequest {
    private UUID productId;
    private StockMovement.MovementType type;
    private Integer quantity;
    private String reason;
}

