package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;
    private String testEmail = "test+" + System.currentTimeMillis() + "@mailnesia.com";

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2500);
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on basketballengland.co.uk on {string}")
    public void iAmOnBasketballenglandCoUkOn(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                System.out.println("Chrome driver loaded");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                System.out.println("Firefox driver loaded");
                break;
            case "edge":
                driver = new EdgeDriver();
                System.out.println("Edge driver loaded");
                break;
            default:
                System.out.println("Unknown browser\n loading Chrome driver");
                driver = new ChromeDriver();
                break;
            //throw new IllegalArgumentException("Okänd webbläsare: " + browser);
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @Then("I fail to become a new member because {string}")
    public void iFailToBecomeANewMemberBecause(String expectedFailureMessage) {
        WebElement element = driver.findElement(By.cssSelector("span[generated=\"true\"]"));
        String actual = element.getText();
        assertEquals(expectedFailureMessage, actual);
        System.out.println("Jag blev inte en medlem för att: " + actual);
    }

    @And("I fill in email and confirm email")
    public void iFillInEmailAndConfirmEmail() {
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(testEmail);
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(testEmail);
    }

    @And("I check I am over 18")
    public void iCheckIAmOver() {
        //driver.findElement(By.cssSelector("label[for='sign_up_26'] span[class='box']")).click(); //I am aged over 18
        driver.findElement(By.cssSelector("label[for='sign_up_26']")).click(); //I am aged over 18
        System.out.println("I am over 18");
    }

    @And("I check I have read Code of Conduct")
    public void iCheckIHaveReadCodeOfConduct() {
        //driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] span[class='box']")).click(); //I have read, Understood Code of conduct
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']")).click(); //I have read, Understood Code of conduct
        System.out.println("I have read code of conduct");
    }

    @And("I press Confirm and join")
    public void iPressConfirmAndJoin() {
        driver.findElement(By.cssSelector(".btn")).click(); //Confirm and Join > Hoppa till ny sida
        System.out.println("I press Confirm and join");
    }

    @Then("I successfully become a member")
    public void iSuccessfullyBecomeAMember() {
        waitToBeDisplayed("h2.gray");
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        String actual = driver.findElement(By.cssSelector("h2.gray")).getText();
        System.out.println(actual);
        assertEquals(expected, actual);
        System.out.println("DET GICK!!!");
        //System.out.println(driver.findElement(By.cssSelector("div.portlet")).getText()); "We've sent you an email to verify your email address (check your junk/spam folder).
        //Please make a note of your Membership Number, you will need this to log in.
        //Your Basketball England Membership Number is:
        //A128323
        //GO TO MY LOCKER"
    }

    @When("I fill in date of birth {string}")
    public void iFillInDateOfBirth(String dateOfBirth) {
        driver.findElement(By.cssSelector("input#dp")).sendKeys(dateOfBirth); //Date of Birth DD/MM/YYYY
        driver.findElement(By.cssSelector("#titleText1")).click();
    }

    @And("I fill in first name {string}")
    public void iFillInFirstName(String firstName) {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys(firstName);
    }

    @And("I fill in last name {string}")
    public void iFillInLastName(String lastName) {
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys(lastName);
    }

    @And("I fill in password and confirm password {string}")
    public void iFillInPasswordAndConfirmPassword(String password) {
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys(password);
    }

    @And("I fill in wrong confirmed password")
    public void iFillInWrongConfirmedPassword() {
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys("wrongpassword");
    }

    @And("I fill in password {string}")
    public void iFillInPassword(String password) {
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(password);
    }

    @And("I fill in confirmed password {string}")
    public void iFillInConfirmedPassword(String confirmPassword) {
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys(confirmPassword);
        driver.findElement(By.cssSelector("#titleText1")).click(); //fixar så att terms blir i klickad korrekt
    }

    @And("I check I have read Terms and Conditions {string}")
    public void iCheckIHaveReadTermsAndConditions(String terms) {
        waitToBeDisplayedAndClickable("label[for='sign_up_25'] .box");
        WebElement termsAndConditonsBox = driver.findElement(By.cssSelector("label[for='sign_up_25'] .box")); //I have read, understood Terms and Conditions

        if (terms.trim().equalsIgnoreCase("true")) {
            termsAndConditonsBox.isSelected();
            termsAndConditonsBox.click();
            System.out.println("I have read and understood Terms and Conditions");
        }
    }

    private void waitToBeDisplayed(String css) {
        System.out.println("Waiting for " + css+ "to be displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        System.out.println(css+" is now displayed");
    }

    private void waitToBeDisplayedAndClickable(String css) {
        System.out.println("Waiting for " + css + " to be displayed and clickable");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
    }
}
