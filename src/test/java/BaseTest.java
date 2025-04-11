import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.parabank.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

        if (!loginPage.isLoggedIn()) {
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
            loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
            if (!loginPage.isLoggedIn()) {
                throw new IllegalStateException("Login failed.");
            }
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
