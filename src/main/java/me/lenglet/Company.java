package me.lenglet;

import java.time.LocalDate;

record Company(
        String name,
        LocalDate dateOfFoundation
) implements LegalEntity {
}
