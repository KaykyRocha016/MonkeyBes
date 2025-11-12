package service.Implementation;

import com.example.monkeyBes.persistence.access.ItemRepository;
import com.example.monkeyBes.persistence.model.Item;
import service.GenericService;

import java.util.UUID;

public class ItemService implements GenericService<UUID, Item> {
    private ItemRepository repository;
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
    public Item find(UUID entityId) {
        return null;
    }
}
