package com.failuresharing;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverParameterResolver implements ParameterResolver, AfterAllCallback, BeforeAllCallback {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == WebDriver.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(ExtensionContext.Namespace.GLOBAL)
                .get("driver", WebDriver.class);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL)
                .put("driver", driver);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        WebDriver driver = extensionContext.getStore(ExtensionContext.Namespace.GLOBAL)
                .get("driver", WebDriver.class);
        if (driver != null) {
            driver.quit();
        }
    }
}
