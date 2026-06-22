package de.gothaer.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class StapelTest {

    private Stapel objectUnderTest;

    @BeforeEach
    public void setUp() {
        objectUnderTest = new Stapel();
    }

    @Test
    void isEmpthy_EmptyStack_returnsTrue() {
        assertTrue(objectUnderTest.isEmpty());
    }

    @Test
    void isEmpthy_NotEmptyStack_returnsFalse() {
        objectUnderTest.push(1);
        assertFalse(objectUnderTest.isEmpty());
    }
}
