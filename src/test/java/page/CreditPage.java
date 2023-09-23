package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private final SelenideElement heading = $$(".heading").findBy(Condition.text("Крудит по данным карты"));
    private final SelenideElement holderField = $(byXpath("/html/body/div[1]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"));
    private final SelenideElement notificationGood = $(".notification_status_ok");
    private final SelenideElement notificationError = $(".notification_status_error");
    SelenideElement form = $(".form");
    private final SelenideElement monthsField = form.$("input[placeholder='08']");
    private final SelenideElement yearsField = form.$("input[placeholder='22']");
    private final SelenideElement cvcField = form.$("input[placeholder='999']");
    private final SelenideElement buttonContinue = form.$$("button").findBy(Condition.text("Продолжить"));
    private final SelenideElement cardNumberField = form.$("input[placeholder='0000 0000 0000 0000']");

    public void PaymentPage() {
        heading.shouldBe(Condition.visible);
    }

    public void setCardNumber(DataHelper.cardNumber number) {
        cardNumberField.setValue(String.valueOf(number));
    }

    public void setMonth(String month) {
        monthsField.setValue(month);
    }

    public void setYear(String year) {
        yearsField.setValue(year);
    }

    public void setHolder(String hold) {
        holderField.setValue(hold);
    }

    public void setCVC(String Cvc) {
        cvcField.setValue(Cvc);
    }

    public void buttonContinueClick() {
        buttonContinue.click();
    }

    public void checkNotificationGood() {
        notificationGood.shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(Condition.exactText("Успешно\n" + "Операция одобрена Банком."));
    }

    public void checkNotificationError() {
        notificationError.shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(Condition.exactText("Ошибка\n" + "Ошибка! Банк отказал в проведении операции."));
    }

    public void checkNotificationInvalidFormat() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Неверный формат"));
    }

    public void checkNotificationRequiredField() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    public void checkNotificationErrorExpirationDate() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    public void checkNotificationErrorExpiredCard() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Истёк срок действия карты"));
    }

    public void checkNotificationErrorHolderNotFull() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Укажите имя и фамилию полностью"));
    }

    public void checkNotificationErrorInvalidCharacters() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Укажите имя и фамилию полностью"));
    }

    public void checkNotificationErrorOverFlow() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Имя и фамилия не должны превышать 20 символов"));
    }

}
