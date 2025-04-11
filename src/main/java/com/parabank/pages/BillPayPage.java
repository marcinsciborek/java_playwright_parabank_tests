package com.parabank.pages;

import com.microsoft.playwright.Page;

public class BillPayPage {
    private final Page page;

    private final String payeeNameField = "[name='payee.name']";
    private final String addressField = "[name='payee.address.street']";
    private final String cityField = "[name='payee.address.city']";
    private final String stateField = "[name='payee.address.state']";
    private final String zipCodeField = "[name='payee.address.zipCode']";
    private final String phoneField = "[name='payee.phoneNumber']";
    private final String accountField = "[name='payee.accountNumber']";
    private final String verifyAccountField = "[name='verifyAccount']";
    private final String amountField = "[name='amount']";
    private final String fromAccountField = "[name='fromAccountId']";
    private final String sendPaymentButton = "input[value='Send Payment']";


    public BillPayPage(Page page) {
        this.page = page;
    }

    public void enterPayeeDetails(String name, String address, String city, String state, String zip, String phone) {
        page.fill(payeeNameField, name);
        page.fill(addressField, address);
        page.fill(cityField, city);
        page.fill(stateField, state);
        page.fill(zipCodeField, zip);
        page.fill(phoneField, phone);
    }

    public void enterPaymentDetails(String account, String verifyAccount, String amount, String fromAccount) {
        page.fill(accountField, account);
        page.fill(verifyAccountField, verifyAccount);
        page.fill(amountField, amount);
        page.selectOption(fromAccountField, fromAccount);
    }

    public void submitPayment() {
        page.click(sendPaymentButton);
    }

    public boolean isPaymentSuccessVisible() {
        return page.locator("text=Bill Payment Complete").isVisible();
    }
}
