package base;

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
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        String userDataDir = System.getProperty("java.io.tmpdir") + "/chrome_user_data_" + System.currentTimeMillis();
        options.addArguments("--user-data-dir=" + userDataDir);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        // Add headless mode for CI environments
        if (System.getProperty("headless", "false").equals("true")) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
        }
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        driver.get(FORM_URL);
    }

    }

