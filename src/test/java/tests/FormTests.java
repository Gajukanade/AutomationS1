package tests;

import base.BaseTest;
import pages.FormPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormTests extends BaseTest {
    private FormPage formPage;
    
    @BeforeMethod
    public void setUpTest() {
        formPage = new FormPage(driver, wait);
        formPage.clickReset(); // Reset form before each test
    }
    
    @Test(priority = 1, description = "Verify all form elements are present and visible")
    public void testFormElementsPresence() {
        System.out.println("Testing form elements presence...");
        
        // Test text fields
        Assert.assertTrue(driver.findElement(By.id("firstName")).isDisplayed(), "First name field not visible");
        Assert.assertTrue(driver.findElement(By.id("lastName")).isDisplayed(), "Last name field not visible");
        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), "Email field not visible");
        Assert.assertTrue(driver.findElement(By.id("phone")).isDisplayed(), "Phone field not visible");
        Assert.assertTrue(driver.findElement(By.id("age")).isDisplayed(), "Age field not visible");
        
        // Test dropdown
        Assert.assertTrue(driver.findElement(By.id("country")).isDisplayed(), "Country dropdown not visible");
        
        // Test radio buttons
        Assert.assertTrue(driver.findElement(By.id("male")).isDisplayed(), "Male radio not visible");
        Assert.assertTrue(driver.findElement(By.id("female")).isDisplayed(), "Female radio not visible");
        Assert.assertTrue(driver.findElement(By.id("other")).isDisplayed(), "Other radio not visible");
        
        // Test checkboxes
        Assert.assertTrue(driver.findElement(By.id("sports")).isDisplayed(), "Sports checkbox not visible");
        Assert.assertTrue(driver.findElement(By.id("music")).isDisplayed(), "Music checkbox not visible");
        Assert.assertTrue(driver.findElement(By.id("travel")).isDisplayed(), "Travel checkbox not visible");
        Assert.assertTrue(driver.findElement(By.id("technology")).isDisplayed(), "Technology checkbox not visible");
        
        // Test buttons
        Assert.assertTrue(driver.findElement(By.id("submitBtn")).isDisplayed(), "Submit button not visible");
        Assert.assertTrue(driver.findElement(By.id("resetBtn")).isDisplayed(), "Reset button not visible");
        
        System.out.println("✓ All form elements are present and visible");
    }
    
    @Test(priority = 2, description = "Test successful form submission with valid data")
    public void testSuccessfulFormSubmission() {
        System.out.println("Testing successful form submission...");
        
        formPage.fillFormWithValidData();
        formPage.clickSubmit();
        
        Assert.assertTrue(formPage.isSuccessMessageDisplayed(), "Success message not displayed");
        Assert.assertTrue(formPage.getSuccessMessageText().contains("Form submitted successfully"), 
                         "Success message text incorrect");
        
        System.out.println("✓ Form submitted successfully with valid data");
    }
    
    @Test(priority = 3, description = "Test required field validation")
    public void testRequiredFieldValidation() {
        System.out.println("Testing required field validation...");
        
        // Try to submit form without filling required fields
        formPage.clickSubmit();
        
        // Check if required fields show error styling
        Assert.assertTrue(formPage.hasErrorClass(driver.findElement(By.id("firstName"))), 
                         "First name field should have error class");
        Assert.assertTrue(formPage.hasErrorClass(driver.findElement(By.id("lastName"))), 
                         "Last name field should have error class");
        Assert.assertTrue(formPage.hasErrorClass(driver.findElement(By.id("email"))), 
                         "Email field should have error class");
        Assert.assertTrue(formPage.hasErrorClass(driver.findElement(By.id("terms"))), 
                         "Terms checkbox should have error class");
        
        System.out.println("✓ Required field validation working correctly");
    }
    
    @Test(priority = 4, description = "Test email validation")
    public void testEmailValidation() {
        System.out.println("Testing email validation...");
        
        formPage.enterFirstName("John");
        formPage.enterLastName("Doe");
        formPage.enterEmail("invalid-email");
        formPage.checkTerms();
        formPage.clickSubmit();
        
        // Check HTML5 validation message
        String validationMessage = driver.findElement(By.id("email")).getAttribute("validationMessage");
        Assert.assertFalse(validationMessage.isEmpty(), "Email validation should show error message");
        
        System.out.println("✓ Email validation working correctly");
    }
    
    @Test(priority = 5, description = "Test radio button functionality")
    public void testRadioButtonFunctionality() {
        System.out.println("Testing radio button functionality...");
        
        // Select male
        formPage.selectGender("male");
        Assert.assertTrue(formPage.isRadioSelected("male"), "Male radio should be selected");
        
        // Select female (should deselect male)
        formPage.selectGender("female");
        Assert.assertTrue(formPage.isRadioSelected("female"), "Female radio should be selected");
        Assert.assertFalse(formPage.isRadioSelected("male"), "Male radio should be deselected");
        
        // Select other
        formPage.selectGender("other");
        Assert.assertTrue(formPage.isRadioSelected("other"), "Other radio should be selected");
        Assert.assertFalse(formPage.isRadioSelected("female"), "Female radio should be deselected");
        
        System.out.println("✓ Radio button functionality working correctly");
    }
    
    @Test(priority = 6, description = "Test checkbox functionality")
    public void testCheckboxFunctionality() {
        System.out.println("Testing checkbox functionality...");
        
        // Test multiple selections
        String[] interests = {"sports", "music", "travel", "technology"};
        
        for (String interest : interests) {
            formPage.selectInterest(interest);
            Assert.assertTrue(formPage.isCheckboxSelected(interest), 
                             interest + " checkbox should be selected");
        }
        
        // Test deselection
        formPage.selectInterest("sports");
        Assert.assertFalse(formPage.isCheckboxSelected("sports"), 
                          "Sports checkbox should be deselected");
        
        System.out.println("✓ Checkbox functionality working correctly");
    }
    
    @Test(priority = 7, description = "Test dropdown functionality")
    public void testDropdownFunctionality() {
        System.out.println("Testing dropdown functionality...");
        
        String[] countries = {"india", "usa", "uk", "canada", "australia"};
        
        for (String country : countries) {
            formPage.selectCountry(country);
            Assert.assertEquals(formPage.getSelectedCountry(), country, 
                               "Selected country should be " + country);
        }
        
        System.out.println("✓ Dropdown functionality working correctly");
    }
    
    @Test(priority = 8, description = "Test form reset functionality")
    public void testFormResetFunctionality() {
        System.out.println("Testing form reset functionality...");
        
        // Fill form with data
        formPage.enterFirstName("John");
        formPage.enterLastName("Doe");
        formPage.enterEmail("john@example.com");
        formPage.selectGender("male");
        formPage.selectInterest("sports");
        
        // Reset form
        formPage.clickReset();
        
        // Verify form is reset
        Assert.assertTrue(formPage.isFieldEmpty(driver.findElement(By.id("firstName"))), 
                         "First name should be empty after reset");
        Assert.assertTrue(formPage.isFieldEmpty(driver.findElement(By.id("lastName"))), 
                         "Last name should be empty after reset");
        Assert.assertTrue(formPage.isFieldEmpty(driver.findElement(By.id("email"))), 
                         "Email should be empty after reset");
        Assert.assertFalse(formPage.isRadioSelected("male"), 
                          "Male radio should not be selected after reset");
        Assert.assertFalse(formPage.isCheckboxSelected("sports"), 
                          "Sports checkbox should not be selected after reset");
        
        System.out.println("✓ Form reset functionality working correctly");
    }
    
    @Test(priority = 9, description = "Test age field validation")
    public void testAgeFieldValidation() {
        System.out.println("Testing age field validation...");
        
        // Test valid age
        formPage.enterAge("25");
        Assert.assertEquals(driver.findElement(By.id("age")).getAttribute("value"), "25");
        
        // Test boundary values
        formPage.enterAge("1");
        Assert.assertEquals(driver.findElement(By.id("age")).getAttribute("value"), "1");
        
        formPage.enterAge("120");
        Assert.assertEquals(driver.findElement(By.id("age")).getAttribute("value"), "120");
        
        System.out.println("✓ Age field validation working correctly");
    }
    
    @Test(priority = 10, description = "Test textarea functionality")
    public void testTextareaFunctionality() {
        System.out.println("Testing textarea functionality...");
        
        String testComment = "This is a multi-line comment\nfor testing purposes\nwith special characters: @#$%";
        formPage.enterComments(testComment);
        
        Assert.assertEquals(driver.findElement(By.id("comments")).getAttribute("value"), testComment);
        
        System.out.println("✓ Textarea functionality working correctly");
    }
    
    @Test(priority = 11, description = "Test complete user journey")
    public void testCompleteUserJourney() {
        System.out.println("Testing complete user journey...");
        
        // Fill all form fields
        formPage.enterFirstName("Alice");
        formPage.enterLastName("Smith");
        formPage.enterEmail("alice.smith@example.com");
        formPage.enterPhone("9876543210");
        formPage.enterAge("28");
        formPage.selectCountry("canada");
        formPage.selectGender("female");
        formPage.selectInterest("music");
        formPage.selectInterest("travel");
        formPage.enterComments("Excited to join the community!");
        formPage.checkNewsletter();
        formPage.checkTerms();
        
        // Submit form
        formPage.clickSubmit();
        
        // Verify success
        Assert.assertTrue(formPage.isSuccessMessageDisplayed(), 
                         "Success message should be displayed");
        
        System.out.println("✓ Complete user journey test passed successfully");
    }
    
    @Test(priority = 12, description = "Test form with partial data")
    public void testFormWithPartialData() {
        System.out.println("Testing form with partial data...");
        
        // Fill only required fields
        formPage.enterFirstName("Test");
        formPage.enterLastName("User");
        formPage.enterEmail("test.user@example.com");
        formPage.checkTerms();
        
        // Submit form
        formPage.clickSubmit();
        
        // Verify success
        Assert.assertTrue(formPage.isSuccessMessageDisplayed(), 
                         "Success message should be displayed with minimal required data");
        
        System.out.println("✓ Form submission with partial data working correctly");
    }
}