package com.example.monkeyBes.persistence;

import com.example.monkeyBes.persistence.model.Stock;
import com.example.monkeyBes.presentation.dto.StockDTO;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockDTO toDTO(Stock stock) {
        if (stock == null) return null;

        return StockDTO.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .productId(stock.getProduct() != null ? stock.getProduct().getId() : null)
                .productName(stock.getProduct() != null ? stock.getProduct().getName() : null)
                .productSkuCode(stock.getProduct() != null ? stock.getProduct().getSkuCode() : null)
                .build();
    }
}

