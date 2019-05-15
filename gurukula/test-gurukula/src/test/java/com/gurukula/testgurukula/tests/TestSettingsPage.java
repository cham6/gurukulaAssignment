package com.gurukula.testgurukula.tests;

/**
 * Copyright (c) 2019, ChamLabs.
 * Responsible: Chandra.Sekhar.Muttineni
 * @author https://github.com/cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/TestAutomationFrameworkForGurukula
 */

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gurukula.testgurukula.implementations.TestBaseImpl;
import com.gurukula.testgurukula.pages.LoggedInPage;
import com.gurukula.testgurukula.pages.LoginPage;
import com.gurukula.testgurukula.pages.SettingsPage;
import com.gurukula.testgurukula.testData.DataProviders;
import com.gurukula.testgurukula.util.TestUtil;

/**
 * Test class to test all the scenarios on Settings page.
 * @author Chandra.Sekhar.Muttineni
 *
 */

public class TestSettingsPage extends TestBaseImpl {

	private SettingsPage settingsPage;
	private LoginPage loginPage;
	private LoggedInPage loggedInPage;
	String context = "Settings";
	
    @BeforeMethod
    public void setUp() {
    	super.setUp();
        TestUtil.loginAndNavigateToContext(driver, context);
        settingsPage = new SettingsPage(driver);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider="ValidSettingsDataProvider")
    public void testSettingsSavedSuccessfully(String firstName, String lastName, 
    		String email, String language) {
    	
    	settingsPage.saveSettings(firstName, lastName, email, language);
    	Assert.assertTrue(settingsPage.isSaveEnabled());
    	Assert.assertTrue(settingsPage.hasSettingsSavedSuccessfully());
    }
    
    @Test(dataProviderClass = DataProviders.class, dataProvider="InvalidSettingsDataProvider")
    public void testSettingsSaveFailed(String firstName, String lastName, 
    		String email, String language) {
    	
    	settingsPage.saveSettings(firstName, lastName, email, language);
    	Assert.assertFalse(settingsPage.isSaveEnabled());
    }

 /*  
    @Test
    public void testSettingsSavedSuccessfully() {
    	settingsPage = new SettingsPage(driver);
    	settingsPage.saveSettings("Administrator", "Administrator", 
    			"admin@localhost", "English");
    	Assert.assertTrue(settingsPage.hasSettingsSavedSuccessfully());
    }
    
    @Test
    public void testSettingsSaveFailed() {
    	settingsPage = new SettingsPage(driver);
    	settingsPage.saveSettings("Administratora", "Administrator", 
    			"admin@localhost", "English");
    	Assert.assertTrue(settingsPage.hasSaveSettingsFailed());
    }
   */  
    
    @AfterMethod
    public void tearDown() {
    	super.tearDown();
    	
    }
}
