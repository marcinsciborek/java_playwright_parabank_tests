Parabank Automated Tests

About:
This project is an automated test suite for the Parabank application using Java + Playwright.

Tech Stack:
-Java 17
-Playwright (UI Testing)
-JUnit 5 (Test Framework)
-Maven (Dependency Management)
-IntelliJ IDEA (IDE)

Project Structure:

parabank_tests_java_playwright/
│-- src/
│   ├── main/java/com/parabank/pages/    # Page Object Model (POM) classes
│   ├── test/java/com/parabank/tests/    # Test classes
│-- pom.xml                              # Maven dependencies
│-- README.md                            # This file
│-- .gitignore                           # Git ignored files

Setup Instructions:
1. Clone the repository

git clone https://github.com/marcinsciborek/parabank_tests_java_playwright.git
cd parabank_tests_java_playwright

2. Install dependencies
Run the following command in the project root:

mvn clean install

3. Run tests
mvn test

OR execute individual test classes from IntelliJ IDEA.

Features Covered:
-Login Test
-Registration Test
-Account Overview Test
-Funds Transfer Test
-Account Activity Verification

Author:

Marcin Ściborek