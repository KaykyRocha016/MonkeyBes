package com.example.monkeyBes.service.Implementation;

import com.example.monkeyBes.persistence.access.SkuProductRepository;
import com.example.monkeyBes.persistence.model.SkuProduct;
import com.example.monkeyBes.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProductService implements GenericService<UUID, SkuProduct> {
    private final SkuProductRepository repository;

    public ProductService(SkuProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(SkuProduct entity) {
        repository.save(entity);
    }

    @Override
    public SkuProduct update(SkuProduct entity) {
        return null;
    }

    @Override
    public SkuProduct delete(UUID entityId) {
        return null;
    }

    @Override
    public Optional<SkuProduct> find(UUID entityId) {
        return null;
    }

    @Override
    public List<SkuProduct> findAll() {
        return repository.findAll();
    }
}
