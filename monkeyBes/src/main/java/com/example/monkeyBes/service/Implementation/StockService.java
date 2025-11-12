package com.example.monkeyBes.service.Implementation;

import com.example.monkeyBes.persistence.access.StockRepository;
import com.example.monkeyBes.persistence.model.Stock;
import org.springframework.stereotype.Service;
import com.example.monkeyBes.service.GenericService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class StockService implements GenericService<UUID, Stock> {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void create(Stock entity) {
        stockRepository.save(entity);
    }

    @Override
    public Stock update(Stock entity) {
        return null;
    }

    @Override
    public Stock delete(UUID entityId) {
        return null;
    }

    @Override
    public Optional<Stock> find(UUID entityId) {
        return null;
    }

    @Override
    public List<Stock> findAll() {
        return (stockRepository.findAll());
    }
}
