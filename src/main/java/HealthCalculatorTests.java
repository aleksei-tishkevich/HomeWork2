import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HealthCalculatorTests {

    private WebDriver driver;

    @BeforeMethod
    public void openWebsite() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver 5");
        driver = new ChromeDriver();
        driver.get("https://beregifiguru.ru/%D0%9A%D0%B0%D0%BB%D1%8C%D0%BA%D1%83%D0%BB%D1%8F%D1%82%D0%BE%D1%80%D1%8B/%D0%A0%D0%B0%D1%81%D1%87%D0%B5%D1%82-%D0%BD%D0%BE%D1%80%D0%BC%D0%B0%D0%BB%D1%8C%D0%BD%D0%BE%D0%B3%D0%BE-%D0%B2%D0%B5%D1%81%D0%B0");
        Thread.sleep(3000);
        //Here test open a tested website and wait 3 second
    }

    @Test
    public void checkTitle() {
        WebElement title = driver.findElement(By.xpath("/html/head/title"));
        String actualTitle = title.getAttribute("innerText");
        Assert.assertTrue(actualTitle.contains("как определить нормальный вес"));
        //This test checked the title of the opened page.
    }

    @Test
    public void buttonStartIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='calcFormId']/button")).isDisplayed());
        //This test checks if the button is displayed on the open page
    }

    @Test
    public void checkboxGenderFemaleIsSelected() {
        WebElement startButton = driver.findElement(By.xpath("//*[@id='calcFormId']/button"));
        startButton.click();
        WebElement checkboxFemale = driver.findElement(By.xpath("//*[@id='chf']"));
        Assert.assertTrue(checkboxFemale.isSelected());
        //This test checked if the gender on the page is "Female" by default
    }

    @Test
    public void fieldWeightIsDisplayed() {
        WebElement startButton = driver.findElement(By.xpath("//*[@id='calcFormId']/button"));
        startButton.click();
        WebElement fieldWeight = driver.findElement(By.xpath("//*[@id='Weight']"));
        Assert.assertTrue(fieldWeight.isDisplayed());
        //This test checked if field "Weight" is display
    }

    @Test
    public void messageAboutEmptyFieldIsDisplay() throws InterruptedException {
        WebElement startButton = driver.findElement(By.xpath("//*[@id='calcFormId']/button"));
        startButton.click();
        Thread.sleep(3000);
        WebElement buttonCalk = driver.findElement(By.xpath("//*/tbody/tr[6]/th/button"));
        buttonCalk.click();
        WebElement messageAboutMistake = driver.findElement(By.xpath("//*[@id='Weight-error']"));
        String actualResult = messageAboutMistake.getText();
        String expectedResult = "Введите значение";
        Assert.assertEquals(actualResult, expectedResult);
        //This test verifies that the hint about the empty input field consists of certain text
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
