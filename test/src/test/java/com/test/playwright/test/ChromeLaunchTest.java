package com.test.playwright.test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author psawale
 * @project test
 * @date 11/8/2025
 */
public class ChromeLaunchTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(100));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @Test
    public void testChromeLaunch() {
        try {
            page.navigate("https://practicetestautomation.com/practice-test-login/");
            PlaywrightAssertions.assertThat(page)
                    .hasTitle("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
