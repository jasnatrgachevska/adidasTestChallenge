Feature: API automated check on DEMO PET STORE

  @Smoke @run
    Scenario: DEMO Pet Store interactions
      Get "available" pets. Assert expected result.
      Post a new available pet to the store. Assert new pet added.
      Update this pet status to "sold". Assert status updated.
      Delete this pet. Assert deletion.

    When the user gets pets with status "available"
    Then the user gets status code "200"
    And the user asserts the list of pets is not empty

    When the user posts a new pet with category "Dog", name "Pancho", tags "BestFriend", status "available" to the store
    Then the user gets status code "200"

    When the user updates the previous pet to status "sold"
    Then the user gets status code "200"

    When the user deletes the previous pet
    Then the user gets status code "200"
