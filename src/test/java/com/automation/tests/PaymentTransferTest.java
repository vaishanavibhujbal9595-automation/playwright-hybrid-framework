package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.DashboardPage;
import com.automation.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PaymentTransferTest extends BaseTest {

    @DataProvider(name = "paymentData")
    public Object[][] getPaymentData() {
        // Ensure "Sheet1" exactly matches the tab name at the bottom of your Excel file
        return ExcelReader.getTestData("src/test/resources/testdata.xlsx", "Sheet1");
    }

    // THE METHOD MUST HAVE EXACTLY 3 STRINGS to match the 3 columns
    @Test(dataProvider = "paymentData")
    public void verifyUpiTransfer(String vpa, String amount, String expectedStatus) {

        DashboardPage dashboardPage = loginPage.doLogin(
                config.getProperty("username"),
                config.getProperty("password")
        );

        dashboardPage.initiateTransfer(vpa, amount);

        String actualStatus = dashboardPage.getTransferStatus();

        System.out.println("Validating Transfer - VPA: " + vpa + " | Amount: " + amount + " | Expected: " + expectedStatus);
        Assert.assertEquals(actualStatus, "SUCCESS", "Transaction status did not match expected value!");
    }
}