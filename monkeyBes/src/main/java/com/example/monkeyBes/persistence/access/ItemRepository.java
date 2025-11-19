package com.example.monkeyBes.persistence.access;

import com.example.monkeyBes.persistence.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
     //Busca os produtos mais vendidos
    @Query("SELECT i.product AS product, SUM(i.quantity) AS totalSold " +
            "FROM Item i " +
            "GROUP BY i.product " +
            "ORDER BY totalSold DESC")
    List<Object[]> findMostSoldProducts();

    //Busca os menos vendidos
    @Query("SELECT i.product AS product, SUM(i.quantity) AS totalSold " +
            "FROM Item i " +
            "GROUP BY i.product " +
            "ORDER BY totalSold ASC")
    List<Object[]> findLeastSoldProducts();

}
