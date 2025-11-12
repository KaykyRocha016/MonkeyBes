package com.example.monkeyBes.presentation;

import com.example.monkeyBes.persistence.access.SkuProductRepository;
import com.example.monkeyBes.persistence.model.SkuProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skus")
public class SkuProductController {
    private final SkuProductRepository repository;

    public SkuProductController(SkuProductRepository repository) {
        this.repository = repository;
    }

    // 🔹 Listar todos os SKUs
    @GetMapping
    public ResponseEntity<List<SkuProduct>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    // 🔹 Buscar SKU por ID
    @GetMapping("/{id}")
    public ResponseEntity<SkuProduct> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Criar novo SKU
//    @PostMapping
//    public ResponseEntity<ProductSku> create(@RequestBody ProductSku sku) {
//        ProductSku saved = service.create(sku);
//        URI location = URI.create("/api/skus/" + saved.getId());
//        return ResponseEntity.created(location).body(saved);
//    }
//
//    // 🔹 Atualizar SKU existente
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
