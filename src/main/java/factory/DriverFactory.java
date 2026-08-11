package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {


    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initializeDriver() {
        if (DRIVER.get() != null) {
            return;
        }

        String browser = ConfigReader.getProperty("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        WebDriver webDriver = switch (browser) {
            case "edge" -> new EdgeDriver(edgeOptions(headless));
            case "firefox" -> new FirefoxDriver(firefoxOptions(headless));
            case "chrome" -> new ChromeDriver(chromeOptions(headless));
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

//        webDriver.manage().timeouts()
//                .implicitlyWait(Duration.ofSeconds(ConfigReader.getPropertyAsNumber("implicit.wait.seconds")));
        webDriver.manage().window().maximize();
        DRIVER.set(webDriver);
    }

    public static WebDriver getDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver was not initialized. Check Cucumber hooks.");
        }
        return webDriver;
    }

    public static void quitDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver != null) {
            webDriver.quit();
            DRIVER.remove();
        }
    }

    private static ChromeOptions chromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-notifications", "--remote-allow-origins=*");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    private static EdgeOptions edgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-notifications");
        return options;
    }

    private static FirefoxOptions firefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        return options;
    }

}
