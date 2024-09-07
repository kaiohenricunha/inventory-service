package com.kcunha.inventory_service;

import com.kcunha.inventory_service.model.Inventory;
import com.kcunha.inventory_service.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InventoryServiceTests {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testInventoryCreationAndUpdate() {
        // Test inventory creation
        Inventory inventory = inventoryService.updateInventory(1L, 100);
        assertThat(inventory.getStock()).isEqualTo(100);

        // Test stock update
        Inventory updatedInventory = inventoryService.updateInventory(1L, 80);
        assertThat(updatedInventory.getStock()).isEqualTo(80);
    }

    @Test
    public void testDecreaseStock() {
        inventoryService.updateInventory(1L, 50);
        Inventory inventory = inventoryService.decreaseStock(1L, 10);
        assertThat(inventory.getStock()).isEqualTo(40);
    }

    @Test
    public void testRestApi() {
        ResponseEntity<Inventory> response = restTemplate.getForEntity("/api/inventory/1", Inventory.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }
}
