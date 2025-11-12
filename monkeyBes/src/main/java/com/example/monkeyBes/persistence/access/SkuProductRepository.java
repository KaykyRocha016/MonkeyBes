package com.example.monkeyBes.persistence.access;

import com.example.monkeyBes.persistence.model.SkuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkuProductRepository extends JpaRepository<SkuProduct, Long> {

    Optional<SkuProduct> findBySkuCode(String skuCode);

    boolean existsBySkuCode(String skuCode);
}