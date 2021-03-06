package com.gurukula.testgurukula.pages;

/**
 * Copyright (c) 2019, ChamLabs.
 * Responsible: Chandra.Sekhar.Muttineni
 * @author https://github.com/cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/TestAutomationFrameworkForGurukula
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class to represent the Base for any Page Objects created for testing.
 * Can be used to represent any common elements or functionalities for Page Objects.
 * @author Chandra.Sekhar.Muttineni
 *
 */
public class BasePage {

	private static final int TIMEOUT_IN_SECONDS = 20;
    private static final int POLLING_INTERVAL_IN_MILLISECONDS = 100;
    
    //Page Factory locators and their corresponding web elements.
    @FindBy(xpath="//a[@title='Gurukula']")
    WebElement homePageImage;
    
    protected WebDriver driver;
    private WebDriverWait wait;
    
    //Constructor to initialize and use the driver object passed by Test classes.
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, TIMEOUT_IN_SECONDS, POLLING_INTERVAL_IN_MILLISECONDS);
    }

    //Can create any Instance methods that can be used by all the pages. 
    //The WebDriver Wait is something that can be initialized here for re-usability.
    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
