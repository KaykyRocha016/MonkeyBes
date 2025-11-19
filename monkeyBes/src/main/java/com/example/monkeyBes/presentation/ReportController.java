package com.example.monkeyBes.presentation;

import com.example.monkeyBes.service.Implementation.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ItemService itemService;

    public ReportController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/sales")
    public ResponseEntity<Map<String, Object>> getSalesReport() {
        return ResponseEntity.ok(itemService.getFullSalesReport());
    }
}
