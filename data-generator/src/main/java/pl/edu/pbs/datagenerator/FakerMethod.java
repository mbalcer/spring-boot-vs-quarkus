package pl.edu.pbs.datagenerator;

import com.github.javafaker.Faker;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class FakerMethod {
    private final Faker faker;

    public FakerMethod() {
        this.faker = new Faker(Locale.forLanguageTag("pl"));
    }

    public Map<String, Map<Object, Method>> generateFakerMethodMap() {
        Map<String, Map<Object, Method>> fakerMethodMap = new LinkedHashMap<>();

        try {
            fakerMethodMap.put("ID",
                    Collections.singletonMap(this.faker.number(), this.faker.number().getClass().getDeclaredMethod("randomNumber")));
            fakerMethodMap.put("First name",
                    Collections.singletonMap(this.faker.name(), this.faker.name().getClass().getDeclaredMethod("firstName")));
            fakerMethodMap.put("Last name",
                    Collections.singletonMap(this.faker.name(), this.faker.name().getClass().getDeclaredMethod("lastName")));
            fakerMethodMap.put("Username",
                    Collections.singletonMap(this.faker.name(), this.faker.name().getClass().getDeclaredMethod("username")));
            fakerMethodMap.put("Phone Number",
                    Collections.singletonMap(this.faker.phoneNumber(), this.faker.phoneNumber().getClass().getDeclaredMethod("cellPhone")));
            fakerMethodMap.put("Is active",
                    Collections.singletonMap(this.faker.bool(), this.faker.bool().getClass().getDeclaredMethod("bool")));
            fakerMethodMap.put("Country",
                    Collections.singletonMap(this.faker.address(), this.faker.address().getClass().getDeclaredMethod("countryCode")));
            fakerMethodMap.put("Zip code",
                    Collections.singletonMap(this.faker.address(), this.faker.address().getClass().getDeclaredMethod("zipCode")));
            fakerMethodMap.put("City",
                    Collections.singletonMap(this.faker.address(), this.faker.address().getClass().getDeclaredMethod("city")));
            fakerMethodMap.put("Street",
                    Collections.singletonMap(this.faker.address(), this.faker.address().getClass().getDeclaredMethod("streetName")));
            fakerMethodMap.put("Address number",
                    Collections.singletonMap(this.faker.address(), this.faker.address().getClass().getDeclaredMethod("streetAddressNumber")));
            fakerMethodMap.put("State",
                    Collections.singletonMap(this.faker.address(), this.faker.address().getClass().getDeclaredMethod("state")));
            fakerMethodMap.put("Birthday date",
                    Collections.singletonMap(this.faker.date(), this.faker.date().getClass().getDeclaredMethod("birthday")));
            fakerMethodMap.put("Job title",
                    Collections.singletonMap(this.faker.job(), this.faker.job().getClass().getDeclaredMethod("title")));
            fakerMethodMap.put("Favourite color",
                    Collections.singletonMap(this.faker.color(), this.faker.color().getClass().getDeclaredMethod("name")));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return fakerMethodMap;
    }
}
