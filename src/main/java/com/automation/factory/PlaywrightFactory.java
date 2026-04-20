package com.automation.factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public Page initBrowser(String browserName, boolean headless) {
        tlPlaywright.set(Playwright.create());
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(headless);

        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(tlPlaywright.get().chromium().launch(options));
                break;
            case "firefox":
                tlBrowser.set(tlPlaywright.get().firefox().launch(options));
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name provided.");
        }

        tlContext.set(tlBrowser.get().newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080)));
        tlPage.set(tlContext.get().newPage());
        return tlPage.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public void quitBrowser() {
        if (tlPage.get() != null) {
            tlPage.get().context().browser().close();
            tlPlaywright.get().close();
            tlPage.remove();
            tlContext.remove();
            tlBrowser.remove();
            tlPlaywright.remove();
        }
    }
}
