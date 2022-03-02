import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCard {
    @Test
    void shouldCompletedApplication() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Курск");  //выбор города
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now().plusDays(5) //+5 дней к сегодняшней дате
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // формат даты
        $("[data-test-id=date] input").setValue(verificationDate);
        $("[data-test-id=name] input").setValue("Шилов Николай");
        $("[data-test-id=phone] input").setValue("+79997257198");
        $("[data-test-id=agreement]").click();
        $(".button").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + verificationDate),
                        Duration.ofSeconds(15)); //Условие загрузки не более 15 секунд
    }
}
