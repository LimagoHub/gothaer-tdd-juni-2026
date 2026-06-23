package de.gothaer.service;

import de.gothaer.persistence.Person;

public interface BlacklistService {

    boolean isBlacklistes(final Person possibleBlacklistedPerson);
}
