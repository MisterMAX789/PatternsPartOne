import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        return faker.address().cityName();
    }

    public static String generateName() {
        return faker.name().fullName();
    }

    public static String generatePhone() {
        return faker.number().digits(11);
    }

    public static UserData generateUserData (String locale) {
        return new UserData (generateCity(), generateName(), generatePhone());
    }
}

