package com.zerowaste.zerowaste.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.zerowaste.models.product.Product;

class ProductTest {
    
    @Test
    void getPromotionsTest(){
        Product product = new Product();
        assertNull(product.getPromotions());
    }

    @Test
    void setPromotionsTest1() {
        Product product = new Product();
        
        assertThrows(IllegalArgumentException.class, () -> {
            product.setPromotions(null);
        });
    }

    @Test
    void setPromotionsTest2() {
        Product product = new Product();
        
        assertDoesNotThrow(() -> {
            product.setPromotions(Set.of());
        });
    }

    @Test
    void equalsTest1() {
        Product product = new Product();
        product.setId(1L);

        assertEquals(product, product);
    }

    @Test
    void equalsTest2() {
        Product product = new Product();
        product.setId(1L);

        assertNotEquals(null, product);
    }

    @Test
    void equalsTest3() {
        Product product = new Product();
        product.setId(1L);

        assertNotEquals("some string", product);
    }

    @Test
    void equalsTest4() {
        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(1L);

        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void equalsTest5() {
        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        assertNotEquals(product1, product2);
    }

    @Test
    void equalsTest6() {
        Product product1 = new Product();
        product1.setId(null);

        Product product2 = new Product();
        product2.setId(null);

        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void equalsTest7() {
        Product product1 = new Product();
        product1.setId(null);

        Product product2 = new Product();
        product2.setId(1L);

        assertNotEquals(product1, product2);
        assertNotEquals(product2, product1);
    }

    @Test
    void hashCodeTest1() {
        Product p1 = new Product();
        p1.setId(10L);

        Product p2 = new Product();
        p2.setId(10L);

        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void hashCodeTest2() {
        Product p1 = new Product();
        p1.setId(1L);

        Product p2 = new Product();
        p2.setId(2L);

        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void hashCodeTest3() {
        Product p1 = new Product();
        p1.setId(null);

        assertDoesNotThrow(() -> p1.hashCode());
    }

    @Test
    void hashCodeTest4() {
        Product p = new Product();
        p.setId(100L);

        int hash1 = p.hashCode();
        int hash2 = p.hashCode();
        int hash3 = p.hashCode();

        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }
}
