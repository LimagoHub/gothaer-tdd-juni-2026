package de.gothaer.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtLeast;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyServiceUsingDependencyTest {

    @Mock
    private Dependeny dependenyMock ;
    @InjectMocks
    private MyServiceUsingDependency objectUnderTest;



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
        verify(dependenyMock, atLeast(1)).foobar("Hallo Welt");
    }

    @Test
    void vierTest() {

        lenient().when(dependenyMock.foobar("Hallo")).thenReturn(5);
        lenient().when(dependenyMock.foobar(anyString())).thenReturn(7);
        lenient().when(dependenyMock.foobar("Hello")).thenReturn(3);

        System.out.println(dependenyMock.foobar("Hello"));
        System.out.println(dependenyMock.foobar("Hallo"));
    }
}