package de.gothaer.persistence;

import java.util.Optional;
import java.util.UUID;

public interface PersonenRepository {

    void save(final Person person);
    void update(final Person person);
    Optional<Person> findById(final UUID id);
    Iterable<Person> findAll();
    boolean delete(final UUID id);
}
