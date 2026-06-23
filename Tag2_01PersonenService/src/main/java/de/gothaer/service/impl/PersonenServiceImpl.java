package de.gothaer.service.impl;

import de.gothaer.persistence.Person;
import de.gothaer.persistence.PersonenRepository;
import de.gothaer.service.PersonenService;
import de.gothaer.service.PersonenServiceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repository;

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
        if(person == null)
            throw new PersonenServiceException("Person darf nicht null sein");
        throw new PersonenServiceException("Vorname zu kurz");
    }
}
