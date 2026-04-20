
package com.automation.base;

import com.automation.factory.PlaywrightFactory;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected PlaywrightFactory factory;
    protected Page page;
    protected ConfigReader config;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        config = new ConfigReader();
        factory = new PlaywrightFactory();

        String browserName = config.getProperty("browser");
        boolean isHeadless = Boolean.parseBoolean(config.getProperty("headless"));

        page = factory.initBrowser(browserName, isHeadless);
        page.navigate(config.getProperty("app.url"));

        loginPage = new LoginPage(page);
    }

    @AfterMethod
    public void tearDown() {
        factory.quitBrowser();
    }
}