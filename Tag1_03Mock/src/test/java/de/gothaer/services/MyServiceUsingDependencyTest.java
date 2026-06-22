package de.gothaer.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtLeast;
import org.mockito.internal.verification.Times;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyServiceUsingDependencyTest {

    private Dependeny dependenyMock ;
    private MyServiceUsingDependency objectUnderTest;

    @BeforeEach
    void setUp() {
        dependenyMock = Mockito.mock(Dependeny.class);
        objectUnderTest = new MyServiceUsingDependency(dependenyMock);

    }

    @Test
    void einsTest() {
        objectUnderTest.eins("Hallo Welt");
        verify(dependenyMock, atLeast(1)).foo("HALLO WELT");
    }

    @Test
    void zweiTest () {

        // Recordmode
        when(dependenyMock.bar())
                .thenReturn(3)
                .thenReturn(5)
                .thenReturn(7)
                .thenThrow(new RuntimeException("Upps"));
        // Replaymode

        var result = objectUnderTest.zwei();

        assertEquals(15, result);
        verify(dependenyMock, times(3)).bar();

    }

    @Test
    void dreiTest() {
        when(dependenyMock.foobar(anyString())).thenReturn(3);
        var result = objectUnderTest.drei();
        assertEquals(9, result);
        verify(dependenyMock).foobar("Hallo Welt");
    }

    @Test
    void vierTest() {

        when(dependenyMock.foobar("Hallo")).thenReturn(5);
        when(dependenyMock.foobar(anyString())).thenReturn(7);
        when(dependenyMock.foobar("Hello")).thenReturn(3);

        System.out.println(dependenyMock.foobar("Franz"));
    }
}