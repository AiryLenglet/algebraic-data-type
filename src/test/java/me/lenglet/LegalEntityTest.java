package me.lenglet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LegalEntityTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeAll() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testNaturalPersonDeserialization() throws JsonProcessingException {
        final var legalEntity = objectMapper.readValue("""
                {
                    "firstName": "Jim",
                    "lastName": "Morrison",
                    "dateOfBirth": "1943-12-08"
                }
                """, LegalEntity.class);

        assertTrue(legalEntity instanceof NaturalPerson);
        final var naturalPerson = (NaturalPerson) legalEntity;
        assertEquals("Jim", naturalPerson.firstName());
        assertEquals("Morrison", naturalPerson.lastName());
        assertEquals(LocalDate.parse("1943-12-08"), naturalPerson.dateOfBirth());
    }

    @Test
    void testCompanyDeserialization() throws JsonProcessingException {
        final var legalEntity = objectMapper.readValue("""
                {
                    "name": "Électricité de France",
                    "dateOfFoundation": "1946-04-08"
                }
                """, LegalEntity.class);

        assertTrue(legalEntity instanceof Company);
        final var company = (Company) legalEntity;
        assertEquals("Électricité de France", company.name());
        assertEquals(LocalDate.parse("1946-04-08"), company.dateOfFoundation());
    }
}
