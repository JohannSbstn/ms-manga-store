package com.spring.boot.project.ms.manga.store.infrastructure.output.jpa.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserEntityTest {

    @Test
    @DisplayName("Should create UserEntity instance with default values")
    void shouldCreateUserEntityWithDefaultValues() {
        // Given & When
        UserEntity user = new UserEntity();

        // Then
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getDni());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getName());
        assertNull(user.getLastname());
        assertNull(user.getPhone());
        assertNull(user.getAddress());
        assertFalse(user.isActive()); // CHANGED: boolean defaults to false
        assertNull(user.getCreatedAt());
        assertNull(user.getUpdatedAt());
    }

    @Test
    @DisplayName("Should set and get all fields correctly")
    void shouldSetAndGetAllFieldsCorrectly() {
        // Given
        UserEntity user = new UserEntity();
        LocalDateTime now = LocalDateTime.now();

        // When
        user.setId(1L);
        user.setDni("12345678A");
        user.setEmail("user@test.com");
        user.setPassword("password123");
        user.setName("John");
        user.setLastname("Doe");
        user.setPhone("+123456789");
        user.setAddress("Main Street 123");
        user.setActive(true); // CHANGED: Setting to true explicitly
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        // Then
        assertEquals(1L, user.getId());
        assertEquals("12345678A", user.getDni());
        assertEquals("user@test.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getLastname());
        assertEquals("+123456789", user.getPhone());
        assertEquals("Main Street 123", user.getAddress());
        assertTrue(user.isActive()); // CHANGED: Now expecting true
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    @DisplayName("Should return true for equals with same instance")
    void shouldReturnTrueForEqualsWithSameInstance() {
        // Given
        UserEntity user = new UserEntity();
        user.setId(1L);

        // When & Then
        assertEquals(user, user);
    }

    @Test
    @DisplayName("Should return false for equals with null")
    void shouldReturnFalseForEqualsWithNull() {
        // Given
        UserEntity user = new UserEntity();
        user.setId(1L);

        // When & Then
        assertNotEquals(null, user);
    }

    @Test
    @DisplayName("Should return true for equals with identical objects")
    void shouldReturnTrueForEqualsWithIdenticalObjects() {
        // Given - CHANGED: Both objects have exactly the same values
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setEmail("test@test.com");
        user1.setDni("12345678A");
        user1.setActive(false);

        UserEntity user2 = new UserEntity();
        user2.setId(1L);
        user2.setEmail("test@test.com");
        user2.setDni("12345678A");
        user2.setActive(false);

        // When & Then
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    @DisplayName("Should return false for equals with different objects")
    void shouldReturnFalseForEqualsWithDifferentObjects() {
        // Given - CHANGED: Testing with different field values
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setEmail("test1@test.com");

        UserEntity user2 = new UserEntity();
        user2.setId(2L); // Different ID
        user2.setEmail("test2@test.com"); // Different email

        // When & Then
        assertNotEquals(user1, user2);
    }

    @Test
    @DisplayName("Should return false for equals when only ID is same but other fields differ")
    void shouldReturnFalseForEqualsWhenOnlyIdIsSame() {
        // Given - CHANGED: This test now correctly expects false
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setEmail("test1@test.com");
        user1.setDni("11111111A");

        UserEntity user2 = new UserEntity();
        user2.setId(1L); // Same ID
        user2.setEmail("test2@test.com"); // Different email
        user2.setDni("22222222B"); // Different DNI

        // When & Then - CHANGED: Now expecting false because Lombok uses all fields
        assertNotEquals(user1, user2);
    }

    @Test
    @DisplayName("Should return false for equals with different class")
    void shouldReturnFalseForEqualsWithDifferentClass() {
        // Given
        UserEntity user = new UserEntity();
        user.setId(1L);

        Object differentObject = "Not a UserEntity";

        // When & Then
        assertNotEquals(user, differentObject);
    }

    @Test
    @DisplayName("Should have same hashCode for identical objects")
    void shouldHaveSameHashCodeForIdenticalObjects() {
        // Given
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setEmail("test@test.com");
        user1.setName("John");

        UserEntity user2 = new UserEntity();
        user2.setId(1L);
        user2.setEmail("test@test.com");
        user2.setName("John");

        // When & Then
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    @DisplayName("Should have different hashCode for different objects")
    void shouldHaveDifferentHashCodeForDifferentObjects() {
        // Given
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setEmail("test1@test.com");

        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setEmail("test2@test.com");

        // When & Then
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    @DisplayName("Should generate toString correctly")
    void shouldGenerateToStringCorrectly() {
        // Given
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@test.com");
        user.setDni("12345678A");

        // When
        String toString = user.toString();

        // Then
        assertThat(toString).contains("UserEntity");
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("name=John");
        assertThat(toString).contains("email=john@test.com");
        assertThat(toString).contains("dni=12345678A");
    }

    @Test
    @DisplayName("Should handle isActive field correctly")
    void shouldHandleIsActiveFieldCorrectly() {
        // Given
        UserEntity user = new UserEntity();

        // When & Then - Test both states
        assertFalse(user.isActive()); // Default should be false

        user.setActive(true);
        assertTrue(user.isActive());

        user.setActive(false);
        assertFalse(user.isActive());
    }

    @Test
    @DisplayName("Should handle null fields correctly")
    void shouldHandleNullFieldsCorrectly() {
        // Given
        UserEntity user = new UserEntity();

        // When - Setting some fields as null
        user.setId(null);
        user.setDni(null);
        user.setEmail(null);
        user.setName(null);
        user.setLastname(null);

        // Then
        assertNull(user.getId());
        assertNull(user.getDni());
        assertNull(user.getEmail());
        assertNull(user.getName());
        assertNull(user.getLastname());
    }
}