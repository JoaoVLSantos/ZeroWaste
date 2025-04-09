package com.zerowaste.zerowaste.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zerowaste.models.broadcast.BroadcastEmail;
import com.zerowaste.models.broadcast.BroadcastList;

class BroadcastEmailTest {
     
    @Test
    void getIdTest() {
        BroadcastEmail p = new BroadcastEmail();
        p.setId(1L);

        assertEquals(1L, p.getId());
    }

    @Test
    void setAndGetCreatedAtTest() {
        BroadcastEmail email = new BroadcastEmail();
        email.setCreatedAt(LocalDate.now());

        assertEquals(LocalDate.now(), email.getCreatedAt());
    }

    @Test
    void setAndGetUpdatedAtTest() {
        BroadcastEmail email = new BroadcastEmail();
        email.setUpdatedAt(LocalDate.now());

        assertEquals(LocalDate.now(), email.getUpdatedAt());
    }

    @Test
    void setAndGetDeletedAtTest() {
        BroadcastEmail email = new BroadcastEmail();
        email.setDeletedAt(LocalDate.now());

        assertEquals(LocalDate.now(), email.getDeletedAt());
    }

    @Test
    void setAndGetBroadcastListsTest() {
        BroadcastEmail email = new BroadcastEmail();
        BroadcastList list1 = new BroadcastList();
        BroadcastList list2 = new BroadcastList();
        List<BroadcastList> lists = List.of(list1, list2);

        email.setBroadcastLists(lists);

        assertEquals(lists, email.getBroadcastLists());
        assertEquals(2, email.getBroadcastLists().size());
    }

    @Test
    void equalsTest1() {
        BroadcastEmail p = new BroadcastEmail();
        p.setId(1L);

        assertEquals(p, p);
    }

    @Test
    void equalsTest2() {
        BroadcastEmail p = new BroadcastEmail();
        p.setId(1L);

        assertNotEquals(null, p);
    }

    @Test
    void equalsTest3() {
        BroadcastEmail p = new BroadcastEmail();
        p.setId(1L);

        assertNotEquals("uma string qualquer", p);
    }

    @Test
    void equalsTest4() {
        BroadcastEmail p1 = new BroadcastEmail();
        p1.setId(1L);

        BroadcastEmail p2 = new BroadcastEmail();
        p2.setId(1L);

        assertEquals(p1, p2);
    }

    @Test
    void equalsTest5() {
        BroadcastEmail p1 = new BroadcastEmail();
        p1.setId(1L);

        BroadcastEmail p2 = new BroadcastEmail();
        p2.setId(2L);

        assertNotEquals(p1, p2);
    }

    @Test
    void equalsTest6() {
        BroadcastEmail p1 = new BroadcastEmail();
        BroadcastEmail p2 = new BroadcastEmail();

        p1.setId(null);
        p2.setId(null);

        assertEquals(p1, p2);
    }

    @Test
    void equalsTest7() {
        BroadcastEmail p1 = new BroadcastEmail();
        p1.setId(null);

        BroadcastEmail p2 = new BroadcastEmail();
        p2.setId(1L);

        assertNotEquals(p1, p2);
        assertNotEquals(p2, p1);
    }

    @Test
    void hashCodeTest1() {
        BroadcastEmail p1 = new BroadcastEmail();
        p1.setId(10L);

        BroadcastEmail p2 = new BroadcastEmail();
        p2.setId(10L);

        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void hashCodeTest2() {
        BroadcastEmail p1 = new BroadcastEmail();
        p1.setId(1L);

        BroadcastEmail p2 = new BroadcastEmail();
        p2.setId(2L);

        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void hashCodeTest3() {
        BroadcastEmail p1 = new BroadcastEmail();
        p1.setId(null);

        assertDoesNotThrow(() -> p1.hashCode());
    }

    @Test
    void hashCodeTest4() {
        BroadcastEmail p = new BroadcastEmail();
        p.setId(100L);

        int hash1 = p.hashCode();
        int hash2 = p.hashCode();
        int hash3 = p.hashCode();

        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }
}
