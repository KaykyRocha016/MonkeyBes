package com.example.monkeyBes.presentation;

import com.example.monkeyBes.persistence.model.SkuProduct;
import com.example.monkeyBes.persistence.model.Stock;
import com.example.monkeyBes.service.Implementation.ProductService;
import com.example.monkeyBes.service.Implementation.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;


    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAll() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getById(@PathVariable UUID id) {
        var stock = stockService.find(id);
        if(stock.isPresent()) { return ResponseEntity.ok(stock.get()); };
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Stock> create(@RequestBody Stock stock) {
        stockService.create(stock);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
