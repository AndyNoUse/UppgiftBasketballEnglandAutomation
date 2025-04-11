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
    private String testPassword = "password";

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
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
        String testEmail = "test+" + System.currentTimeMillis() + "@mailnesia.com";
        System.out.println(testEmail);
        String testPassword = "password";
        driver.findElement(By.cssSelector("input#dp")).sendKeys("07/01/1999"); //Date of Birth DD/MM/YYYY
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Urban"); //First Name
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("Test"); //Last name
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(testEmail); //email
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(testEmail); //confirm email
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(testPassword); //Password
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys(testPassword); //Confirm password

        driver.findElement(By.cssSelector("label[for='sign_up_25'] span[class='box']")).click(); //I have read, understood Terms and Conditions
        System.out.println("I have read and understood Terms and Conditions");
        driver.findElement(By.cssSelector("label[for='sign_up_26'] span[class='box']")).click(); //I am aged over 18
        System.out.println("I am over 18");
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] span[class='box']")).click(); //I have read, Understood Code of conduct
        System.out.println("I have read code of conduct");
    }


    @Then("I fail to become a new member because {string}")
    public void iFailToBecomeANewMemberBecause(String expectedFailureMessage) {
        //System.out.println("Jag börjar med att försöka hitta felmeddelandet");
        WebElement element = driver.findElement(By.cssSelector("span[generated=\"true\"]"));
        String actual = element.getText();
        //System.out.println(actual);

        assertEquals(expectedFailureMessage, actual);

        System.out.println("Jag blev inte en medlem för att: " + actual);
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
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(testEmail);
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(testEmail);
    }

    @And("I fill in password and confirm password")
    public void iFillInPasswordAndConfirmPassword() {
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(testPassword);
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys(testPassword);
    }

    @And("I check I have read Terms and Conditions")
    public void iCheckIHaveReadTermsAndConditions() {
        //driver.findElement(By.cssSelector("label[for='sign_up_25'] span[class='box']")).click(); //I have read, understood Terms and Conditions
        driver.findElement(By.cssSelector("label[for='sign_up_25']")).click(); //I have read, understood Terms and Conditions
        System.out.println("I have read and understood Terms and Conditions");
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

    private void waitToBeDisplayed(String css) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
    }

    @When("I remove {string}")
    public void iRemove(String elementTobBeCleared) {
        driver.findElement(By.cssSelector("input#member_" + elementTobBeCleared)).clear(); //Last name
        System.out.println("I removed " + elementTobBeCleared);
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

    @And("I fill in password")
    public void iFillInPassword() {
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(testPassword); //Password only
    }

    @When("I fill in date of birth {string}")
    public void iFillInDateOfBirth(String dateOfBirth) {
        driver.findElement(By.cssSelector("input#dp")).sendKeys(dateOfBirth); //Date of Birth DD/MM/YYYY
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
    }
}
