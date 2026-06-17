package com.eeshanoor.factory;

import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Map;

/**
 * Central factory for all UI test data.
 * Generates realistic, randomised test data using JavaFaker.
 */
public class TestDataFactory {
    private static final Faker faker = new Faker();

    public static Map<String, String> validCheckoutUser() {
        return new HashMap<>() {{
            put("firstName",  faker.name().firstName());
            put("lastName",   faker.name().lastName());
            put("postalCode", faker.address().zipCode());
        }};
    }

    public static Map<String, String> checkoutUserWithOverrides(
            String firstName, String lastName, String zip) {
        return new HashMap<>() {{
            put("firstName",  firstName  != null ? firstName  : faker.name().firstName());
            put("lastName",   lastName   != null ? lastName   : faker.name().lastName());
            put("postalCode", zip        != null ? zip        : faker.address().zipCode());
        }};
    }
}