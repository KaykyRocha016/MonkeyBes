package com.example.monkeyBes.presentation.dto;

import com.example.monkeyBes.persistence.model.StockMovement;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementDTO {
    private UUID id;
    private UUID productId;
    private String productName;
    private String productSkuCode;
    private StockMovement.MovementType type;
    private Integer quantity;
    private Integer stockAfterMovement;
    private String reason;
    private LocalDateTime createdAt;
}
