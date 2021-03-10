package com.petstore.gherkinsDefinitions;

import com.petstore.serenitySteps.CommonSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import java.util.List;

/**
 * This is class contains gherkin definitions which are used in multiple features and are common
 */
public class CommonDefinitions {


    @Then("^the user gets status code \"([^\"]*)\"$")
    public void assertStatusCode(int statusCode) {
        CommonSteps.assertStatusCode(statusCode);
    }
}
