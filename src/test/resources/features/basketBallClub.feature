Feature:Test of regristration of users

  Scenario Outline: Add new user and everything works but as Scenario Outline
    Given I am on basketballengland.co.uk on "<browser>"
    When I fill in date of birth "<date of birth>"
    And I fill in first name "<first name>"
    And I fill in last name "<last name>"
    And I fill in email and confirm email
    And I fill in password and confirm password "<password>"
    And I check I have read Terms and Conditions "<terms accepted>"
    And I check I am over 18
    And I check I have read Code of Conduct
    And I press Confirm and join
    Then I successfully become a member
    Examples:
      | browser | date of birth | first name | last name | password          | terms accepted |
      | chrome  | 07/01/1995    | Urban      | Testman   | password          | true           |
      | firefox | 06/12/1989    | Pröv       | Aren      | Passsign_up_25    | true           |
      | edge    | 31/09/1953    | Kval       | Itet      | span[class='box'] | true           |
      | crome   | 07/01/1999    | Urban      | Itet      | passpassword      | true           |

  Scenario Outline: Add new user and it fails
    Given I am on basketballengland.co.uk on "<browser>"
    When I fill in date of birth "<date of birth>"
    And I fill in first name "<first name>"
    And I fill in last name "<last name>"
    And I fill in email and confirm email
    And I fill in password "<password>"
    And I fill in confirmed password "<confirmed password>"
    And I check I have read Terms and Conditions "<terms accepted>"
    And I check I am over 18
    And I check I have read Code of Conduct
    And I press Confirm and join
    Then I fail to become a new member because "<error message>"
    Examples:
      | browser        | date of birth | first name | last name | password       | confirmed password | terms accepted | error message                                                             |
      | fakeWebBrowser | 06/12/1989    | Pröv       |           | Passsign_up_25 | Passsign_up_25     | true           | Last Name is required                                                     |
      | firefox        | 07/01/1999    | Urban      | Itet      | passpassword   | Wrongpassword      | true           | Password did not match                                                    |
      | chrome         | 07/01/1999    | Urban      | Itet      | passpassword   | Wrongpassword      | true           | Password did not match                                                    |
      | chrome         | 07/01/1999    | Urban      | Itet      | passpassword   |                    | true           | Confirm Password is required                                              |
      | chrome         | 07/01/1999    | Urban      | Itet      | passpassword   | passpassword       | false          | You must confirm that you have read and accepted our Terms and Conditions |
      | edge           | 07/01/1999    | Urban      | Itet      | passpassword   | passpassword       | false          | You must confirm that you have read and accepted our Terms and Conditions |
      | chrome         | 07/01/1999    |            | Testman   | password       | password           | true           | First Name is required                                                    |
      | edge           | 31/09/1953    | Kval       | Itet      |                | password           | true           | Password is required                                                      |

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