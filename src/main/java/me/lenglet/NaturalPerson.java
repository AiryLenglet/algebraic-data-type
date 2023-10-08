package me.lenglet;

import java.time.LocalDate;

record NaturalPerson(
        String firstName,
        String lastName,
        LocalDate dateOfBirth
) implements LegalEntity {
}
