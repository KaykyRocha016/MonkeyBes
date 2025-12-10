package com.example.monkeyBes.presentation;

import com.example.monkeyBes.persistence.StockMapper;
import com.example.monkeyBes.persistence.StockMovementMapper;
import com.example.monkeyBes.presentation.dto.StockDTO;
import com.example.monkeyBes.presentation.dto.StockMovementDTO;
import com.example.monkeyBes.presentation.dto.StockMovementRequest;

import com.example.monkeyBes.service.Implementation.StockMovementService;
import com.example.monkeyBes.service.Implementation.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stock")
@Tag(name = "Estoque", description = "Operações de consulta e movimentação de estoque.")
public class StockController {
    private final StockService stockService;
    private final StockMovementService stockMovementService;
    private final StockMapper stockMapper;
    private final StockMovementMapper movementMapper;

    public StockController(StockService stockService,
                           StockMovementService stockMovementService,
                           StockMapper stockMapper,
                           StockMovementMapper movementMapper) {
        this.stockService = stockService;
        this.stockMovementService = stockMovementService;
        this.stockMapper = stockMapper;
        this.movementMapper = movementMapper;
    }

    @Operation(summary = "Lista todos os saldos de estoque atuais.")
    @GetMapping
    public ResponseEntity<List<StockDTO>> getAll() {
        List<StockDTO> stocks = stockService.findAll().stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(stocks);
    }

    @Operation(summary = "Busca o saldo de estoque por ID do registro de estoque.")
    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getById(@PathVariable UUID id) {
        return stockService.find(id)
                .map(stockMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Registra uma nova movimentação de estoque (Entrada ou Saída).",
            description = "Este é o método obrigatório para alterar o estoque, garantindo o histórico à prova de alterações.")
    @PostMapping("/movement")
    public ResponseEntity<StockMovementDTO> recordMovement(@RequestBody StockMovementRequest request) {
        try {
            var movement = stockMovementService.recordMovement(
                    request.getProductId(),
                    request.getType(),
                    request.getQuantity(),
                    request.getReason()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(movementMapper.toDTO(movement));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca o histórico de movimentações do estoque por ID do produto.")
    @GetMapping("/history/{productId}")
    public ResponseEntity<List<StockMovementDTO>> getHistoryByProduct(@PathVariable UUID productId) {
        List<StockMovementDTO> history = stockMovementService.getHistoryByProduct(productId).stream()
                .map(movementMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(history);
    }
}
