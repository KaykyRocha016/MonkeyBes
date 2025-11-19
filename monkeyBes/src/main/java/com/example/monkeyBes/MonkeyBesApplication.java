package com.example.monkeyBes;

import com.example.monkeyBes.persistence.access.ItemRepository;
import com.example.monkeyBes.persistence.access.SkuProductRepository;
import com.example.monkeyBes.persistence.model.Item;
import com.example.monkeyBes.persistence.model.SkuProduct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MonkeyBesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonkeyBesApplication.class, args);
	}

    @Bean
    CommandLineRunner initDatabase(SkuProductRepository productRepo,  ItemRepository itemRepo) {

        return args -> {
            SkuProduct p1 = SkuProduct.builder()
                    .name("iPhone 15 Pro")
                    .skuCode("APPLE-15P")
                    .price(new BigDecimal("7999.9"))
                    .description("Smartphone Apple")
                    .build();

            SkuProduct p2 = SkuProduct.builder()
                    .name("Notebook Dell XPS")
                    .skuCode("DELL-XPS13")
                    .price(new BigDecimal("11500.0"))
                    .description("Ultrabook potente para dev")
                    .build();

            SkuProduct p3 = SkuProduct.builder()
                    .name("Mouse Logitech MX Master")
                    .skuCode("LOGI-MX3")
                    .price(new BigDecimal("650.0"))
                    .description("Melhor mouse para produtividade")
                    .build();

            SkuProduct p4 = SkuProduct.builder()
                    .name("CABO HDMI 2.0")
                    .skuCode("CAB-HDMI")
                    .price(new BigDecimal("29.9"))
                    .description("Cabo 2 metros 4K")
                    .build();

            productRepo.saveAll(List.of(p1,p2,p3,p4));

            Item item1 = new Item();
            item1.setProduct(p4);
            item1.setQuantity(150);
            item1.setCreatedAt(LocalDateTime.now().minusDays(2));

            Item item2 = new Item();
            item2.setProduct(p3);
            item2.setQuantity(45);
            item2.setCreatedAt(LocalDateTime.now().minusDays(1));

            Item item3 = new Item();
            item3.setProduct(p1);
            item3.setQuantity(10);
            item3.setCreatedAt(LocalDateTime.now());

            Item item4 = new Item();
            item4.setProduct(p2);
            item4.setQuantity(1);
            item4.setCreatedAt(LocalDateTime.now());

            itemRepo.saveAll(List.of(item1, item2, item3, item4));

        };

    }

}
