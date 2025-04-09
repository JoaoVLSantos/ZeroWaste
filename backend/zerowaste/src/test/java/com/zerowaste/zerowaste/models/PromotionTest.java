package com.zerowaste.zerowaste.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.zerowaste.models.product.Product;
import com.zerowaste.models.promotion.Promotion;

class PromotionTest {
    
    @Test
    void fullConstructorTest() {
        Long id = 1L;
        Set<Product> products = new HashSet<>();
        products.add(new Product());

        String name = "Promoção de Verão";
        int percentage = 20;
        LocalDate startsAt = LocalDate.now();
        LocalDate endsAt = LocalDate.now().plusDays(3);
        LocalDate createdAt = LocalDate.now();
        LocalDate updatedAt = LocalDate.now();
        LocalDate deletedAt = null;

        Promotion promotion = new Promotion(id, products, name, percentage, startsAt, endsAt, createdAt, updatedAt, deletedAt);

        assertEquals(id, promotion.getId());
        assertEquals(products, promotion.getProducts());
        assertEquals(name, promotion.getName());
        assertEquals(percentage, promotion.getPercentage());
        assertEquals(startsAt, promotion.getStartsAt());
        assertEquals(endsAt, promotion.getEndsAt());
        assertEquals(createdAt, promotion.getCreatedAt());
        assertEquals(updatedAt, promotion.getUpdatedAt());
        assertNull(promotion.getDeletedAt());
    }

    @Test
    void addProductTest() {
        Product product1 = new Product();
        Set<Product> initialProducts = new HashSet<>();
        Promotion promotion = new Promotion(
                1L,
                initialProducts,
                "Promo Teste",
                15,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now(),
                null
        );

        assertTrue(promotion.getProducts().isEmpty());

        promotion.addProduct(product1);

        assertEquals(1, promotion.getProducts().size());
        assertTrue(promotion.getProducts().contains(product1));
    }

    @Test 
    void removeProductTest() {
        Product product1 = new Product();
        Set<Product> initialProducts = new HashSet<>();
        Promotion promotion = new Promotion(
                1L,
                initialProducts,
                "Promo Teste",
                15,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now(),
                null
        );

        promotion.addProduct(product1);
        assertEquals(1, promotion.getProducts().size());

        promotion.removeProduct(product1);
        assertEquals(0, promotion.getProducts().size());
    }

    @Test
    void equalsTest1() {
        Promotion p = new Promotion();
        p.setId(1L);

        assertEquals(p, p);
    }

    @Test
    void equalsTest2() {
        Promotion p = new Promotion();
        p.setId(1L);

        assertNotEquals(null, p);
    }

    @Test
    void equalsTest3() {
        Promotion p = new Promotion();
        p.setId(1L);

        assertNotEquals("uma string qualquer", p);
    }

    @Test
    void equalsTest4() {
        Promotion p1 = new Promotion();
        p1.setId(1L);

        Promotion p2 = new Promotion();
        p2.setId(1L);

        assertEquals(p1, p2);
    }

    @Test
    void equalsTest5() {
        Promotion p1 = new Promotion();
        p1.setId(1L);

        Promotion p2 = new Promotion();
        p2.setId(2L);

        assertNotEquals(p1, p2);
    }

    @Test
    void equalsTest6() {
        Promotion p1 = new Promotion();
        Promotion p2 = new Promotion();

        p1.setId(null);
        p2.setId(null);

        assertEquals(p1, p2);
    }

    @Test
    void equalsTest7() {
        Promotion p1 = new Promotion();
        p1.setId(null);

        Promotion p2 = new Promotion();
        p2.setId(1L);

        assertNotEquals(p1, p2);
        assertNotEquals(p2, p1);
    }
}
