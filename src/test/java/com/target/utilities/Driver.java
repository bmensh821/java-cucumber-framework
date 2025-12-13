package com.target.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

     private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    private Driver() {
        // Prevent instantiation
	}

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.setCapability("webSocketUrl", true); // Enable BiDi
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }

            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driverPool.get();
    }
	public static void closeDriver() {
		if (driverPool.get() != null) {
			driverPool.get().quit();
			driverPool.remove();
		}
	}
	}
