package com.example.monkeyBes.service.Implementation;

import com.example.monkeyBes.persistence.access.StockRepository;
import com.example.monkeyBes.persistence.model.SkuProduct;
import com.example.monkeyBes.persistence.model.Stock;
import com.example.monkeyBes.persistence.model.StockMovement;
import org.springframework.stereotype.Service;
import com.example.monkeyBes.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService implements GenericService<UUID, Stock> {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public int updateStockQuantity(SkuProduct product, StockMovement.MovementType type, int quantity) {
        // Busca o estoque existente ou cria um novo com quantidade 0 (primeira movimentação)
        Stock stock = stockRepository.findByProduct(product)
                .orElseGet(() -> Stock.builder().product(product).quantity(0).build());

        int currentQuantity = stock.getQuantity();
        int newQuantity = currentQuantity;

        if (type == StockMovement.MovementType.IN) {
            newQuantity = currentQuantity + quantity;
        } else if (type == StockMovement.MovementType.OUT) {
            newQuantity = currentQuantity - quantity;
            if (newQuantity < 0) {
                throw new IllegalStateException("Insufficient stock for product " + product.getSkuCode());
            }
        }

        stock.setQuantity(newQuantity);
        stockRepository.save(stock);
        return newQuantity;
    }

    @Override
    public void create(Stock entity) {
        stockRepository.save(entity);
    }

    @Override
    public Stock update(Stock entity) {
        // Update desativado: deve ser feito via updateStockQuantity (e StockMovementService) para garantir a auditoria.
        return null;
    }

    @Override
    public Stock delete(UUID entityId) {
        return null;
    }

    @Override
    public Optional<Stock> find(UUID entityId) {
        return stockRepository.findById(entityId);
    }

    @Override
    public List<Stock> findAll() {
        return (stockRepository.findAll());
    }
}