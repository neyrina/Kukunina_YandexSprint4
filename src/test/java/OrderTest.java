import Model.MainPage;
import Model.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class OrderTest {
    private static WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phoneNumber;
    private final String date;
    private final String comment;
    private final boolean isOrderButton;

    @Before
    public void browserStart() {
        //createDriver("firefox");
        createDriver("chrome");
    }

    @After
    public void browserQuit() {
        driver.quit();
    }

    public OrderTest(boolean isOrderButton, String name, String surname, String address, String station, String phoneNumber, String date, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.comment = comment;
        this.isOrderButton = isOrderButton;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {true, "Иван", "Иванов", "ул. Яснополянская, д.7, кв. 98", "Рязанский проспект", "79876543221", "01.04.2024", "коммент1"},
                {false, "СЕМЕН", "сидоров", "Зеленоград", "Динамо", "79998887766", "01.05.24", "коммент2"},
                {true, "Петя", "Петров", "корпус 2014, кв. 101", "Речной вокзал", "79111111111", "05.03.2024", "коммент3"},
        };
    }

    @Test
    public void testOrder() { //проверка заказа самоката
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl();
        mainPage.clickOrderButton(isOrderButton);//true - верхняя кнопка заказа, false - нижняя
        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForLoadOrderPage();
        orderPage.inputPersonalInformation(name, surname, address, station, phoneNumber); //вводим инфо о заказчике
        orderPage.inputAdditionalInformation(date, comment); //вводим доп инфу о заказе
        boolean isDisplayed = orderPage.checkOrderConfirmationIsDisplayed();
        Assert.assertTrue("Подтверждение заказа не отображается", isDisplayed);

    }

    public static WebDriver createDriver(String browser) {
        driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            System.out.println("Не поддерживаемый браузер" + browser);
        }
        return driver;
    }
}
