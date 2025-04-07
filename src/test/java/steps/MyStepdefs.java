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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;
    String testEmail = "test+" + System.currentTimeMillis() + "@mailnesia.com"; // Rewrite to a dynamic email
    String testPassword = "password";


    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am on basketballengland.co.uk")
    public void iAmOnBasketballenglandCoUk() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I fill in the correct member details")
    public void iFillInTheCorrectMemberDetails() {
        String testEmail = "test+" + System.currentTimeMillis() + "@mailnesia.com"; // Rewrite to a dynamic email
        System.out.println(testEmail);
        String testPassword = "password";
        driver.findElement(By.cssSelector("input#dp")).sendKeys("07/01/1999"); //Date of Birth DD/MM/YYYY
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Urban"); //First Name
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("Test"); //Last name
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(testEmail); //email
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(testEmail); //confirm email
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(testPassword); //Password
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys(testPassword); //Confirm password

        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inc"))));
        driver.findElement(By.cssSelector("label[for='sign_up_25'] span[class='box']")).click(); //I have read, understood Terms and Conditions
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inc"))));
        System.out.println("I have read and understood Terms and Conditions");
        driver.findElement(By.cssSelector("label[for='sign_up_26'] span[class='box']")).click(); //I am aged over 18
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("label[for='sign_up_26'] span.inc"))));
        System.out.println("I am over 18");
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] span[class='box']")).click(); //I have read, Understood Code of conduct
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[for='fanmembersignup_agreetocodeofethicsandconduct'] .inc "))));
        System.out.println("I have read code of conduct");
    }


    @Then("I fail to become a new member because {string}")
    public void iFailToBecomeANewMemberBecause(String arg0) {
    }

    @When("I fill in date of birth")
    public void iFillInDateOfBirth() {
        driver.findElement(By.cssSelector("input#dp")).sendKeys("07/01/1999"); //Date of Birth DD/MM/YYYY

    }

    @And("I fill in first name")
    public void iFillInFirstName() {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Urban"); //First Name

    }
    @And("I fill in last name")
    public void iFillInLastName() {
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("Test"); //Last name

    }

    @And("I fill in email and confirm email")
    public void iFillInEmailAndConfirmEmail() {
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(testEmail); //email
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(testEmail); //confirm email

    }

    @And("I fill in password and confirm password")
    public void iFillInPasswordAndConfirmPassword() {
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(testPassword); //Password
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys(testPassword); //Confirm password

    }

    @And("I check I have read Terms and Conditions")
    public void iCheckIHaveReadTermsAndConditions() {
        driver.findElement(By.cssSelector("label[for='sign_up_25'] span[class='box']")).click(); //I have read, understood Terms and Conditions
        System.out.println("I have read and understood Terms and Conditions");
    }

    @And("I check I am over {int}")
    public void iCheckIAmOver(int arg0) {
        driver.findElement(By.cssSelector("label[for='sign_up_26'] span[class='box']")).click(); //I am aged over 18
        System.out.println("I am over 18");
    }

    @And("I check I have read Code of Conduct")
    public void iCheckIHaveReadCodeOfConduct() {
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] span[class='box']")).click(); //I have read, Understood Code of conduct
        System.out.println("I have read code of conduct");
    }

    @And("I press Confirm and join")
    public void iPressConfirmAndJoin() {
        driver.findElement(By.cssSelector(".btn")).click(); //Confirm and Join > Hoppa till ny sida
        System.out.println("I press Confirm and join");

    }

    @Then("I successfully become a member")
    public void iSuccessfullyBecomeAMember() {
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        String actual = driver.findElement(By.cssSelector("h2.gray")).getText();
        assertEquals(expected, actual);
        System.out.println("DET GICK!!!");
    }

    @When("I remove {string}")
    public void iRemove(String elementTobBeCleared) {
        driver.findElement(By.cssSelector("input#member_" + elementTobBeCleared)).clear(); //Last name
        System.out.println("I removed " + elementTobBeCleared);
    }

    @Then("I fail to become a new member {string}")
    public void iFailToBecomeANewMember(String expectedFailureMessage) {
        //.field-validation-error
        //[generated="true"]
        System.out.println("Jag börjar med att försöka hitta 'span[for='member_lastname']'");
        WebElement element = driver.findElement(By.cssSelector("span[for='member_lastname']"));
        String actual = element.getText();
        System.out.println(actual);
        assertEquals(expectedFailureMessage,actual);

        System.out.println("Jag blev inte en medlem för att: "+actual);

    }

    @Given("I am on basketballengland.co.uk on {string}")
    public void iAmOnBasketballenglandCoUkOn(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Okänd webbläsare: " + browser);
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }


    @And("I fill in password")
    public void iFillInPassword() {
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(testPassword); //Password

    }
}
