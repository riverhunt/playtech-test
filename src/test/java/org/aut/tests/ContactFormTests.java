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
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static org.aut.CommonMethods.*;

public class ContactFormTests {
    private static WebDriver driver;
    private static FormPage formPage;
    private static FormSubmittedPage formSubmittedPage;
    private static TestData testData = loadTestData("testdata.json");
    private static final String url = ConfProperties.getProperty("url");

    @DataProvider
    public Object[][] getNames() {
        return testData.invalidNames;
    }

    @DataProvider
    public Object[][] getEmails() {
        return testData.invalidEmails;
    }

    @DataProvider
    public Object[][] getAddresses() {
        return testData.invalidAddresses;
    }

    @DataProvider
    public Object[][] getPhoneNumbers() {
        return testData.invalidPhones;
    }

    @BeforeClass
    public void checkTestData() {
        Assert.assertNotNull(testData);
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

    @Test(dataProvider = "getNames")
    public void nameIsWrong_shouldShowValidationError(String name) {
        formPage.inputName(name);
        formPage.inputEmail(testData.email);
        formPage.inputAddress(testData.address);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent(), "No Validation Error for name: " + name);
    }

    @Test
    public void emailIsEmpty_shouldShowValidationError() {
        formPage.selectOption(2);
        formPage.inputName(testData.name);
        formPage.inputAddress(testData.address);
        formPage.inputPhone(testData.phone);
        formPage.inputComment(testData.comment);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent());
    }

    @Test(dataProvider = "getEmails")
    public void emailIsWrong_shouldShowValidationError(String email) {
        formPage.selectOption(3);
        formPage.inputName(testData.name);
        formPage.inputAddress(testData.address);
        formPage.inputPhone(testData.phone);
        formPage.inputComment(testData.comment);
        formPage.inputEmail(email);
        formPage.clickSubmitButton();
        Assert.assertTrue(formPage.errorAlertPresent(), "No Validation Error for email: " + email);
    }

    @Test
    public void addressIsEmpty_shouldShowValidationError() {
        formPage.selectOption(4);
        formPage.inputName(testData.name);
        formPage.inputEmail(testData.email);
        formPage.inputPhone(testData.phone);
        formPage.inputComment(testData.comment);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent());
    }

    @Test(dataProvider = "getAddresses")
    public void addressIsWrong_shouldShowValidationError(String address) {
        formPage.inputName(testData.name);
        formPage.inputEmail(testData.email);
        formPage.inputAddress(address);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent(), "No Validation Error for address: " + address);
    }

    @Test(dataProvider = "getPhoneNumbers")
    public void phoneNumberIsWrong_shouldShowValidationError(String phone) {
        formPage.selectOption(5);
        formPage.inputName(testData.name);
        formPage.inputEmail(testData.email);
        formPage.inputAddress(testData.address);
        formPage.inputPhone(phone);
        formPage.inputComment(testData.comment);
        formPage.clickSubmitButton();

        Assert.assertTrue(formPage.errorAlertPresent(), "No Validation Error for phone number: " + phone);
    }

    @Test
    public void clearSelectionButtonTest_optionSelectionShouldBeCleared() {
        formPage.selectOption(6);
        formPage.inputRadioChoiceComment(testData.radioComment);
        Assert.assertTrue(formPage.isOptionSelected(), "No option selected.");
        formPage.clearRadioChoice();
        Assert.assertFalse(formPage.isOptionSelected(), "Option still selected.");
    }

    @Test
    public void allRequiredFieldsAreFilled_formShouldGetSubmitted() {
        formPage.inputName(testData.name);
        formPage.inputEmail(testData.email);
        formPage.inputAddress(testData.address);
        formPage.clickSubmitButton();

        Assert.assertTrue(formSubmittedPage.editLinkPresent());
    }

    @Test
    public void asterisksArePresentInRequiredFieldsTitles() {
        SoftAssert softAssertion= new SoftAssert();

        softAssertion.assertFalse(formPage.containsAsterisk(formPage.getRadioChoiceTitle()));
        softAssertion.assertTrue(formPage.containsAsterisk(formPage.getNameTitle()));
        softAssertion.assertTrue(formPage.containsAsterisk(formPage.getEmailTitle()));
        softAssertion.assertTrue(formPage.containsAsterisk(formPage.getAddressTitle()));
        softAssertion.assertFalse(formPage.containsAsterisk(formPage.getPhoneTitle()));
        softAssertion.assertFalse(formPage.containsAsterisk(formPage.getCommentTitle()));

        softAssertion.assertAll();
    }
}
