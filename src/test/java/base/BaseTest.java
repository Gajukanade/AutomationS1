package base;

//package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final String FORM_URL = "https://gajukanade.github.io/AutomationS1/"; // Update this path
    
    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();

     // Headless for CI
     options.addArguments("--headless=new");
     options.addArguments("--no-sandbox");
     options.addArguments("--disable-dev-shm-usage");
     options.addArguments("--disable-extensions");
     options.addArguments("--remote-allow-origins=*");
     options.addArguments("--disable-gpu");

     // 🔥 THIS LINE CAUSES THE ISSUE (REMOVE IT IF PRESENT)
     // options.addArguments("--user-data-dir=...");  ❌

     // Add ChromeDriver
     WebDriver driver = new ChromeDriver(options);



        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // Navigate to the form
        driver.get(FORM_URL);
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}