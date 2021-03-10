Feature: Purchase in a demo shop

  @run
  Scenario: Purchase in a demo shop
    Given The user navigates to DemoblazePage
    And she navigates to category "Laptops" and item "Sony vaio i5"
    And she adds "it" to cart by clicking "Add to cart"
    And she accepts pop up confirmation
    And she navigates to DemoblazePage
    And she navigates to category "Laptops" and item "Dell i7 8gb"
    And she adds "it" to cart by clicking "Add to cart"
    And she accepts pop up confirmation
    And she navigates to "Cart"
    And she deletes "Dell i7 8gb" from cart
    And she places order and remembers the amount
    And she fills in all web form fields
    And she clicks on "Purchase"
    And she captures and log "purchase ID" and "Amount"
    Then she asserts purchase amount equals expected
    And she clicks on "OK"



