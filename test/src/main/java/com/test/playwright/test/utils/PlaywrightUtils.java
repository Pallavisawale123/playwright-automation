package com.test.playwright.test.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

/**
 * @author psawale
 * @project test
 * @date 11/22/2025
 */
@Slf4j
public class PlaywrightUtils {
    private final Page page;    // store page from constructor

    // Constructor to pass shared Page instance
    public PlaywrightUtils(Page page) {
        this.page = page;
    }
    public static boolean isElementPresent(Page page, String xpath) {
        Locator loc = page.locator("xpath=" + xpath);
        boolean present = loc.count() > 0;

        if (present) {
            log.info("Element found: {}", xpath);
        } else {
            log.info("Element NOT found: {}", xpath);
        }

        return present;
    }
}
