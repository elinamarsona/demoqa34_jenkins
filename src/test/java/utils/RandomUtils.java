package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {

    private final Faker faker = new Faker(new Locale("ru"));

    public String firstName = faker.name().firstName(),
                    lastName = faker.name().lastName(),
                    userEmail = faker.internet().emailAddress("example"),
                    userNumber = faker.phoneNumber().subscriberNumber(10),

                    day = String.format("%02d", faker.number().numberBetween(1, 28)),
                    year = String.valueOf(faker.number().numberBetween(1900, 2024)),
                    currentAddress = faker.address().fullAddress(),

                    gender = faker.options().option("Male", "Female", "Other"),
                    month = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"),
                    subject = faker.options().option("Physics", "Chemistry", "Computer Science", "Commerce", "Accounting", "Economics", "Social Studies"),
                    hobbies = faker.options().option("Sports", "Reading", "Music"),
                    uploadImage = "2.jpg",
                    state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
                    city = getCity(state);

     public String getCity(String state) {
         return switch (state) {
             case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
             case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
             case "Haryana" -> faker.options().option("Karnal", "Panipat");
             case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
             default -> "";
         };
    }

}


