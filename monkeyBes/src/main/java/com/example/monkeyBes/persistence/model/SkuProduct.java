package com.example.monkeyBes.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "sku_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String skuCode;

    @Column(nullable = false, length = 120)
    private String name;

    @Column()
    private String description;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stockQuantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkuProduct that)) return false;
        return Objects.equals(skuCode, that.skuCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuCode);
    }

    @Override
    public String toString() {
        return "ProductSku{" +
                "id=" + id +
                ", skuCode='" + skuCode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}

