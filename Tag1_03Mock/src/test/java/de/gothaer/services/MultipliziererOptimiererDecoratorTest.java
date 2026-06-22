package de.gothaer.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MultipliziererOptimiererDecoratorTest {

    @Mock
    private Multiplizierer multipliziererMock;
    @InjectMocks
    private MultipliziererOptimiererDecorator objectUnderTest;

   /* @BeforeEach
    void setUp() {
        multipliziererMock = Mockito.mock(Multiplizierer.class);
        objectUnderTest = new MultipliziererOptimiererDecorator(multipliziererMock);
    }
    */
    @Test
    void mult_AGreaterThanB_parametersSwapped() {
        objectUnderTest.mult(1000,1);
        verify(multipliziererMock).mult(1,1000);
    }

    @Test
    void mult_LessEqualB_parametersNotSwapped() {
        objectUnderTest.mult(1,1000);
        verify(multipliziererMock).mult(1,1000);
    }
}