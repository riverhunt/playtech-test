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

    @FindBy(xpath = "(//div[contains(@class,'QuestionBaseTitleDescContainer')])[1]")
    private WebElement radioChoiceTitle;

    @FindBy(xpath = "//div[contains(@class,'QuestionRadioChoicesContainer')]")
    private WebElement radioChoicesContainer;

    @FindBy(xpath = "(//input[@type='text'])[1]")
    private WebElement radioChoiceComment;

    @FindBy(xpath = "(//div[contains(@class,'QuestionRadioFooter')])")
    private WebElement radioChoiceFooter;

    @FindBy(xpath = "(//div[contains(@class,'QuestionBaseTitleDescContainer')])[2]")
    private WebElement nameTitle;

    @FindBy(xpath = "(//input[@type='text'])[2]")
    private WebElement nameField;

    @FindBy(xpath = "(//div[contains(@class,'QuestionBaseTitleDescContainer')])[3]")
    private WebElement emailTitle;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "(//div[contains(@class,'QuestionBaseTitleDescContainer')])[4]")
    private WebElement addressTitle;

    @FindBy(xpath = "(//textarea[contains(@class,'textareaInput')])[1]")
    private WebElement addressField;

    @FindBy(xpath = "(//div[contains(@class,'QuestionBaseTitleDescContainer')])[5]")
    private WebElement phoneTitle;

    @FindBy(xpath = "(//input[@type='text'])[3]")
    private WebElement phoneField;

    @FindBy(xpath = "(//div[contains(@class,'QuestionBaseTitleDescContainer')])[6]")
    private WebElement commentTitle;

    @FindBy(xpath = "(//textarea[contains(@class,'textareaInput')])[2]")
    private WebElement commentsField;

    @FindBy(xpath = "//div[(@role='button') and (contains(@class,'SubmitButton'))]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[(@role='alert') and (contains(@class,'ValidationError'))]")
    private WebElement errorAlert;


    public WebElement getRadioChoiceTitle() {
        return radioChoiceTitle;
    }

    public WebElement getNameTitle() {
        return nameTitle;
    }

    public WebElement getEmailTitle() {
        return emailTitle;
    }

    public WebElement getAddressTitle() {
        return addressTitle;
    }

    public WebElement getPhoneTitle() {
        return phoneTitle;
    }

    public WebElement getCommentTitle() {
        return commentTitle;
    }


    public void inputRadioChoiceComment(String name) {
        radioChoiceComment.sendKeys(name);
    }

    public void clearRadioChoice() {
        radioChoiceFooter.findElement(By.xpath(".//div[@role='button']")).click();
    }

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
        List<WebElement> options = radioChoicesContainer.findElements(By.xpath(".//div[@role='radio']"));
        if (options.size() >= option - 1)
            options.get(option - 1).click();
    }

    public void clickSubmitButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {}

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

    public boolean isOptionSelected() {
        List<WebElement> selected = radioChoicesContainer.findElements(By.xpath(".//label[contains(@class,'isChecked')]"));
        return selected != null && selected.size() > 0;
    }

    public boolean containsAsterisk(WebElement e) {
        return e.getText().contains("*");
    }
}
