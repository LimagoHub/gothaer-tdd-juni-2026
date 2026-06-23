package de.gothaer.service.impl;

import de.gothaer.persistence.Person;
import de.gothaer.persistence.PersonenRepository;
import de.gothaer.service.BlacklistService;
import de.gothaer.service.PersonenService;
import de.gothaer.service.PersonenServiceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repository;
    private final BlacklistService blacklistService;

    /*
        parameter == null -> PSE Ok
        Vorname null oder Vorname.lenght < 1 -> PSE
        Nachname dito

        Vorname == Attila -> PSE

        Fehler im datenbankservice -> PSE

        happy day -> Person an repo
     */

    @Override
    public void speichern(Person person) throws PersonenServiceException {
        try {
            speichernImpl(person);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Speichern",e);
        }
    }

    private void speichernImpl(final Person person) throws PersonenServiceException {
        checkPerson(person);
        repository.save(person);
    }

    private void checkPerson(final Person person) throws PersonenServiceException {
        validatePerson(person);
        businesscheck(person);
    }

    private static void businesscheck(final Person person) throws PersonenServiceException {
        if("Attila".equalsIgnoreCase(person.getVorname()))
            throw new PersonenServiceException("Antipath");
    }

    private static void validatePerson(final Person person) throws PersonenServiceException {
        if(person == null)
            throw new PersonenServiceException("Person darf nicht null sein");
        if(person.getVorname() == null|| person.getVorname().length()<2)
            throw new PersonenServiceException("Vorname zu kurz");
        if(person.getNachname() == null|| person.getNachname().length()<2)
            throw new PersonenServiceException("Nachname zu kurz");
    }
}
