package com.example.monkeyBes.presentation;

import com.example.monkeyBes.persistence.SkuProductMapper;
import com.example.monkeyBes.persistence.model.SkuProduct;
import com.example.monkeyBes.presentation.dto.SkuProductDTO;
import com.example.monkeyBes.service.Implementation.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/skus")
public class SkuProductController {
    private final ProductService productService;
    private final SkuProductMapper productMapper;

    public SkuProductController(ProductService productService, SkuProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<SkuProductDTO>> getAll() {
        List<SkuProductDTO> products = productService.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkuProductDTO> getById(@PathVariable UUID id) {
        return productService.find(id)
                .map(productMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<SkuProductDTO> create(@RequestBody SkuProduct product) {
        product.setStock(null);
        product.setItems(null);
        productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDTO(product));
    }
}
