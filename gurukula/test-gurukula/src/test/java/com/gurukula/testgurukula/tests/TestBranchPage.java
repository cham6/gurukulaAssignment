package com.gurukula.testgurukula.tests;

import static org.junit.Assert.fail;

/**
 * Copyright (c) 2019, ChamLabs.
 * Responsible: Chandra.Sekhar.Muttineni
 * @author https://github.com/cham6
 * @email: paperplanes.chandra@gmail.com
 * @fork: https://github.com/cham6/TestAutomationFrameworkForGurukula
 */

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gurukula.testgurukula.implementations.TestBaseImpl;
import com.gurukula.testgurukula.pages.BranchPage;
import com.gurukula.testgurukula.pages.LoggedInPage;
import com.gurukula.testgurukula.pages.LoginPage;
import com.gurukula.testgurukula.testData.DataProviders;
import com.gurukula.testgurukula.util.TestUtil;

/**
 * Test class to test all the scenarios on Branches page.
 * @author Chandra.Sekhar.Muttineni
 *
 */
public class TestBranchPage extends TestBaseImpl{

	BranchPage branchPage;
	LoginPage loginPage;
	LoggedInPage loggedInPage;
	String context = "Branch";
	
	String branchName = "BRANCHNAME";
	String branchCode = "BRANCHCODE";
	
	String branchNameEdited = "EDITEDNAME";
	String branchCodeEdited = "EDITEDCODE";
	
    @BeforeMethod
    public void setUp() {
    	super.setUp();
        TestUtil.loginAndNavigateToContext(driver, context);
        branchPage = new BranchPage(driver);
    }

    /**
     * Test valid Branch creation test cases. 
     * @param branchName
     * @param branchCode
     */
    @Test(dataProviderClass = DataProviders.class, dataProvider="ValidBranchDataProvider")
    public void testNewBranchCreationSuccessful(String branchName, String branchCode) {
    	try {
	    	branchPage.createNewBranch(branchName, branchCode);
	    	Assert.assertTrue(branchPage.isViewBranchSuccessful(branchName, branchCode));	    	
    	} catch(Exception e) {
			System.out.println("New Branch creation has failed in testNewBranchCreationSuccessful");
			e.printStackTrace();
			fail("Test testNewBranchCreationSuccessful failed.");
		}
    }
    
    /**
     * Test failures during Branch creations.
     * @param branchName
     * @param branchCode
     */
    @Test(dataProviderClass = DataProviders.class, dataProvider="InvalidBranchDataProvider")
    public void testNewBranchCreationFailure(String branchName, String branchCode) {
    	try {
	    	branchPage.createNewBranch(branchName, branchCode);
	    	Assert.assertFalse(branchPage.isSaveEnabled());
	    	branchPage.cancelNewBranchWizard();
	    	Assert.assertFalse(branchPage.isViewBranchSuccessful(branchName, branchCode));	    	
    	} catch(Exception e) {
			System.out.println("'Exception while running testNewBranchCreationFailure."
					+ "Test failed.");
			e.printStackTrace();
			fail("Test testNewBranchCreationFailure failed.");
		}
    }
 
    /**
     * Test the View action on a New branch
     */
    @Test
    public void testViewActionOnNewBranch() {
    	try {
	    	branchPage.createNewBranch(branchName, "VIEWCODE");
	    	Assert.assertTrue(branchPage.isViewBranchSuccessful(branchName, branchCode));
    	} catch(Exception e) {
			System.out.println("'Save button is not enabled");
			e.printStackTrace();
		}
    }
  
    /**
     * Test the Edit action on a new branch.
     */
    @Test
    public void testEditActionOnNewBranch() {
    	try {
	    	branchPage.createNewBranch(branchName, "EDITCODE");
	    	Assert.assertTrue(branchPage.isEditBranchSuccessful(branchName, branchCode, 
	    			branchNameEdited, branchCodeEdited));
    	} catch(Exception e) {
			System.out.println("'Exception in test testEditActionOnNewBranch");
			e.printStackTrace();
		}
    }
    
    /**
     * Test delete action on a newly created branch
     */
    @Test
    public void testDeleteActionOnNewBranch() {
    	try {
	    	
	    	branchPage.createNewBranch(branchName, branchCode);
	    	Assert.assertTrue(branchPage.isDeleteBranchSuccessful(branchName, branchCode), 
	    			"The deleted Branch has found during search");
    	} catch(Exception e) {
			System.out.println("'Exception in test testDeleteActionOnNewBranch");
			e.printStackTrace();
		}
    } 
    
    /**
     * Tear down method for cleanup after each test.
     */
    @AfterMethod
    public void tearDown() {
    	super.tearDown();
    }
	
}
