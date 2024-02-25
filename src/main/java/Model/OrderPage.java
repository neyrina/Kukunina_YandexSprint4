package Model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    //Локатор страницы
    public static final By ORDER_FORM = By.xpath(".//div[text()='Для кого самокат']");
    //Имя
    public static final By NAME = By.xpath(".//input[@placeholder='* Имя']");
    //Фамилия
    public static final By SURNAME = By.xpath(".//input[@placeholder='* Фамилия']");
    //Адрес
    public static final By ADDRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Станция
    public static final By STATION = By.xpath(".//input[@placeholder='* Станция метро']");
    // локатор выпадающего списка метро на странице заказа
    public static final By METRO_FROM_LIST = By.xpath("//*[@class='select-search__select']");
    //Телефон
    public static final By PHONE_NUMBER = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    public static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");
    // Когда привезти самокат
    private final By orderTime = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //выбор даты в выпадающем календаре на странице заказа
    private final By orderTimeFull = By.xpath("//div[@class='react-datepicker__day react-datepicker__day--016 react-datepicker__day--selected']");
    // Срок аренды
    private final By orderRentalPeriod = By.xpath("//div[@class='Dropdown-placeholder']");
    //Срок аренды - двое суток
    private final By orderRentalTwoDay = By.xpath("//div[@class='Dropdown-option'] [2]");
    //Срок аренды - четверо суток
    private final By orderRentalFourDay = By.xpath("//div[@class='Dropdown-option'] [4]");
    // цвет - Серая безысходность
    private final By checkBoxGreyColorScooter = By.xpath("//input[@id='grey']");
    // цвет - Черный жемчуг
    private final By checkBoxBlackColorScooter = By.xpath("//input[@id='black']");
    // кнопка Заказать
    private final By orderButtonOnOrderPage = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // локатор кнопка Да
    private final By orderConfirmationButton = By.xpath("//button[text()='Да']");
    // локатор подтверждения заказа
    private final By orderConfirmation = By.xpath("//div[text()='Заказ оформлен']");
    //Комментарий для курьера
    private final By orderCommentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadOrderPage() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(ORDER_FORM).getText() != null
                && !driver.findElement(ORDER_FORM).getText().isEmpty()
        ));
    }

    public void inputPersonalInformation(String name, String surname, String address,
                                         String station, String phoneNumber) { //вводим данные зааказчика
        driver.findElement(NAME).sendKeys(name);
        driver.findElement(SURNAME).sendKeys(surname);
        driver.findElement(ADDRESS).sendKeys(address);
        driver.findElement(STATION).sendKeys(station);
        driver.findElement(METRO_FROM_LIST).click();
        driver.findElement(PHONE_NUMBER).sendKeys(phoneNumber);
        pressNext();
    }

    public void inputAdditionalInformation(String date, String comment) { //вводим доп инфу
        driver.findElement(orderTime).sendKeys(date, Keys.ENTER);
        driver.findElement(orderRentalPeriod).click();
        driver.findElement(orderRentalTwoDay).click();
        driver.findElement(checkBoxGreyColorScooter).click();
        driver.findElement(orderCommentField).sendKeys(comment);
        driver.findElement(orderButtonOnOrderPage).click();//нажимаем Заказать
        driver.findElement(orderConfirmationButton).click();// в хроме при нажатии на эту кнопку ничего не происходит

    }


    public boolean checkOrderConfirmationIsDisplayed() {
        return driver.findElement(orderConfirmation).isDisplayed();
    }


    public void pressNext() {
        driver.findElement(NEXT_BUTTON).click();
    }


}
