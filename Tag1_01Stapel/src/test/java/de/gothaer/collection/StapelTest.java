package de.gothaer.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StapelTest {

    private Stapel objectUnderTest;

    @BeforeEach
    void init(){
        objectUnderTest=new Stapel();
    }

    @Test
    void isEmpty_EmptyStack_returnsTrue()  {
        // Arrange


        // Action
        var result =  objectUnderTest.isEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    void isEmpty_NotEmptyStack_returnsFalse()  throws StapelException {
        // Arrange

        objectUnderTest.push(1);

        // Action


        // Assert
        assertFalse( objectUnderTest.isEmpty());
    }

    @Test
    void push_fillUpToLimit_noExceptionIsThrown() throws StapelException {
        for (int i = 0; i < 10; i++) {
            assertDoesNotThrow(()->objectUnderTest.push(1));
        }
    }

    @Test
    void push_Overflow_thowsStapelException() throws StapelException {
        // Arrange
        for (int i = 0; i < 10; i++) {
            assertDoesNotThrow(()->objectUnderTest.push(1));
        }

        // Act + Assert
        StapelException ex = assertThrows(StapelException.class,()->objectUnderTest.push(1));
        assertEquals("Overflow", ex.getMessage());
    }

}