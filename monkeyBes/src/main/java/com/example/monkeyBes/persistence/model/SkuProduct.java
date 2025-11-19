package com.example.monkeyBes.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "sku_product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String skuCode;

    @Column(nullable = false, length = 120)
    private String name;

    @Column()
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, optional = false)
    private Stock stock;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Item> items;

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
                '}';
    }
}

