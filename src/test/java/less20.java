import io.github.bonigarcia.wdm.WebDriverManager;
import org.DariaRyabinina.CodePage;
import org.DariaRyabinina.LoginPage;
import org.DariaRyabinina.ReviewPage;
import org.DariaRyabinina.TabMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class less20 {
    private WebDriver webDriver;
    public static final Logger LOGG = LoggerFactory.getLogger(less20.class);

    @BeforeClass
    public void downloadDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
    }

     @AfterMethod
      public void closeDriver() {
          webDriver.close();
      }

    @Test
    public void Test1() {
        webDriver.get("https://idemo.bspb.ru/");
        webDriver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.longin("demo", "demo");

        CodePage codePage = new CodePage(webDriver);
        codePage.enterCode("0000");
        codePage.clickEnterButton();

        TabMenu tabMenu = new TabMenu(webDriver);
        tabMenu.clickButtonId("bank-overview");

        ReviewPage reviewPage = new ReviewPage(webDriver);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 60);
        webDriverWait.until(ExpectedConditions.invisibilityOf(reviewPage.nameReview(1)));
        String nameReview = reviewPage.nameReview(1).getText();
        nameReview = nameReview.replaceAll("[^(а-яёА-ЯЁ)]", "");
        Assert.assertEquals(nameReview, "Обзор");
        Assert.assertEquals(reviewPage.financialfreedom(1).getText(), "Финансовая свобода");
        String sumMony = reviewPage.webColumnMoney().getText().trim();
        LOGG.info(sumMony);
        boolean mach = sumMony.matches("\\d{0,3}\\s\\d{0,3}\\s\\d{0,3}\\.\\d{2}\\s.");
        Assert.assertTrue(mach);


        Actions action = new Actions(webDriver);
        action.moveToElement(reviewPage.webColumnMoney()).build().perform();

        String myMoney = reviewPage.webColumnMyMoney().getText();
        myMoney = myMoney.replaceAll("[^(а-яёА-ЯЁ), ]", "").trim();
        Assert.assertEquals(myMoney, "Моих средств");
        String mySumMony = reviewPage.webColumnMyMoney().getText().replaceAll("Моих средств", "").trim();
        LOGG.info(mySumMony);
        mach = mySumMony.matches("\\d{0,3}\\s\\d{0,3}\\s\\d{0,3}\\.\\d{2}\\s.");
        Assert.assertTrue(mach);


    }
}
