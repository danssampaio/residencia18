package com.example.atividades.atividade09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemCollectionTest {

    private ItemCollection itemCollection;
    private Item item1;

    @BeforeEach
    void setUp() {
        itemCollection = new ItemCollection();
        item1 = new Item("Item 1");
    }

    @Test
    void addItem_SuccessfullyAddsItemToCollection() {
        itemCollection.addItem(item1);
        
        List<Item> items = itemCollection.getItems();
        
        assertEquals(1, items.size());
        
        assertTrue(items.contains(item1));
    }

    @Test
    void addItem_ThrowsExceptionWhenItemIsNull_ReturnIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            itemCollection.addItem(null);
        });
        
        assertEquals("Item cannot be null", exception.getMessage());
    }

    @Test
    void removeItem_SuccessfullyRemovesItemFromCollection() {
        itemCollection.addItem(item1);
        
        itemCollection.removeItem(item1);
        
        List<Item> items = itemCollection.getItems();
        
        assertFalse(items.contains(item1));
    }

    @Test
    void removeItem_ThrowsExceptionWhenItemNotInCollection_ReturnIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            itemCollection.removeItem(item1);
        });
        
        assertEquals("Item not found in the collection", exception.getMessage());
    }

    @Test
    void getItems_ReturnsAllItemsInCollection() {
        itemCollection.addItem(item1);
        
        List<Item> items = itemCollection.getItems();
        
        assertEquals(1, items.size());
        
        assertTrue(items.contains(item1));
    }

    @Test
    void getItems_ReturnsEmptyListWhenCollectionIsEmpty() {
        List<Item> items = itemCollection.getItems();
        
        assertTrue(items.isEmpty());
    }
}
