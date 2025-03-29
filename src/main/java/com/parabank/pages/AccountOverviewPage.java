package com.parabank.pages;

import com.microsoft.playwright.Page;

import java.util.Random;

public class AccountOverviewPage {

    private final Page page;

    private final String accountOverviewHeader = "h1:text('Accounts Overview')";
    private final String accountsTable = "table[id='accountTable']";
    private final String firstAvailableBalance = "table[id='accountTable'] tr:nth-child(2) td:nth-child(2)";
    private final String accountNumberDetails = "//table[@id='accountTable']//td/a";
    private final String accountDetailsBalance = "//td[text()='Balance:']/following-sibling::td";
    private final String activityPeriodDropdown = "//select[@id='month']";
    private final String typeDropdown = "//select[@id='month']";
    private final String goButton = "//select[@id='transactionType']";

    public AccountOverviewPage(Page page) {
        this.page = page;
    }

    public boolean isAccountOverviewVisible() {
        return page.locator(accountOverviewHeader).isVisible();
    }

    public boolean isAccountsTableVisible() {
        return page.locator(accountsTable).isVisible();
    }

    public String getFirstAvailableBalance() {
        return page.locator(firstAvailableBalance).textContent().trim();
    }

    public void getAccountBalanceDetails(){
        page.click(accountNumberDetails);
    }

    public String getAccountDetailsBalanceField(){
        return page.locator(accountDetailsBalance).textContent().trim();
    }

    public void isAccountActivityPerPeriodTypeVisible(){
        Random random = new Random();
        int monthIndex = random.nextInt(12) + 1 ;
        int typeIndex = random.nextInt(2) + 1;

        page.selectOption("#month", page.locator("#month option").nth(monthIndex).getAttribute("value"));
        page.selectOption("#transactionType", page.locator("#transactionType option").nth(typeIndex).getAttribute("value"));

        page.click(goButton);
    }
}
