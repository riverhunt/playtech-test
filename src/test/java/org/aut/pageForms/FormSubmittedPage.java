package org.aut.pageForms;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormSubmittedPage {
    public WebDriver driver;

    public FormSubmittedPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[contains(@class,'ResponseLinksContainer')]/a")
    private WebElement editResponseLink;


    public boolean editLinkPresent() {
        try {
            this.editResponseLink.getText();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
