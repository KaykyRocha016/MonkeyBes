package com.example.monkeyBes.service.Implementation;

import com.example.monkeyBes.persistence.access.ItemRepository;
import com.example.monkeyBes.persistence.model.Item;
import org.springframework.stereotype.Service;
import com.example.monkeyBes.service.GenericService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
}
