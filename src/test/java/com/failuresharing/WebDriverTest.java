package com.failuresharing;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ChromeTest
public class WebDriverTest {

    @Test
    public void testApp(WebDriver driver) {
        driver.get("https://www.naver.com"); 
        assertTrue(true);
    }
}
