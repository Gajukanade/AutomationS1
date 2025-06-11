package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class FormPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Form input elements
    @FindBy(id = "firstName")
    private WebElement firstNameField;
    
    @FindBy(id = "lastName")
    private WebElement lastNameField;
    
    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "phone")
    private WebElement phoneField;
    
    @FindBy(id = "age")
    private WebElement ageField;
    
    @FindBy(id = "country")
    private WebElement countryDropdown;
    
    // Gender radio buttons
    @FindBy(id = "male")
    private WebElement maleRadio;
    
    @FindBy(id = "female")
    private WebElement femaleRadio;
    
    @FindBy(id = "other")
    private WebElement otherRadio;
    
    // Interest checkboxes
    @FindBy(id = "sports")
    private WebElement sportsCheckbox;
    
    @FindBy(id = "music")
    private WebElement musicCheckbox;
    
    @FindBy(id = "travel")
    private WebElement travelCheckbox;
    
    @FindBy(id = "technology")
    private WebElement technologyCheckbox;
    
    // Other elements
    @FindBy(id = "comments")
    private WebElement commentsField;
    
    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;
    
    @FindBy(id = "terms")
    private WebElement termsCheckbox;
    
    @FindBy(id = "submitBtn")
    private WebElement submitButton;
    
    @FindBy(id = "resetBtn")
    private WebElement resetButton;
    
    @FindBy(id = "successMessage")
    private WebElement successMessage;
    
    // Constructor
    public FormPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    
    // Actions
    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    
    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    
    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }
    
    public void enterPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
    }
    
    public void enterAge(String age) {
        ageField.clear();
        ageField.sendKeys(age);
    }
    
    public void selectCountry(String country) {
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByValue(country);
    }
    
    public void selectGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                maleRadio.click();
                break;
            case "female":
                femaleRadio.click();
                break;
            case "other":
                otherRadio.click();
                break;
        }
    }
    
    public void selectInterest(String interest) {
        switch (interest.toLowerCase()) {
            case "sports":
                sportsCheckbox.click();
                break;
            case "music":
                musicCheckbox.click();
                break;
            case "travel":
                travelCheckbox.click();
                break;
            case "technology":
                technologyCheckbox.click();
                break;
        }
    }
    
    public void enterComments(String comments) {
        commentsField.clear();
        commentsField.sendKeys(comments);
    }
    
    public void checkNewsletter() {
        if (!newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
    }
    
    public void checkTerms() {
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }
    
    public void clickSubmit() {
        submitButton.click();
    }
    
    public void clickReset() {
        resetButton.click();
    }
    
    // Validation methods
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getSuccessMessageText() {
        return successMessage.getText();
    }
    
    public boolean hasErrorClass(WebElement element) {
        return element.getAttribute("class").contains("error");
    }
    
    public boolean isFieldEmpty(WebElement field) {
        return field.getAttribute("value").isEmpty();
    }
    
    public boolean isRadioSelected(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                return maleRadio.isSelected();
            case "female":
                return femaleRadio.isSelected();
            case "other":
                return otherRadio.isSelected();
            default:
                return false;
        }
    }
    
    public boolean isCheckboxSelected(String interest) {
        switch (interest.toLowerCase()) {
            case "sports":
                return sportsCheckbox.isSelected();
            case "music":
                return musicCheckbox.isSelected();
            case "travel":
                return travelCheckbox.isSelected();
            case "technology":
                return technologyCheckbox.isSelected();
            case "newsletter":
                return newsletterCheckbox.isSelected();
            case "terms":
                return termsCheckbox.isSelected();
            default:
                return false;
        }
    }
    
    public String getSelectedCountry() {
        Select countrySelect = new Select(countryDropdown);
        return countrySelect.getFirstSelectedOption().getAttribute("value");
    }
    
    // Utility method to fill form with valid data
    public void fillFormWithValidData() {
        enterFirstName("John");
        enterLastName("Doe");
        enterEmail("john.doe@example.com");
        enterPhone("1234567890");
        enterAge("30");
        selectCountry("usa");
        selectGender("male");
        selectInterest("sports");
        selectInterest("technology");
        enterComments("This is a test comment for automation.");
        checkNewsletter();
        checkTerms();
    }
}