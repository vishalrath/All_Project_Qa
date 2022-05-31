package com.bridgelabz.keyword.base;

import com.bridgelabz.keyword.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    //read the properties file
    public Properties properties;

    /**
     * @param browserName
     * @return driver
     */

    public WebDriver initializeDriver(String browserName) {
        if(browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vishal\\Downloads\\chromedriver_win32\\chromedriver.exe");
            if(properties.getProperty("headless").equals("yes")) {
                //headless mode:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }else{
                driver= new ChromeDriver();
            }
        }
        return driver;
    }

    public Properties initializeProperties() {
        properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("C:\\Users\\Vishal\\IdeaProjects\\KeywordDriven\\src\\" +
                    "main\\java\\com\\bridgelabz\\keyworddriven\\config\\config.Properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
