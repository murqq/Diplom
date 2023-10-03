import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.StartPage;

import static data.SQLHelper.clearDB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PurchaseTravelingDayTest {

    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:8080");
        clearDB();

    }

    // Positive

    @Test
    @Order(1)
    @DisplayName("All fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveAllValidFieldRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationGood();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    @DisplayName("All fields are filled with valid data(CreditPage, RUS)")
    void creditFormShouldHaveAllValidFieldRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidWorkCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderRus());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationGood();
        String actual = SQLHelper.getCreditStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    @DisplayName("All fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveAllValidFieldENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationGood();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    @DisplayName("All fields are filled with valid data(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldENG() {
        StartPage start = new StartPage();
        start.startPage();
        var CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidWorkCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderENG());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationGood();
        String actual = SQLHelper.getCreditStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test // bug
    @Order(5)
    @DisplayName("All fields are filled with valid data and blocked card(PaymentPage, RUS)")
    void paymentFormShouldHaveAllValidFieldBlockedCardRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidBlockedCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    @Test // bug
    @Order(6)
    @DisplayName("All fields are filled with valid data and blocked card(CreditPage, RUS)")
    void creditFormShouldHaveAllValidFieldBlockedCardRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidBlockedCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderRus());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    @Test // bug
    @Order(7)
    @DisplayName("All fields are filled with valid data and blocked card(PaymentPage, ENG)")
    void paymentFormShouldHaveAllValidFieldBlockedCardENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidBlockedCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    @Test // bug
    @Order(8)
    @DisplayName("All fields are filled with valid data and blocked card(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldBlockedCardENG() {
        StartPage start = new StartPage();
        start.startPage();
        var CreditPage = start.buyInCredit();

        CreditPage.setCardNumber(DataHelper.getValidBlockedCard());
        CreditPage.setMonth(DataHelper.getValidMonth());
        CreditPage.setYear(DataHelper.getValidYear());
        CreditPage.setHolder(DataHelper.getValidHolderENG());
        CreditPage.setCVC(DataHelper.getValidCVCCode());
        CreditPage.buttonContinueClick();
        CreditPage.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }


    // Negative

    @Test
    @Order(9)
    @DisplayName("All fields are empty(PaymentPage)")
    void paymentFormShouldHaveAllEmptyField() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(10)
    @DisplayName("All fields are empty(CreditPage)")
    void CreditFormShouldHaveAllEmptyField() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(11)
    @DisplayName("Number card empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyNumberCardRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();


        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(12)
    @DisplayName("Number card empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyNumberCardRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(13)
    @DisplayName("Month empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyMonthRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();


        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(14)
    @DisplayName("Month empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyMonthRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(15)
    @DisplayName("Year empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyYearRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(16)
    @DisplayName("Year empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyYearRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(17)
    @DisplayName("Holder empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyHolderRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(18)
    @DisplayName("Holder empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyHolderRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }


    @Test // bug
    @Order(19)
    @DisplayName("CVC empty,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveEmptyCVCRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(20)
    @DisplayName("CVC empty,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveEmptyCVCRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(21)
    @DisplayName("Non-existent card,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveNonExistentCardRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getInvalidFullCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(22)
    @DisplayName("Non-existent card,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveNonExistentCardRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getInvalidFullCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(23)
    @DisplayName("Non-existent short card,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveNonExistentShortCardRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getInvalidShortCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(24)
    @DisplayName("Non-existent Short card,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveNonExistentShortCardRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getInvalidShortCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(25)
    @DisplayName("Month above range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveMonthAboveRangeRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidMonthAboveYear());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(26)
    @DisplayName("Month above range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveMonthAboveRangeRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidMonthAboveYear());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(27)
    @DisplayName("Month below range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveMonthBelowRangeRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidMonthZero());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(28)
    @DisplayName("Month below range ,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveMonthBelowRangeRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidMonthZero());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(29)
    @DisplayName("Previous month,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHavePreviousMonthRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getInvalidPreviousMonth());
        paymentPage.setYear(DataHelper.getCurrentYear());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(30)
    @DisplayName("Previous month,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHavePreviousMonthRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getInvalidPreviousMonth());
        creditPage.setYear(DataHelper.getCurrentYear());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(31)
    @DisplayName("Year above range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveYearAboveRangeRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getInvalidYearAboveRange());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(32)
    @DisplayName("Year above range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveYearAboveRangeRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getInvalidYearAboveRange());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(33)
    @DisplayName("Year below range,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveYearBelowRangeRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getInvalidYearBelowRange());
        paymentPage.setHolder(DataHelper.getValidHolderENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(34)
    @DisplayName("Year below range,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveYearBelowRangeRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getInvalidYearBelowRange());
        creditPage.setHolder(DataHelper.getValidHolderENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorExpiredCard();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(35)
    @DisplayName("Only name in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOnlyNameHolderRestFieldValidRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlyNameRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(36)
    @DisplayName("Only name in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOnlyNameHolderRestFieldValidRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlyNameRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(37)
    @DisplayName("Only name in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOnlyNameHolderRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlyNameENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(38)
    @DisplayName("Only name in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOnlyNameHolderRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlyNameENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(39)
    @DisplayName("Only surname in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOnlySurnameHolderRestFieldValidRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlySurnameRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(40)
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOnlySurnameHolderRestFieldValidRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlySurnameRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(41)
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOnlySurnameHolderRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderOnlySurnameENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(42)
    @DisplayName("Only Surname in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOnlySurnameHolderRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderOnlySurnameENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(43)
    @DisplayName("One letter in holder,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveOneLetterHolderRestFieldValidRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getOneLetterRUS());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(44)
    @DisplayName("One letter in holder,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveOneLetterHolderRestFieldValidRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getOneLetterRUS());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(45)
    @DisplayName("One letter in holder,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveOneLetterHolderRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getOneLetterENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(46)
    @DisplayName("One letter in holder,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveOneLetterHolderRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getOneLetterENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(47)
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveHolderWithHyphenatedRestFieldValidRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedRus());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(48)
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(CreditPage, RUS)")
    void CreditFormShouldHaveHolderWithHyphenatedRestFieldValidRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedRus());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(49)
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveHolderWithHyphenatedRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedENG());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(50)
    @DisplayName("Holder with hyphenated,rest fields are filled with valid data(CreditPage, ENG)")
    void CreditFormShouldHaveHolderWithHyphenatedRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderWithHyphenatedENG());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorHolderNotFull();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }


    @Test // bug
    @Order(51)
    @DisplayName("Holder with hieroglyphs ,rest fields are filled with valid data(PaymentPage, CN)")
    void paymentFormShouldHaveHolderWithHieroglyphsRestFieldValidCN() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getInvalidHolderCN());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(52)
    @DisplayName("Holder with hieroglyphs,rest fields are filled with valid data(CreditPage, CN)")
    void CreditFormShouldHaveHolderWithHieroglyphsRestFieldValidCN() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getInvalidHolderCN());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(53)
    @DisplayName("Special symbol in holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveHolderWithSpecialSymbolRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getRandomSymbol());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(54)
    @DisplayName("Special symbol in holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveHolderWithSpecialSymbolRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getRandomSymbol());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(55)
    @DisplayName("Number in holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveHolderWithNumberRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getRandomNumber());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(56)
    @DisplayName("Number in holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveHolderWithNumberRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getRandomNumber());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorInvalidCharacters();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(57)
    @DisplayName("Over flow holder,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveOverFlowHolderRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getHolderOverFlowString());
        paymentPage.setCVC(DataHelper.getValidCVCCode());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationErrorOverFlow();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test // bug
    @Order(58)
    @DisplayName("Over Flow holder,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveOverFlowHolderRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getHolderOverFlowString());
        creditPage.setCVC(DataHelper.getValidCVCCode());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationErrorOverFlow();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(59)
    @DisplayName("One number in CVC,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveOneNumberInCVCRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getInvalidCVCCodeOneNumber());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(60)
    @DisplayName("One number in CVC,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveOneNumberInCVCRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderRus());
        creditPage.setCVC(DataHelper.getInvalidCVCCodeOneNumber());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(61)
    @DisplayName("Two number in CVC,rest fields are filled with valid data(PaymentPage)")
    void paymentFormShouldHaveTwoNumberInCVCRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentPage = start.buy();

        paymentPage.setCardNumber(DataHelper.getValidWorkCard());
        paymentPage.setMonth(DataHelper.getValidMonth());
        paymentPage.setYear(DataHelper.getValidYear());
        paymentPage.setHolder(DataHelper.getValidHolderRus());
        paymentPage.setCVC(DataHelper.getInvalidCVCCodeTwoNumber());
        paymentPage.buttonContinueClick();
        paymentPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertNull(actual);
    }

    @Test
    @Order(62)
    @DisplayName("Two number in CVC,rest fields are filled with valid data(CreditPage)")
    void CreditFormShouldHaveTwoNumberInCVCRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditPage = start.buyInCredit();

        creditPage.setCardNumber(DataHelper.getValidWorkCard());
        creditPage.setMonth(DataHelper.getValidMonth());
        creditPage.setYear(DataHelper.getValidYear());
        creditPage.setHolder(DataHelper.getValidHolderRus());
        creditPage.setCVC(DataHelper.getInvalidCVCCodeTwoNumber());
        creditPage.buttonContinueClick();
        creditPage.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

}
