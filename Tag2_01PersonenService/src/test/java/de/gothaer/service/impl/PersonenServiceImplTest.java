package de.gothaer.service.impl;

import de.gothaer.persistence.Person;
import de.gothaer.persistence.PersonenRepository;
import de.gothaer.service.PersonenService;
import de.gothaer.service.PersonenServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {
    @Mock
    private PersonenRepository repositoryMock;

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Test
    void speichern_ParameterNull_throwsPersonenServiceException() throws PersonenServiceException {
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein", ex.getMessage());
    }
    @Test
    void speichern_VornameNull_throwsPersonenServiceException() throws PersonenServiceException {

        Person invalidPerson = Person.builder().id(UUID.randomUUID()).vorname(null).nachname("Doe").build();

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
        assertEquals("Vorname zu kurz", ex.getMessage());
    }
}