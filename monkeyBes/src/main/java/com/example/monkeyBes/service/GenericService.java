package com.example.monkeyBes.service;

import java.util.List;
import java.util.Optional;

public interface GenericService <K,E>{
    void create(E entity);
    E  update(E entity);
    E delete(K entityId);
    Optional<E> find(K entityId);
    List<E> findAll();
}
