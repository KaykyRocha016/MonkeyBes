package com.example.monkeyBes.persistence.access;

import com.example.monkeyBes.persistence.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID>  { }
