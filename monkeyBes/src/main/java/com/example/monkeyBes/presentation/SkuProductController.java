package com.example.monkeyBes.presentation;

import com.example.monkeyBes.persistence.model.SkuProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.monkeyBes.service.Implementation.ItemService;
import com.example.monkeyBes.service.Implementation.ProductService;
import com.example.monkeyBes.service.Implementation.StockService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/skus")
public class SkuProductController {
    private final ProductService productService;


    public SkuProductController( ProductService productService) {
        this.productService = productService;
    }

    // 🔹 Listar todos os SKUs
    @GetMapping
    public ResponseEntity<List<SkuProduct>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    // 🔹 Buscar SKU por ID
    @GetMapping("/{id}")
    public ResponseEntity<SkuProduct> getById(@PathVariable UUID id) {
        var product = productService.find(id);
        if(product.isPresent()) {
            return ResponseEntity.ok(product.get());
        };
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<SkuProduct> create(@RequestBody SkuProduct product) {
        productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductSku> update(@PathVariable Long id, @RequestBody ProductSku sku) {
//        return service.update(id, sku)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // 🔹 Deletar SKU
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (service.delete(id)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
