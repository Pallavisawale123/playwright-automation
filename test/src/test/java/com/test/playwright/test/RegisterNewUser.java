package com.test.playwright.test;

import com.microsoft.playwright.*;
import com.test.playwright.test.utils.PlaywrightUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author psawale
 * @project test automation using Playwright
 * @date 11/22/2025
 */
@Slf4j
public class RegisterNewUser {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    // XPaths for Signup Page
    private final String headerXpath = "//*[text()='Sign Up']";
    private final String nameXpath = "//*[@placeholder='Name']";
    private final String emailXpath = "//*[@placeholder='Email']";
    private final String passwordXpath = "//*[@placeholder='Password']";
    private final String femaleRadio = "//input[@value='Female']";
    private final String stateDropdown = "//select[@name='state']";
    private final String hobbiesDropdown = "//select[@name='hobbies']";
    private final String signup = "//*[@class='submit-btn']";
    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(100)
//                        .setArgs(Arrays.asList("--window-size=400,300"))
        );

        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @Test
    public void signUpPageTest() {
        try {
            page.navigate("https://freelance-learn-automation.vercel.app/signup");
            log.info("Page title: {}", page.title());

            boolean headerFound = PlaywrightUtils.isElementPresent(page, headerXpath);

            if (headerFound) {
                log.info("Signup header is visible. Proceeding with form filling...");

                // Fill Name
                page.locator(nameXpath).fill("PallaviSawale");

                // Fill Email
                page.locator(emailXpath).fill("psawale@gmail.com");

                // Fill Password
                page.locator(passwordXpath).fill("SaiSai@123");

                // Select Selenium (checkbox)
//                page.getByAltText("Selenium").click();
                page.getByLabel("Selenium").check();
                // Select Gender: Female
                page.locator(femaleRadio).click();

                // Select State
                page.locator(stateDropdown).selectOption("Maharashtra");

                // Select Multiple Hobbies
                String[] hobbies = {"Playing", "Reading"};
                page.locator(hobbiesDropdown).selectOption(hobbies);
                page.locator(signup).click();

                log.info("Signup form filled successfully.");

            } else {
                log.error("Signup header NOT found. Cannot proceed.");
            }

        } catch (Exception e) {
            log.error("Test failed due to exception: ", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
