package org.aut.pageForms;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FormPage {
    public WebDriver driver;

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div/div[2]/div[1]/div/span/div")
    private WebElement radioButtonsContainer;

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/input")
    private WebElement nameField;

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[1]/input")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[4]/div/div/div[2]/div/div[1]/div[2]/textarea")
    private WebElement addressField;

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div/div[1]/input")
    private WebElement phoneField;

    @FindBy(xpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[6]/div/div/div[2]/div/div[1]/div[2]/textarea")
    private WebElement commentsField;

    @FindBy(xpath = "//div[(@role='button') and (contains(@class,'SubmitButton'))]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[(@role='alert') and (contains(@class,'ValidationError'))]")
    private WebElement errorAlert;


    public void inputName(String name) {
        nameField.sendKeys(name);
    }

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void inputAddress(String address) {
        addressField.sendKeys(address);
    }

    public void inputPhone(String phone) {
        phoneField.sendKeys(phone);
    }

    public void inputComment(String comment) {
        commentsField.sendKeys(comment);
    }

    public void selectOption(int option) {
        List<WebElement> options = radioButtonsContainer.findElements(By.xpath(".//div[@role='radio']"));
        if (options.size() >= option - 1)
            options.get(option - 1).click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public boolean errorAlertPresent() {
        try {
            this.errorAlert.getText();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
