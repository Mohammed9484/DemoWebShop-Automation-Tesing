package DemoWebShop.TestComponents;

import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import DemoWebShop.PageObjects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public class BaseTest {
    public HomePage homePage;
    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/DemoWebShop/Resources/GlobalData.properties");

        prop.load(file);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        //String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeSuite
    public void cleanAllureResults() {
        File allureResultsDir = new File("test-outputs/allure-results");
        if (allureResultsDir.exists()) {
            for (File file : Objects.requireNonNull(allureResultsDir.listFiles())) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        }
    }

    @Step("Launching ")
    @BeforeMethod(alwaysRun = true)
    public HomePage launchBrowser() throws IOException {

        Allure.step("Launching the browser");
        driver = initializeDriver();
        homePage = new HomePage(driver);
        homePage.goTo();
        return homePage;

    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        Allure.step("Closing the browser");
        driver.close();

    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;

    }

    public String getScreenshot(String testCaseName, WebDriver driver, String suiteName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "/reports/" + suiteName + "_Bug_ScreenShot/" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "/reports/" + suiteName + "_Bug_ScreenShot/" + testCaseName + ".png";
    }


}
