package com.automation.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    // Locators
    private String loginMenu = "#login2";
    private String usernameInput = "#loginusername";
    private String passwordInput = "#loginpassword";
    private String loginBtn = "//button[text()='Log in']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public DashboardPage doLogin(String username, String password) {
        page.click(loginMenu);
        page.fill(usernameInput, username);
        page.fill(passwordInput, password);
        page.click(loginBtn);
        return new DashboardPage(page);
    }
}