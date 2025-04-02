package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;


    @Given("I am on basketballengland.co.uk")
    public void iAmOnBasketballenglandCoUk() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I fill in the correct member details")
    public void iFillInTheCorrectMemberDetails() {
        String testEmail = "test4@gmail.com"; // Rewrite to a dynamic email
        String testPassword = "password";
        driver.findElement(By.cssSelector("input#dp")).sendKeys("07/01/1999"); //Date of Birth DD/MM/YYYY
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Urban"); //First Name
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("Test"); //Last name
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(testEmail); //email
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(testEmail); //confirm email
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(testPassword); //Password
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys(testPassword); //Confirm password
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redbox")));
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inc"))));
        driver.findElement(By.cssSelector("label[for='sign_up_25'] span[class='box']")).click(); //I have read, understood Terms and Conditions
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inc"))));
        driver.findElement(By.cssSelector("label[for='sign_up_26'] span[class='box']")).click(); //I am aged over 18
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("label[for='sign_up_26'] span.check"))));
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] span[class='box']")).click(); //I have read, Understood Code of conduct
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inc"))));
        driver.findElement(By.cssSelector(".btn")).click();
        //input[value='CONFIRM AND JOIN']
        //label[for='sign_up_26'] span[class='check']
        //label[for='sign_up_26'] span.check
    }

    //    @Then("I successfully become a member")
//    public void iSuccessfullyBecomeAMember() {
//        assertEquals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND", driver.findElement(By.cssSelector("h2.gray")).getText());
//    }
    @Then("I successfully become a member")
    public void iSuccessfullyBecomeAMember() {
        //wait.until(ExpectedConditions.visi)
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        String actual = driver.findElement(By.cssSelector("h2.gray")).getText();
        assertEquals(expected, actual);
        System.out.println("DET GICK!!!");
    }


    @When("I remove {string}")
    public void iRemove(String elementTobeCleared) {
        driver.findElement(By.cssSelector("input#member_" + elementTobeCleared)).clear(); //Last name

    }

    @Then("I fail to become a new member")
    public void iFailToBecomeANewMember() {
    }

    @Given("I am on basketballengland.co.uk on {}")
    public void iAmOnBasketballenglandCoUkOn(String arg0) {
    }
}
