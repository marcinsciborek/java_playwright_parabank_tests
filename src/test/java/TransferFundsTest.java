import com.parabank.pages.TransferFundsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferFundsTest extends BaseTest {

    TransferFundsPage transferFundsPage;

    @BeforeEach
    void setupPage() {
        transferFundsPage = new TransferFundsPage(page);
        page.waitForSelector("text=Transfer Funds");
        page.click("text=Transfer Funds");
    }

    @Test
    void transferFundsTest() {

        BigDecimal amount = BigDecimal.ONE;
        String firstAccount = page.locator("//select[@id='fromAccountId']/option[1]").getAttribute("value");
        String secondAccount = page.locator("//select[@id='toAccountId']/option[2]").count() > 0
                ? page.locator("//select[@id='toAccountId']/option[2]").getAttribute("value")
                : firstAccount;
        transferFundsPage.transferFunds(amount, firstAccount, secondAccount);

        assertTrue(page.locator("text=Transfer Complete!").isVisible(),
                "Transfer confirmation should be visible.");
    }

}
