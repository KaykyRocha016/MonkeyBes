package com.example.monkeyBes.service.Implementation;

import com.example.monkeyBes.persistence.access.SkuProductRepository;
import com.example.monkeyBes.persistence.access.StockMovementRepository;
import com.example.monkeyBes.persistence.model.SkuProduct;
import com.example.monkeyBes.persistence.model.StockMovement;
import com.example.monkeyBes.service.GenericService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockMovementService implements GenericService<UUID, StockMovement> {

    private final StockMovementRepository repository;
    private final SkuProductRepository productRepository;
    private final StockService stockService;

    public StockMovementService(StockMovementRepository repository, SkuProductRepository productRepository, StockService stockService) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.stockService = stockService;
    }

    @Transactional
    public StockMovement recordMovement(UUID productId, StockMovement.MovementType type, int quantity, String reason) {
        SkuProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        // 1. Update Stock Quantity
        int newStockQuantity = stockService.updateStockQuantity(product, type, quantity);

        // 2. Record the Movement (Audit Log)
        StockMovement movement = StockMovement.builder()
                .product(product)
                .type(type)
                .quantity(quantity)
                .reason(reason)
                .createdAt(LocalDateTime.now())
                .stockAfterMovement(newStockQuantity) // Ponto de auditoria
                .build();

        return repository.save(movement);
    }

    public List<StockMovement> getHistoryByProduct(UUID productId) {
        return repository.findByProductIdOrderByCreatedAtDesc(productId);
    }

    @Override
    public void create(StockMovement entity) {
        throw new UnsupportedOperationException("Use recordMovement method for creation/updates to ensure auditing.");
    }

    @Override
    public StockMovement update(StockMovement entity) {
        throw new UnsupportedOperationException("Stock movements are considered immutable for audit purposes.");
    }

    @Override
    public StockMovement delete(UUID entityId) {
        throw new UnsupportedOperationException("Stock movements cannot be deleted for audit purposes.");
    }

    @Override
    public Optional<StockMovement> find(UUID entityId) {
        return repository.findById(entityId);
    }

    @Override
    public List<StockMovement> findAll() {
        return repository.findAll();
    }
}