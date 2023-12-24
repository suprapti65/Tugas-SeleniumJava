package tugas.prapti;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class BasicWebAutomation {

    WebDriver driver;

    @Test
    public void loginTest() {
        //Buka Yopmail.com
        //Search email “automationtest”
        //Extract content di email paling pertama menggunakan switch iframe

        WebDriverManager.chromedriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://yopmail.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Search email “automationtest”
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login']")));
        driver.findElement(By.id("login")).sendKeys("automationtest");
        driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();

        // Switch to the first iframe
        WebElement iframeElement1 = driver.findElement(By.xpath("//iframe[@id='ifinbox']"));
        driver.switchTo().frame(iframeElement1);

        // Interaction within the first iframe
        By firstIframeButton = By.xpath("//div[@class='mctn']/div[2]/button[@class='lm']");
        wait.until(ExpectedConditions.elementToBeClickable(firstIframeButton));
        driver.findElement(firstIframeButton).click();

        // Switch back to the default content before switching to the second iframe
        driver.switchTo().defaultContent();

        // Switch to the second iframe
        WebElement iframeElement2 = driver.findElement(By.xpath("//iframe[@id='ifmail']"));
        driver.switchTo().frame(iframeElement2);

        // Interaction within the second iframe
        By secondIframeButton = By.cssSelector("header div:nth-of-type(2) > .material-icons-outlined");
        wait.until(ExpectedConditions.elementToBeClickable(secondIframeButton));
        driver.findElement(secondIframeButton).click();

        //close browser
        driver.quit();
    }

}
