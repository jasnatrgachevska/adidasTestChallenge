package com.petstore.gherkinsDefinitions;

import com.petstore.model.Pet;
import com.petstore.serenitySteps.CommonSteps;
import com.petstore.serenitySteps.PetApiSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import java.io.IOException;
import java.util.List;


/**
 * This class contains steps for features specific for pet
 */
public class PetStepsDefinitions {

    @Steps
    public PetApiSteps petApiSteps;

    @When("^the user gets pets with status \"([^\"]*)\"$")
    public void theUserGetsPetWithStatus(List<String> status) throws Throwable {
        petApiSteps.findPetsByStatusStep(status);
    }

    @And("^the user posts a new pet with category \"([^\"]*)\", name \"([^\"]*)\", tags \"([^\"]*)\", status \"([^\"]*)\" to the store$")
    public void theUserPostsANewPetWithCategoryNameTagStatusToTheStore(String category, String name, List<String> tags, String status) throws Throwable {
        petApiSteps.postNewPetWithParams(category, name, tags, status);
    }

    @When("^the user updates the previous pet to status \"([^\"]*)\"$")
    public void theUserUpdatesThePreviousPetToStatus(String status) throws Throwable {
        petApiSteps.updatePreviousPetToStatus(status);
        // Write code here that turns the phrase above into concrete actions
    }

    @When("^the user deletes the previous pet$")
    public void theUserDeletesThePreviousPet() throws IOException {
        petApiSteps.deletePreviousPet();
    }

    @And("^the user asserts the list of pets is not empty$")
    public void theUserAssertsTheListOfPetsIsNotEmpty() {
        List<Pet> petList = petApiSteps.getPreviousListOfPets();
        CommonSteps.assertListIsNotEmpty(petList);
    }

    @And("^the user asserts the previous pet is in the list$")
    public void theUserAssertsThePreviousPetIsInTheList() {
        petApiSteps.assertPetInList();

    }

    @And("^the user gets the \"([^\"]*)\" pet$")
    public void theUserGetsThePet(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}

