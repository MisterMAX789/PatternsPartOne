import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataManager {
    private DataManager() {
    }

    public static String generateDate(int day) {
        return LocalDate.now().plusDays(day)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}