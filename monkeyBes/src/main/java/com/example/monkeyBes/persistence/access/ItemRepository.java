package com.example.monkeyBes.persistence.access;

import com.example.monkeyBes.persistence.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
