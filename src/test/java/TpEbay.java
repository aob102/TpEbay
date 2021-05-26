import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TpEbay {

    WebDriver driver;

    @BeforeMethod
    public void ouvrirChrome() {
        driver = new ChromeDriver();
        driver.get("https://www.ebay.fr/");
        driver.manage().window().maximize();


    }

    @AfterMethod
    public void fermerChrome() {
        driver.quit();
    }

    @Test
    public void testEbay() {
        // Arrange

        String msgAttendu = "Nouveau Apple iPhone 12 Pro Max (128 Go) - Bleu Pacifique";
        String prixAttendu = "249,00";
        WebDriverWait wait = new WebDriverWait(driver, 2);
        // Act
        By cat = By.id("gh-shop-ei");
       driver.findElement(cat).click();
       wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[_sp='m570.l3778']")));
       driver.findElement(By.cssSelector("[_sp='m570.l3778']")).click();
        WebElement objet=driver.findElement(By.xpath("//*[@id='s0-27_2-9-0-1[0]-0-0-xCarousel-x-carousel-items']//li[1]"));
        String nomAttendu= objet.getText();
        objet.click();

        driver.findElement(By.id("atcWrapperId")).click(); ;
        WebElement dplist = driver.findElement(By.cssSelector("[data-test-id='page-alerts']"));
        Assert.assertTrue(dplist.isDisplayed(), msgAttendu);
        driver.findElement(By.cssSelector("[data-test-id='cart-item-link']")).click();
        WebElement elementAjout= driver.findElement(By.cssSelector("#itemTitle"));
        String nomObtenu= elementAjout.getText();
        Assert.assertTrue(nomAttendu.contains(nomObtenu));
        WebElement dp = driver.findElement(By.cssSelector("#qtyTextBox"));
        Assert.assertTrue(dp.isDisplayed(), "1");








        // Asserts

    }
}
