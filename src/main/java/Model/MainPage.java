package Model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private static final String IMPORTANT_QUESTIONS = ".//div[text()='Вопросы о важном']";
    private WebDriver driver;
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    // локатор кнопки "Заказать" в заголовке главной страницы
    private By headerOrderButton = By.xpath("//button[@class='Button_Button__ra12g']");

    //локатор кнопки "Заказать" в середине главной страницы
    private By middleOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локаторы вопросов
    private static final String QUESTION1 = "accordion__heading-0";
    private static final String QUESTION2 = "accordion__heading-1";
    private static final String QUESTION3 = "accordion__heading-2";
    private static final String QUESTION4 = "accordion__heading-3";
    private static final String QUESTION5 = "accordion__heading-4";
    private static final String QUESTION6 = "accordion__heading-5";
    private static final String QUESTION7 = "accordion__heading-6";
    private static final String QUESTION8 = "accordion__heading-7";
    //Локаторы ответов
    private static final String ANSWER1 = "accordion__panel-0";
    private static final String ANSWER2 = "accordion__panel-1";
    private static final String ANSWER3 = "accordion__panel-2";
    private static final String ANSWER4 = "accordion__panel-3";
    private static final String ANSWER5 = "accordion__panel-4";
    private static final String ANSWER6 = "accordion__panel-5";
    private static final String ANSWER7 = "accordion__panel-6";
    private static final String ANSWER8 = "accordion__panel-7";

    // локатор кнопки куков "да все привыкли"
    private static final String COOKIES_BUTTON = ".//button[@id='rcc-confirm-button']";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl() {
        driver.get(URL);
    }


    public void scrollToImportantQuestions() {
        WebElement element = driver.findElement(By.xpath(IMPORTANT_QUESTIONS));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }


    public String checkImportantQuestion(String question) {
        scrollToImportantQuestions();
        if (driver.findElement(By.id(QUESTION1)).getText().equals(question)) {
            driver.findElement(By.id(QUESTION1)).click();
            return driver.findElement(By.id(ANSWER1)).getText();
        } else if (driver.findElement(By.id(QUESTION2)).getText().equals(question)) {
            driver.findElement(By.id(QUESTION2)).click();
            return driver.findElement(By.id(ANSWER2)).getText();
        } else if (driver.findElement(By.id(QUESTION3)).getText().equals(question)) {
            driver.findElement(By.id(QUESTION3)).click();
            return driver.findElement(By.id(ANSWER3)).getText();
        } else if (driver.findElement(By.id(QUESTION4)).getText().equals(question)) {
            driver.findElement(By.id(QUESTION4)).click();
            return driver.findElement(By.id(ANSWER4)).getText();
        } else if (driver.findElement(By.id(QUESTION5)).getText().equals(question)) {
            driver.findElement(By.id(QUESTION5)).click();
            return driver.findElement(By.id(ANSWER5)).getText();
        } else if (driver.findElement(By.id(QUESTION6)).getText().equals(question)) {
            driver.findElement(By.id(QUESTION6)).click();
            return driver.findElement(By.id(ANSWER6)).getText();
        } else if (driver.findElement(By.id(QUESTION7)).getText().equals(question)) {
            driver.findElement(By.id(QUESTION7)).click();
            return driver.findElement(By.id(ANSWER7)).getText();
        } else if (driver.findElement(By.id(QUESTION8)).getText().equals(question)) {
            driver.findElement(By.id(QUESTION8)).click();
            return driver.findElement(By.id(ANSWER8)).getText();
        } else {
            throw new IllegalArgumentException("Вопрос не найден: " + question);
        }
    }


    public void clickOrderButton(boolean isOrderButton) {
        if (isOrderButton) {
            driver.findElement(headerOrderButton).click();
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(middleOrderButton));
            driver.findElement(middleOrderButton).click();
        }
    }

    public void clickButtonCookies() {
        driver.findElement(By.xpath(COOKIES_BUTTON)).click();
    }


}

