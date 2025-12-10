package com.example.monkeyBes.presentation.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    private UUID id;
    private Integer quantity;
    private UUID productId;
    private String productName;
    private String productSkuCode;
}
