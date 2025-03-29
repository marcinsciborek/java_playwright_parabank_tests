package com.parabank.pages;

import com.microsoft.playwright.Page;

import java.math.BigDecimal;

public class TransferFundsPage {

    private final Page page;

    private final String amountField = "//input[@id='amount']";
    private final String fromAccountField = "//select[@id='fromAccountId']";
    private final String toAccountField = "//select[@id='toAccountId']";
    private final String transferButton = "//input[@value='Transfer']";


    public TransferFundsPage(Page page) {
        this.page = page;
    }

    public void enterAmount(BigDecimal amount) {
        page.locator(amountField).fill(String.valueOf(amount));
    }

    public void selectFromAccount(String accountNumber) {
        page.locator(fromAccountField).selectOption(accountNumber);
    }

    public void selectToAccount(String accountNumber) {
        page.locator(toAccountField).selectOption(accountNumber);
    }

    public void submitTransfer() {
        page.locator(transferButton).click();
    }

    public void transferFunds(BigDecimal amount, String fromAccount, String toAccount) {
        enterAmount(amount);
        selectFromAccount(fromAccount);
        selectToAccount(toAccount);
        submitTransfer();
    }
}
