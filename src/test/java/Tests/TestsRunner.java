package Tests;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import services.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestsRunner {
    private WebDriver driver;
    private static final String URL = "https://passport.yandex.by";
    private static final String PAGE_TITLE = "Яндекс";
    private LaunchAndLogIn launchAndLogIn;
    private NewLetter newLetter;
    private SendDraftMail sendDraftMail;
    private DeleteLetter deleteLetter;
    private LogOut logOut;
    private Finish finish;
    private static final String RECEIVER = "fake@gmale.com";
    private static final String TOPIC = String.format("AT-WD-№-%s", new Random().nextInt(100));
    private static final String TEXT = "SOME TEXT FOR LETTER";
    public static final int IMPLICIT_WAIT = 80;


    @BeforeClass
    @Parameters({"browserName"})
    public void startBrowser(String browserName) {
        DriverFactory factory = new DriverFactory();
        driver = factory.createDriver(browserName);
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    private void login() {

        launchAndLogIn = new LaunchAndLogIn(driver);


        //newLetter = launchAndLogIn.startAndLogIn();
        deleteLetter = launchAndLogIn.startAndLogIn();
        // Assert.assertTrue(launchAndLogIn.userAccount());
    }

    //   @Test(parameters = "chrome")
    private void newLetterSavedInDraft() {

        sendDraftMail = newLetter.newLetter(RECEIVER, TOPIC, TEXT);
        Assert.assertTrue(newLetter.findDraft());
        System.out.println("TOPIC " + TOPIC);
    }

    //   @Test
    private void verifyAndSendDraft() {
        Assert.assertTrue(newLetter.verifyDraftIsCorrect(RECEIVER, TOPIC, TEXT));
    }

    //  @Test(dependsOnMethods = "verifyAndSendDraft", alwaysRun = true)
    private void checkDraftFolder() {
        deleteLetter = sendDraftMail.checkDraftLettersFolder(TOPIC);
        // Assert.assertFalse(sendDraftMail.checkFolders(TOPIC));
    }

    //    @Test(dependsOnMethods = "checkDraftFolder", alwaysRun = true)
    private void checkSentLetterFolder() {
        deleteLetter = sendDraftMail.checkSentLettersFolder(TOPIC);
        // Assert.assertFalse(sendDraftMail.checkFolders(TOPIC));
    }


    @Test(dependsOnMethods = "login")
//(dependsOnMethods = "checkSentLetterFolder", alwaysRun = true)
    private void delete() throws InterruptedException {
        logOut = deleteLetter.checkDraftLettersFolder(TOPIC);
    }

    @Test(dependsOnMethods = "delete", alwaysRun = true)
    private void logingOut() {
        finish = logOut.logOut();
        Assert.assertEquals(logOut.getStartPageTitel(), PAGE_TITLE);
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.killDriver();
    }
}
