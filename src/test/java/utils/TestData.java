package utils;


import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

    Faker faker = new Faker(new Locale("en-US"));

    public String
            name = getRandomName(),
            job = getRandomJob();


    String getRandomName() {
        return faker.name().firstName();
    }

    String getRandomJob() {
        return faker.job().title();
    }
}


