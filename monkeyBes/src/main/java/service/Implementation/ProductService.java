package service.Implementation;

import com.example.monkeyBes.persistence.access.SkuProductRepository;
import com.example.monkeyBes.persistence.model.SkuProduct;
import service.GenericService;

import java.util.UUID;

public class ProductService implements GenericService<UUID, SkuProduct> {
    private SkuProductRepository repository;
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
    public SkuProduct find(UUID entityId) {
        return null;
    }
}
