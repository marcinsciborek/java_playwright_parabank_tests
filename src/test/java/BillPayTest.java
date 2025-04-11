import com.microsoft.playwright.Locator;
import com.parabank.pages.BillPayPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillPayTest extends BaseTest {

    BillPayPage billPayPage;

    @BeforeEach
    void setupPage() {
        billPayPage = new BillPayPage(page);
        page.waitForSelector("text=Bill Pay");
        page.click("text=Bill Pay");
    }

    @Test
    public void billPaymentServiceTest() {
        page.waitForSelector("text=Bill Payment Service");
        page.click("text=Bill Payment Service");
        BillPayPage billPayPage = new BillPayPage(page);
        page.click("text=Bill Pay");
        page.waitForSelector("text=Bill Payment Service");
        BigDecimal amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
        Locator fromAccountLocator = page.locator("select[name='fromAccountId']");
        String fromAccount = fromAccountLocator.locator("option").first().getAttribute("value");
        page.click("text=Bill Pay");

        billPayPage.enterPayeeDetails(
                ConfigReader.getProperty("firstName") + " " + ConfigReader.getProperty("lastName"),
                ConfigReader.getProperty("address"),
                ConfigReader.getProperty("city"),
                ConfigReader.getProperty("state"),
                ConfigReader.getProperty("zipCode"),
                ConfigReader.getProperty("phoneNumber"));
        billPayPage.enterPaymentDetails("987654", "987654", amount.toString(), fromAccount);
        billPayPage.submitPayment();

        assertTrue(billPayPage.isPaymentSuccessVisible(), "Bill Payment confirmation should be visible.");
    }
}
