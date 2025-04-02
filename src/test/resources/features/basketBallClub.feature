Feature:Test of regristration of users

  Scenario: Add new user and everything works as expected
    Given I am on basketballengland.co.uk
    When I fill in the correct member details
    Then I successfully become a member

  Scenario: Add new user but lastname is missing
    Given I am on basketballengland.co.uk
    When I fill in the correct member details
    And  I remove "lastname"
    Then I fail to become a new member

  #Scenario: Add new user but password does not match
    Given I am on basketballengland.co.uk


 # Scenario: Add new user Terms and conditions not accepted
    Given I am on basketballengland.co.uk


  Scenario Outline: Add new user and everything works as expected on different browsers
    Given I am on basketballengland.co.uk on <browser>
    When I fill in the correct member details
    Then I successfully become a member
    Examples:
      | browser |
      | chrome  |
      | firefox |
      | edge    |

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
    # ❖ Skapa användare – efternamn saknas
    # ❖ Skapa användare – lösenord matchar inte
    # ❖ Skapa användare – terms and conditions är inte godkänt

    # ❖ Verifiering skall ske på varje scenario med minst en Junit assert
    # ❖ Feature-filen skall vara skapad enligt BDD och innehålla en tydlig beskrivning
    # ❖ Feature-filen skall kopplas till Selenium Webdriver-kod som utför testandet

  # ❖ Testfallen skall köras med minst en Scenario Outline
  # ❖ Testfallen skall köras på minst två browsrar
  # ❖ Skapa minst en privat metod som använder sig av explicit wait