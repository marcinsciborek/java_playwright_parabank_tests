import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.parabank.pages.LoginPage;
import org.junit.jupiter.api.*;
import utils.ConfigReader;


public class BaseTest {
    static Playwright playwright;
    static Browser browser;
    Page page;
    LoginPage loginPage;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    }

    @BeforeEach
    void createContext() {
        page = browser.newPage();
        loginPage = new LoginPage(page);

        page.navigate("https://parabank.parasoft.com/parabank/index.htm");
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        if (page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Available Amount")).isVisible()) {
        } else {
            System.out.println("Login failed, creating new account...");
            loginPage.createAccount(
                    "https://parabank.parasoft.com/parabank/register.htm",
                    ConfigReader.getProperty("firstName"),
                    ConfigReader.getProperty("lastName"),
                    ConfigReader.getProperty("address"),
                    ConfigReader.getProperty("city"),
                    ConfigReader.getProperty("state"),
                    ConfigReader.getProperty("zipCode"),
                    ConfigReader.getProperty("phoneNumber"),
                    ConfigReader.getProperty("ssn"),
                    ConfigReader.getProperty("username"),
                    ConfigReader.getProperty("password")
            );
        }
    }

    @AfterEach
    void closePage() {
        page.close();
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }
}
