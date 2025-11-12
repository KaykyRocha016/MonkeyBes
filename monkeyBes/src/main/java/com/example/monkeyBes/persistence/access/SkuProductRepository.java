package com.example.monkeyBes.persistence.access;

import com.example.monkeyBes.persistence.model.SkuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SkuProductRepository extends JpaRepository<SkuProduct, UUID> {

    Optional<SkuProduct> findBySkuCode(String skuCode);

    boolean existsBySkuCode(String skuCode);
}