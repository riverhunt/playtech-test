package org.aut.tests;

import org.aut.ConfProperties;
import org.aut.TestData;
import org.aut.pageForms.FormPage;
import org.aut.pageForms.FormSubmittedPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static org.aut.CommonMethods.*;

public class ContactFormTests {
    private WebDriver driver;
    private static FormPage formPage;
    private static FormSubmittedPage formSubmittedPage;
    private static TestData testData = loadTestData();
    private static final String url = ConfProperties.getProperty("url");

    @BeforeClass
    public void checkTestData() {
        Assert.assertNotNull(testData);
    }

    @DataProvider
    public Object[][] getEmails() {
        return testData.invalidEmails;
    }

    @DataProvider
    public Object[][] getPhoneNumbers() {
        return testData.invalidPhones;
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = getChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        formPage = new FormPage(driver);
        formSubmittedPage = new FormSubmittedPage(driver);

        driver.get(url);
    }

    @AfterMethod
    public void cleanUp(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Reporter.setCurrentTestResult(result);
            takeScreenshot(driver);
        }
        driver.quit();
    }

    @Test
    public void nameIsEmpty_shouldShowValidationError() {
        formPage.selectOption(1);
        formPage.inputEmail(testData.validEmail);
        formPage.inputAddress(testData.address);
        formPage.inputPhone(testData.validPhone);
        formPage.inputComment(testData.comment);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent());
    }

    @Test
    public void emailIsEmpty_shouldShowValidationError() {
        formPage.selectOption(2);
        formPage.inputName(testData.name);
        formPage.inputAddress(testData.address);
        formPage.inputPhone(testData.validPhone);
        formPage.inputComment(testData.comment);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent());
    }

    @Test(dataProvider = "getEmails")
    public void emailIsWrong_shouldShowValidationError(String email) {
        formPage.selectOption(3);
        formPage.inputName(testData.name);
        formPage.inputAddress(testData.address);
        formPage.inputPhone(testData.validPhone);
        formPage.inputComment(testData.comment);
        formPage.inputEmail(email);
        formPage.clickSubmitButton();
        Assert.assertTrue(formPage.errorAlertPresent(), "No Validation Error for email: " + email);
    }

    @Test
    public void addressIsEmpty_shouldShowValidationError() {
        formPage.selectOption(4);
        formPage.inputName(testData.name);
        formPage.inputEmail(testData.validEmail);
        formPage.inputPhone(testData.validPhone);
        formPage.inputComment(testData.comment);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent());
    }

    // this form will accept any kind of nonsense in "name" or "address" fields,
    // so I'm skipping negative tests for those
    // made one for the phone number though, not sure what the expected result should be.
    // is it meant to accept everything? or should it fail on wrong type of input?
    // idk, guess I'll make it fail to demonstrate screenshot taking on test fail

    @Test(dataProvider = "getPhoneNumbers")
    public void phoneNumberIsWrong_shouldShowValidationError(String phone) {
        formPage.selectOption(3);
        formPage.inputName(testData.name);
        formPage.inputEmail(testData.validEmail);
        formPage.inputAddress(testData.address);
        formPage.inputPhone(phone);
        formPage.inputComment(testData.comment);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent(), "No Validation Error for phone number: " + phone);
    }

    @Test
    public void allRequiredFieldsAreFilled_formShouldGetSubmitted() {
        formPage.inputName(testData.name);
        formPage.inputEmail(testData.validEmail);
        formPage.inputAddress(testData.address);
        formPage.clickSubmitButton();

        Assert.assertTrue(formSubmittedPage.editLinkPresent());
    }
}
