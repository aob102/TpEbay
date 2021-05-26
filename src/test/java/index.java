import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class index {

    WebDriver driver;

    @BeforeMethod
    public void ouvrirChrome() {
        driver = new ChromeDriver();
        driver.get("https://www.amazon.fr/");
        driver.manage().window().maximize();

        By cookiesSelector = By.name("accept");
        driver.findElement(cookiesSelector).click();
    }

    @AfterMethod
    public void fermerChrome() {
        driver.quit();
    }

    @Test
    public void testAmazon() {
        // Arrange
        String recherche = "iPhone 12 pro max";
        String nomAttendu = "Nouveau Apple iPhone 12 Pro Max (128 Go) - Bleu Pacifique";
        String prixAttendu = "249,00";

        // Act
        By barreRechercheSelector = By.id("twotabsearchtextbox");
        driver.findElement(barreRechercheSelector).sendKeys(recherche + Keys.ENTER);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        By nomSelector = By.cssSelector("[data-cel-widget='search_result_1'] .a-text-normal.a-color-base");
        WebElement nomLabel = driver.findElement(nomSelector);

        By prixSelector = By.cssSelector("[data-cel-widget='search_result_1'] .a-price-whole");
        String prixLabel = driver.findElement(prixSelector).getText();

        // Asserts
        Assert.assertEquals(nomLabel.getText(), nomAttendu);

        Assert.assertTrue(prixLabel.contains(prixAttendu), "Le prix reel [" + prixLabel + "] ne contient pas la chaine [" + prixAttendu + "].");
    }
}
