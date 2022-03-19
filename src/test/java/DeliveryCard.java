import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCard {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
    }

    @Test
    public void shouldCompletedApplication() {
        var daysForMeet = 5;
        String verificationDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").val(DataGenerator.generateCity());
        $("[data-test-id='date'] input").sendKeys(verificationDate);
        $("[data-test-id='name'] input").val(DataGenerator.generateName());
        $("[data-test-id='phone'] input").val(DataGenerator.generatePhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='success-notification']").shouldHave(text("Встреча успешно запланирована на " + verificationDate));
        Duration.ofSeconds(15);
    }

    @Test
    public void shouldChangeDate() {
        var daysForMeeting = 5;
        var firstMeetingDate = DataGenerator.generateDate(daysForMeeting);
        var daysForSecondMeeting = 9;
        var secondMeetingDate = DataGenerator.generateDate(daysForSecondMeeting);
        $("[data-test-id='city'] input").val(DataGenerator.generateCity());
        $("[data-test-id='date'] input").sendKeys(firstMeetingDate);
        $("[data-test-id='name'] input").val(DataGenerator.generateName());
        $("[data-test-id='phone'] input").val(DataGenerator.generatePhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='success-notification']").shouldHave(text("Встреча успешно запланирована на "
                + firstMeetingDate));
        $(".button").click();
        $("[data-test-id='replan-notification']").shouldHave(text("У вас уже запланирована встреча на другую дату." +
                " Перепланировать?"));
        $(byText("Перепланировать")).click();
        $("[data-test-id='success-notification']").shouldHave(text("Встреча успешно запланирована на "
                + secondMeetingDate));
    }
}

