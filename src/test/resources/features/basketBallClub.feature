Feature:Test of regristration of users

  Scenario: Add new user and everything works as expected
    Given I am on basketballengland.co.uk
    When I fill in the correct member details
    And I press Confirm and join
    Then I successfully become a member

  Scenario: Add new user but lastname is missing
    Given I am on basketballengland.co.uk
    When I fill in the correct member details
    And  I remove "lastname"
    And I press Confirm and join
    Then I fail to become a new member because "Last Name is required"

  Scenario: Add new user but password does not match
    Given I am on basketballengland.co.uk
    When I fill in date of birth
    And I fill in first name
    And I fill in last name
    And I fill in email and confirm email
    And I fill in password
    And I fill in wrong confirmed password
    And I check I have read Terms and Conditions
    And I check I am over 18
    And I check I have read Code of Conduct
    And I press Confirm and join
    Then I fail to become a new member because "Password did not match"

  Scenario: Add new user Terms and conditions not accepted
    Given I am on basketballengland.co.uk
    When I fill in date of birth
    And I fill in first name
    And I fill in last name
    And I fill in email and confirm email
    And I fill in password and confirm password
    And I check I am over 18
    And I check I have read Code of Conduct
    And I press Confirm and join
    Then I fail to become a new member because "You must confirm that you have read and accepted our Terms and Conditions"

  Scenario Outline: Add new user and everything works but as Scenario Outline
    Given I am on basketballengland.co.uk on "<browser>"
    When I fill in date of birth "<date of birth>"
    And I fill in first name "<first name>"
    And I fill in last name "<last name>"
    And I fill in email and confirm email
    And I fill in password and confirm password "<password>"
    And I check I have read Terms and Conditions
    And I check I am over 18
    And I check I have read Code of Conduct
    And I press Confirm and join
    Then I successfully become a member
    Examples:
      | browser | date of birth | first name | last name | password          |
      | chrome  | 07/01/1995    | Urban      | Testman   | password          |
      | firefox | 06/12/1989    | Pröv       | Aren      | Passsign_up_25    |
      | edge    | 31/09/1953    | Kval       | Itet      | span[class='box'] |
      | crome   | 07/01/1999    | Urban      | Itet      | passpassword      |

  Scenario Outline: Add new user and it fails
    Given I am on basketballengland.co.uk on "<browser>"
    When I fill in date of birth "<date of birth>"
    And I fill in first name "<first name>"
    And I fill in last name "<last name>"
    And I fill in email and confirm email
    And I fill in password "<password>"
    And I fill in confirmed password "<confirmed password>"
    And I check I have read Terms and Conditions
    And I check I am over 18
    And I check I have read Code of Conduct
    And I press Confirm and join
    Then I fail to become a new member because "<error message>"
    Examples:
      | browser | date of birth | first name | last name | password       | confirmed password | error message                |
      | chrome  | 07/01/1999    |            | Testman   | password       | password           | First Name is required       |
      | firefox | 06/12/1989    | Pröv       |           | Passsign_up_25 | Passsign_up_25     | Last Name is required        |
      | edge    | 31/09/1953    | Kval       | Itet      |                | password           | Password is required         |
      | crome   | 07/01/1999    | Urban      | Itet      | passpassword   | passpasspass       | Password did not match       |
      | chrome  | 07/01/1999    | Urban      | Itet      | passpassword   |                    | Confirm Password is required |
    #På
    #https://membership.basketballengland.co.uk/NewSupporterAccount
    # sig som användare
    # kan man registrera
    #❖ Skriv testfall som testar denna
    #registreringsfunktion genom automatisering
    #via Cucumber och Selenium
    # ❖ Testfallen skall täcka kraven på kommande
    #slides

    # ❖ Skapa användare – allt går som förväntat och ett konto skapas X
    # ❖ Skapa användare – efternamn saknas                            X
    # ❖ Skapa användare – lösenord matchar inte                       X
    # ❖ Skapa användare – terms and conditions är inte godkänt        X

    # ❖ Verifiering skall ske på varje scenario med minst en Junit assert               X
    # ❖ Feature-filen skall vara skapad enligt BDD och innehålla en tydlig beskrivning  X
    # ❖ Feature-filen skall kopplas till Selenium Webdriver-kod som utför testandet     X

  # ❖ Testfallen skall köras med minst en Scenario Outline            X
  # ❖ Testfallen skall köras på minst två browsrar                    X
  # ❖ Skapa minst en privat metod som använder sig av explicit wait   X