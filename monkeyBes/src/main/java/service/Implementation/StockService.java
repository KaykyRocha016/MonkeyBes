package service.Implementation;

import com.example.monkeyBes.persistence.access.StockRepository;
import com.example.monkeyBes.persistence.model.Stock;
import org.springframework.stereotype.Service;
import service.GenericService;

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
    public Stock find(UUID entityId) {
        return null;
    }
}
