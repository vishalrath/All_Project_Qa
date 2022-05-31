package com.bridgelabz.keyword.engine;

import com.bridgelabz.keyword.base.Base;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**

 * @purpose taking data from xlsx file and perform action
 */
public class KeywordEngine {

    public WebDriver driver;
    public Properties properties;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    public WebElement element;

    public final String SCENARIO_SHEET_PATH = "C:\\Users\\Vishal\\IdeaProjects\\KeywordDriven\\src\\" +
            "main\\java\\com\\bridgelabz\\scenarioes\\HubSpot.xlsx";

    public void startExecution(String sheetName) {



        FileInputStream file = null;
        try {
            file = new FileInputStream((SCENARIO_SHEET_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);
        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {
//                String locatorColumneValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim(); //id=username
//                if (!locatorColumneValue.equalsIgnoreCase("NA")) {
//                    locatorName = locatorColumneValue.split("=")[0].trim(); //id
//                    locatorValue = locatorColumneValue.split("=")[1].trim(); //username
//                }
//
//                String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
//                String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

                String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

                switch (action) {
                    case "open browser":
                        base = new Base();
                        properties = base.initializeProperties();
                        if (value.isEmpty() || value.equals("NA")) {
                            driver = base.initializeDriver(properties.getProperty("browser"));
                        } else {
                            driver = base.initializeDriver(value);
                        }
                        break;

                    case "enter url":
                        if (value.isEmpty() || value.equals("NA")) {
                            driver.get(properties.getProperty("url"));
                        } else {
                            driver.get(value);
                        }
                        break;

                    case "quit":
                        driver.quit();
                        break;

                    default:
                        break;
                }

                switch (locatorType) {
                    case "id":
                        element =  driver.findElement(By.id(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                            Thread.sleep(1000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();

                        }
                        else  if(action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }else if(action.equalsIgnoreCase("getText")){
                            String elementText= element.getText();
                            System.out.println("text from elements :"+elementText);
                        }
                        locatorType = null;
                        break;


                    case "xpath":
                        element =  driver.findElement(By.xpath(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                            Thread.sleep(1000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        else  if(action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }
                        else if(action.equalsIgnoreCase("getText")){
                            String elementText= element.getText();
                            System.out.println("text from elements :"+elementText);
                        }
                        locatorType = null;
                        break;

                    case "className":
                        element =  driver.findElement(By.className(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                            Thread.sleep(1000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        else  if(action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }
                        else if(action.equalsIgnoreCase("getText")){
                           String elementText= element.getText();
                           System.out.println("text from elements :"+elementText);
                        }
                        locatorType = null;
                        break;

                    case "name":
                        element =  driver.findElement(By.name(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                            Thread.sleep(1000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        else  if(action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }
                        else if(action.equalsIgnoreCase("getText")){
                            String elementText= element.getText();
                            System.out.println("text from elements :"+elementText);
                        }
                        locatorType = null;
                        break;

                    case "cssSelector":
                        element =  driver.findElement(By.cssSelector(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                            Thread.sleep(1000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        else  if(action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }
                        else if(action.equalsIgnoreCase("getText")){
                            String elementText= element.getText();
                            System.out.println("text from elements :"+elementText);
                        }
                        locatorType = null;
                        break;

                    case "linkText":
                        element = driver.findElement(By.linkText(locatorValue));
                        element.click();
                        locatorType = null;
                        break;

                    case "partialLinkText":
                        element = driver.findElement(By.partialLinkText(locatorValue));
                        element.click();
                        locatorType = null;
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
            }
        }
    }
}