package de.gothaer.service.impl;

import de.gothaer.persistence.Person;
import de.gothaer.persistence.PersonenRepository;
import de.gothaer.service.BlacklistService;
import de.gothaer.service.PersonenServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class PersonenServiceImplTest {
    @Mock
    private PersonenRepository repositoryMock;

    @Mock
    private BlacklistService blacklistServiceMock;

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Test
    void speichern_ParameterNull_throwsPersonenServiceException() throws PersonenServiceException {
        //when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenReturn(false);
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein", ex.getMessage());
    }
    @Test
    void speichern_VornameNull_throwsPersonenServiceException() throws PersonenServiceException {

        Person invalidPerson = Person.builder().id(UUID.randomUUID()).vorname(null).nachname("Doe").build();

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
        assertEquals("Vorname zu kurz", ex.getMessage());
    }
    @Test
    void speichern_VornameZuKurz_throwsPersonenServiceException() throws PersonenServiceException {

        Person invalidPerson = Person.builder().id(UUID.randomUUID()).vorname("J").nachname("Doe").build();

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
        assertEquals("Vorname zu kurz", ex.getMessage());
    }
    @Test
    void speichern_NachnameNull_throwsPersonenServiceException() throws PersonenServiceException {

        Person invalidPerson = Person.builder().id(UUID.randomUUID()).vorname("John").nachname(null).build();

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
        assertEquals("Nachname zu kurz", ex.getMessage());
    }
    @Test
    void speichern_NachnameZuKurz_throwsPersonenServiceException() throws PersonenServiceException {

        Person invalidPerson = Person.builder().id(UUID.randomUUID()).vorname("John").nachname("D").build();

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
        assertEquals("Nachname zu kurz", ex.getMessage());
    }

    @Test
    void speichern_Antipath_throwsPersonenServiceException() throws PersonenServiceException {

        Person unerwuenschtePerson = Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();

        when(blacklistServiceMock.isBlacklisted(unerwuenschtePerson)).thenReturn(true);
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(unerwuenschtePerson));
        assertEquals("Antipath", ex.getMessage());
        verify(blacklistServiceMock).isBlacklisted(unerwuenschtePerson);
    }

    @Test
    void speichern_UnexpectedExceptionInUnderlyingService_throwsPersonenServiceException() throws PersonenServiceException {

        Person validPerson = Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();

        doThrow(new ArithmeticException("Upps")).when(repositoryMock).save(any(Person.class));

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(validPerson));
        assertEquals("Fehler beim Speichern", ex.getMessage());
        assertEquals(ArithmeticException.class, ex.getCause().getClass());
    }

    @Test
    void speichern_happyDay_personPassedToRepoAndNoExceptionIsThrown() throws PersonenServiceException {
        Person validPerson = Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();
        objectUnderTest.speichern(validPerson);
        verify(repositoryMock, times(1)).save(validPerson);
    }

    @Test
    void speichern_ExceptionInBlacklistServce_throwsPersonenServiceException() throws PersonenServiceException {
        Person validPerson = Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();
        when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenThrow(ArithmeticException.class);
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(validPerson));
        assertEquals("Fehler beim Speichern", ex.getMessage());
        assertEquals(ArithmeticException.class, ex.getCause().getClass());
    }
}