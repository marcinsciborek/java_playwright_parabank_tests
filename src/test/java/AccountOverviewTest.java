import com.parabank.pages.AccountOverviewPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountOverviewTest extends BaseTest{

    AccountOverviewPage accountOverviewPage;

    @BeforeEach
    void setupPage() {
        accountOverviewPage = new AccountOverviewPage(page);
        page.waitForSelector("text=Accounts Overview");
        page.click("text=Accounts Overview");
    }

    @Test
    void accountOverviewPageIsDisplayedTest() {
        assertTrue(accountOverviewPage.isAccountOverviewVisible(), "Account Overview page is not visible");
    }

    @Test
    void accountsTableIsVisibleTest() {
        assertTrue(accountOverviewPage.isAccountsTableVisible(), "Accounts table is not visible");
    }

    @Test
    void checkFirstAvailableBalanceTest() {
        String balance = accountOverviewPage.getFirstAvailableBalance();
        System.out.println("First available balance: " + balance);
        assertTrue(balance.startsWith("$"), "Balance format is incorrect");
    }

    @Test
    void checkAccountDetailsBalanceTest() {
        accountOverviewPage.getAccountBalanceDetails();
        String available = accountOverviewPage.getAccountDetailsBalanceField();
        assertTrue(available.startsWith("$") || available.startsWith("-$"), "Balance format in Account Details is incorrect");
    }

    @Test
    void checkAccountActivityTest(){
        accountOverviewPage.getAccountBalanceDetails();
        accountOverviewPage.isAccountActivityPerPeriodTypeVisible();
        boolean isNoTransactionsFoundVisible = page.locator("text=No transactions found").isVisible();
        boolean isTransactionTableVisible = page.locator("#transactionTable").isVisible();
        assertTrue(isNoTransactionsFoundVisible || isTransactionTableVisible,
                "The 'No transactions found' message or the Transaction table should be visible.");
    }
}
