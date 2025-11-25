package com.example.monkeyBes.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock_movement")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private SkuProduct product;

    // Tipo de Movimentação: ENTRADA (IN) ou SAÍDA (OUT)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    // Quantidade movida
    @Column(nullable = false)
    private Integer quantity;

    // Saldo do estoque após a movimentação (para fins de auditoria)
    @Column(nullable = false)
    private Integer stockAfterMovement;

    // Motivo ou origem da movimentação (e.g., Venda, Compra/Recebimento, Ajuste)
    @Column(length = 50)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Enum para o tipo de movimentação
    public enum MovementType {
        IN, // Entrada (Recebimento, Ajuste Positivo)
        OUT // Saída (Venda, Ajuste Negativo)
    }
}