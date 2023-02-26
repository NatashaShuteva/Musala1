import io.github.bonigarcia.wdm.WebDriverManager;
import io.ous.jtoml.impl.Token;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.Keys.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;



public class steps  {



    WebDriver driver;
    @Test
    public void userNaviagtesToUrl() throws InterruptedException, AWTException {
        configProperties.initialazePropertyFile();

    if(configProperties.property.getProperty("BrowserType").equalsIgnoreCase("Firefox"))
    {
             WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();
    }
      else if (configProperties.property.getProperty("BrowserType").equalsIgnoreCase("chrome"))
      {
            WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
      }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();


        //test case 1
        driver.navigate().to(configProperties.property.getProperty("AppURL"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)", "");
        WebElement clickContactbutton= driver.findElement(By.xpath("//span[@data-alt='Contact us']"));
        clickContactbutton.click();
        driver.findElement(By.xpath("//*[@id=\"cf-1\"]")).sendKeys("Natasha");
        driver.findElement(By.xpath("//*[@id=\"cf-3\"]")).sendKeys("078308710");
        driver.findElement(By.xpath("//*[@id=\"cf-4\"]")).sendKeys("Project Musala Software.");
        driver.findElement(By.xpath("//*[@id=\"cf-5\"]")).sendKeys("Please review my project.");
        driver.findElement(By.xpath("//*[@id=\"cf-2\"]")).sendKeys("test@test.");
        WebElement clickSendbutton= driver.findElement(By.xpath("//input[@class='wpcf7-form-control has-spinner wpcf7-submit btn-cf-submit']"));
        clickSendbutton.click();
        String expText="The e-mail address entered is invalid.";
        String actText=driver.findElement(By.xpath("//span[@class='wpcf7-not-valid-tip']")).getText();
        Assert.assertEquals(expText,actText);


        //testcase 2

        driver.navigate().to(configProperties.property.getProperty("AppURL"));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//a[@class='main-link'])[21]")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[@class='main-link'])[21]")));
        String expURL="https://www.musala.com/company/";
        WebDriverWait w = new WebDriverWait(driver,3);
        w.until(urlContains("https://www.musala.com/company/"));
        String actURL= driver.getCurrentUrl();
        System.out.println(actURL);
        Assert.assertEquals(expURL,actURL);
        Thread.sleep(5);
        WebDriverWait e = new WebDriverWait(driver,3);
        e.until(presenceOfElementLocated(By.xpath("/html/body/main/div/div/div[2]/section[3]/div/h2")));
        Boolean verifySection = driver.findElement(By.xpath("/html/body/main/div/div/div[2]/section[3]/div/h2")).isDisplayed();
        assertTrue(verifySection);
       Thread.sleep(3000L);
        WebElement clickAcceptbutton= driver.findElement(By.id("wt-cli-accept-all-btn"));
        clickAcceptbutton.click();
        WebDriverWait e2 = new WebDriverWait(driver,3);
        e2.until(presenceOfElementLocated(By.xpath("//span[@class='musala musala-icon-facebook']")));
        WebElement element = driver.findElement(By.xpath("//span[@class='musala musala-icon-facebook']"));
        ((RemoteWebDriver) driver).executeScript("arguments[0].click();", element);
        WebElement i = driver.findElement(new By.ByTagName("img"));
        // Javascript executor to check image
        Boolean p = (Boolean) ((JavascriptExecutor)driver) .executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", i);

        //verify if status is true
        if (p) {
            System.out.println("Musala Picture present");
        } else {
            System.out.println("Musala Picture not present");
        }

        //testcase 3
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.navigate().to(configProperties.property.getProperty("AppURL"));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//a[@class='main-link'])[25]")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[@class='main-link'])[25]")));
       // driver.findElement(By.xpath("//span[@data-alt='Check our open positions']")).click();
        Thread.sleep(3000L);
        WebDriverWait coop = new WebDriverWait(driver,3);
        coop.until(presenceOfElementLocated(By.xpath("//span[@data-alt='Check our open positions']"))).click();
        String expURLJA="https://www.musala.com/careers/join-us/";
        WebDriverWait joinas = new WebDriverWait(driver,3);
        joinas.until(urlContains("https://www.musala.com/careers/join-us/"));
        String actURLJA= driver.getCurrentUrl();
        System.out.println(actURLJA);
        Assert.assertEquals(expURLJA,actURLJA);
        Select se = new Select(driver.findElement(By.id("get_location")));
        se.selectByIndex(4);
        WebElement openpositioncard= driver.findElement(By.xpath("(//div[@class='card-container'])[1]"));
        openpositioncard.click();
        String gdesexp="General description";
        String gdesact= driver.findElement(By.xpath("(//div[@class='content-title'])[1]")).getText();
        System.out.println(gdesact);
        Assert.assertEquals(gdesexp,gdesact);
        String requexp="Requirements";
        String requact= driver.findElement(By.xpath("(//div[@class='content-title'])[2]")).getText();
        System.out.println(requact);
        Assert.assertEquals(requexp,requact);
        String respoexp="Responsibilities";
        String respoact= driver.findElement(By.xpath("(//div[@class='content-title'])[3]")).getText();
        System.out.println(respoact);
        Assert.assertEquals(respoexp,respoact);
        String whatofferexp="What we offer";
        String whatofferact= driver.findElement(By.xpath("(//div[@class='content-title'])[4]")).getText();
        System.out.println(whatofferact);
        Assert.assertEquals(whatofferexp,whatofferact);
        Boolean Display = driver.findElement(By.xpath("//input[@class='wpcf7-form-control wpcf7-submit btn-join-us btn-apply']")).isDisplayed();
        System.out.println("Element displayed is :"+Display);
        WebElement clickApplybutton= driver.findElement(By.xpath("//input[@class='wpcf7-form-control wpcf7-submit btn-join-us btn-apply']"));
        clickApplybutton.click();
        driver.findElement(By.id("cf-1")).sendKeys("Natasha");
        driver.findElement(By.id("cf-2")).sendKeys("test@test");

        WebDriverWait wait11 = new WebDriverWait(driver, 30);
        wait11.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='wpcf7-form-control-wrap'])[4]")));
        driver.findElement(By.xpath("(//span[@class='wpcf7-form-control-wrap'])[4]")).click();
        Thread.sleep(5000L);
        Robot robots = new Robot();
        String arr[] = { "C:\\Users\\User\\Desktop\\CV Natasha Shuteva.docx" };
        String Arr_Final = arr[0];
        for (int n = 1; n < arr.length; n++) {
            Arr_Final += arr[n];
        }
        StringSelection sss = new StringSelection(Arr_Final);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sss, null);

        Thread.sleep(3000L);
        robots.keyPress(KeyEvent.VK_CONTROL);
        robots.keyPress(KeyEvent.VK_V);
        robots.keyRelease(KeyEvent.VK_V);
        robots.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(3000L);
        // robots.delay(3000);
        robots.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(3000L);
        robots.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(3000L);

        WebElement clickSendbutton2= driver.findElement(By.xpath("//input[@class='wpcf7-form-control has-spinner wpcf7-submit btn-cf-submit']"));
        clickSendbutton2.click();
        String invaliedemailexp="The e-mail address entered is invalid.";
        String invailidemailact= driver.findElement(By.xpath("(//span[@class='wpcf7-not-valid-tip'])[1]")).getText();
        System.out.println(invailidemailact);
        Assert.assertEquals(invaliedemailexp,invailidemailact);
        String requredfiledexp="The field is required.";
        String requiredfieldact= driver.findElement(By.xpath("(//span[@class='wpcf7-not-valid-tip'])[2]")).getText();
        System.out.println(requiredfieldact);
        Assert.assertEquals(requredfiledexp,requiredfieldact);

        //testcase4
      driver.navigate().to(configProperties.property.getProperty("AppURL"));
      js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//a[@class='main-link'])[25]")));
      js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[@class='main-link'])[25]")));
      WebDriverWait coop2 = new WebDriverWait(driver,3);
      coop2.until(presenceOfElementLocated(By.xpath("//span[@data-alt='Check our open positions']"))).click();
      Select se2 = new Select(driver.findElement(By.id("get_location")));
      se2.selectByIndex(2);
        Select dropDown = new Select(driver.findElement(By.id("get_location")));
        List<WebElement> e3 = dropDown.getOptions();
       // int itemCount = e3.size();
        System.out.println(e3.get(2).getText());
       // for(int l = 0; l < itemCount; l++)
       // {
       //     System.out.println(e3.get(2).getText());
       // }

        WebElement table= driver.findElement(By.xpath("//section[@class='join-us']"));
        {
            List<WebElement> cardtitle = (List<WebElement>) table.findElements(By.xpath("//h2[@data-alt]"));
            int itemCount = cardtitle.size();
            for (int l = 0; l < itemCount; l++) {
                System.out.println("Position:" + cardtitle.get(l).getText());
                List<WebElement> moreinfo = (List<WebElement>) table.findElements(By.xpath("//a[@class='card-jobsHot__link']"));
                //  int itemCount2 = moreinfo.size();
                //  for (int l2 = 0; l2 < itemCount2; l2++) {
                System.out.println("More Info:" + moreinfo.get(l).getAttribute("href"));
            }
      //  }
        }
        Select se3 = new Select(driver.findElement(By.id("get_location")));
        se3.selectByIndex(8);
        Select dropDown3 = new Select(driver.findElement(By.id("get_location")));
        List<WebElement> e4 = dropDown3.getOptions();
        System.out.println(e4.get(8).getText());

        WebElement table2= driver.findElement(By.xpath("//section[@class='join-us']"));
        {
            List<WebElement> cardtitle2 = (List<WebElement>) table2.findElements(By.xpath("//h2[@data-alt]"));
            int itemCount2 = cardtitle2.size();
            for (int m = 0; m < itemCount2; m++) {
                System.out.println("Position:" + cardtitle2.get(m).getText());
                List<WebElement> moreinfo = (List<WebElement>) table2.findElements(By.xpath("//a[@class='card-jobsHot__link']"));
                System.out.println("More Info:" + moreinfo.get(m).getAttribute("href"));
            }

        }
       }

      }
