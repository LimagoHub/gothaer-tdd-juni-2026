package de.gothaer.persistence;

import lombok.*;
import lombok.experimental.Tolerate;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @ToString.Exclude
    private UUID id;
    private String vorname;
    private String nachname ;
}
