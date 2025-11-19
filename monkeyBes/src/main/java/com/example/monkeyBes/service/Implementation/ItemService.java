package com.example.monkeyBes.service.Implementation;

import com.example.monkeyBes.persistence.access.ItemRepository;
import com.example.monkeyBes.persistence.model.Item;
import com.example.monkeyBes.persistence.model.SkuProduct;
import org.springframework.stereotype.Service;
import com.example.monkeyBes.service.GenericService;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ItemService implements GenericService<UUID, Item> {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    @Override

    public void create(Item item) {
        repository.save(item);

    }

    @Override
    public Item update(Item entity) {
        return null;
    }

    @Override
    public Item delete(UUID entityId) {
        return null;
    }

    @Override
    public Optional<Item> find(UUID entityId) {
        return null;
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    public Map<String, Object> getFullSalesReport() {
        Map<String, Object> report = new HashMap<>();

        List<Map<String, Object>> mostSoldList = formatResult(repository.findMostSoldProducts());
        report.put("mostSoldProducts", mostSoldList);

        List<Map<String, Object>> leastSoldList = leastSoldList = formatResult(repository.findLeastSoldProducts());
        report.put("leastSoldProducts", leastSoldList);

        return report;
    }

    private List<Map<String, Object>> formatResult(List<Object[]> results) {

        List<Map<String, Object>> formattedList = new ArrayList<>();

        for(Object[] item : results) {
            SkuProduct product = (SkuProduct) item[0];
            Long totalSold = (Long) item[1];

            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("product", product.getName());
            itemMap.put("sku", product.getSkuCode());
            itemMap.put("totalSold", totalSold);
            itemMap.put("amount", product.getPrice().multiply(BigDecimal.valueOf(totalSold)));

            formattedList.add(itemMap);
        }
        return formattedList;
    }
}
