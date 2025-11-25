package com.example.monkeyBes;

import com.example.monkeyBes.persistence.access.ItemRepository;
import com.example.monkeyBes.persistence.access.SkuProductRepository;
import com.example.monkeyBes.service.Implementation.StockMovementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonkeyBesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkeyBesApplication.class, args);
    }

    @Bean
        // O CommandLineRunner foi esvaziado para que o banco de dados inicie sem produtos, itens ou movimentações de estoque.
    CommandLineRunner initDatabase(SkuProductRepository productRepo, ItemRepository itemRepo, StockMovementService movementService) {

        return args -> {
            // Banco de dados iniciando vazio para testes do zero.
        };

    }

}