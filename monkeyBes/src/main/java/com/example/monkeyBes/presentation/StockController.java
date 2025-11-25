package com.example.monkeyBes.presentation;

import com.example.monkeyBes.persistence.model.Stock;
import com.example.monkeyBes.persistence.model.StockMovement;
import com.example.monkeyBes.service.Implementation.StockMovementService;
import com.example.monkeyBes.service.Implementation.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock")
@Tag(name = "Estoque", description = "Operações de consulta e movimentação de estoque.")
public class StockController {
    private final StockService stockService;
    private final StockMovementService stockMovementService;

    public StockController(StockService stockService, StockMovementService stockMovementService) {
        this.stockService = stockService;
        this.stockMovementService = stockMovementService;
    }

    @Operation(summary = "Lista todos os saldos de estoque atuais.")
    @GetMapping
    public ResponseEntity<List<Stock>> getAll() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @Operation(summary = "Busca o saldo de estoque por ID do registro de estoque.")
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getById(@PathVariable UUID id) {
        var stock = stockService.find(id);
        if(stock.isPresent()) { return ResponseEntity.ok(stock.get()); };
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Registra uma nova movimentação de estoque (Entrada ou Saída).",
            description = "Este é o método obrigatório para alterar o estoque, garantindo o histórico à prova de alterações.")
    @PostMapping("/movement")
    public ResponseEntity<StockMovement> recordMovement(@RequestParam UUID productId,
                                                        @RequestParam StockMovement.MovementType type,
                                                        @RequestParam int quantity,
                                                        @RequestParam String reason) {
        try {
            StockMovement movement = stockMovementService.recordMovement(productId, type, quantity, reason);
            return ResponseEntity.status(HttpStatus.CREATED).body(movement);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca o histórico de movimentações do estoque por ID do produto.")
    @GetMapping("/history/{productId}")
    public ResponseEntity<List<StockMovement>> getHistoryByProduct(@PathVariable UUID productId) {
        List<StockMovement> history = stockMovementService.getHistoryByProduct(productId);
        return ResponseEntity.ok(history);
    }
}