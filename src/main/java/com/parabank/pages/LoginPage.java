package com.parabank.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {
    private final Page page;
    private final String usernameField = "//input[@name='username']";
    private final String passwordField = "//input[@name='password']";
    private final String loginButton = "//input[@value='Log In']";
    private final String firstNameField = "input[name='customer.firstName']";
    private final String lastNameField = "input[name='customer.lastName']";
    private final String addressField = "input[name='customer.address.street']";
    private final String cityField = "input[name='customer.address.city']";
    private final String stateField = "input[name='customer.address.state']";
    private final String zipCodeField = "input[name='customer.address.zipCode']";
    private final String phoneField = "input[name='customer.phoneNumber']";
    private final String ssnField = "input[name='customer.ssn']";
    private final String newUsernameField = "//input[@name='customer.username']";
    private final String newPasswordField = "//input[@name='customer.password']";
    private final String confirmPasswordField = "input[name='repeatedPassword']";
    private final String registerButton = "Register";
    private final String logOut = "//a[text()='Log Out']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void clickRegisterNewUserButton() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(registerButton)).click();
    }

    public void createAccount(String url, String firstName, String lastName, String street, String city, String state, String zipCode, String phoneNumber, String ssn,
                              String username, String password){
        page.navigate(url);
        page.fill(firstNameField, firstName);
        page.fill(lastNameField, lastName);
        page.fill(addressField, street);
        page.fill(cityField, city);
        page.fill(stateField, state);
        page.fill(zipCodeField, zipCode);
        page.fill(phoneField, phoneNumber);
        page.fill(ssnField, ssn);

        page.fill(newUsernameField, username);
        page.fill(newPasswordField, password);
        page.fill(confirmPasswordField, password);
        clickRegisterNewUserButton();
        page.click(logOut);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void enterUsername(String username) {
        page.fill(usernameField, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordField, password);
    }

    public void clickLogin() {
        page.click(loginButton);
    }

    public boolean isLoggedIn() {
        try {
            return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions()
                    .setName("Accounts Overview")).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

}
