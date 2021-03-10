package com.petstore.serenitySteps;
import com.petstore.model.Pet;
import com.vladsch.flexmark.util.collection.OrderedMultiMap;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class PetApiSteps {
    public static final String RESPONSE = "response";
    public static Pet sessionPet;
    /**
     * Method which is used to find peets by status
     */

    @Step
    public void findPetsByStatusStep(List<String> status) throws IOException {
        CommonSteps.sendRequestwithQueryParam("GET", "pet/findByStatus", status);
    }

    /**
     * Method which is used to post new pet with params
     */

    @Step
    public void postNewPetWithParams(String category, String name, List<String> tags, String status) throws IOException {
        Pet pet = new Pet(category, name, tags, status);
        CommonSteps.sendRequestWithJsonBody("POST", "/pet", pet.toJsonObject());
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        sessionPet = Pet.getPetFromSessionVariable(response);
    }

    /**
     * Method which is used to update the previous pet status
     */

    @Step
    public void updatePreviousPetToStatus(String status) throws IOException {
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        Pet pet = Pet.changePetStatus(response,status);
        CommonSteps.sendRequestWithJsonBody("PUT","/pet", pet.toJsonObject());
    }

    /**
     * Method which is used to delete the previous pet
     */

    @Step
    public void deletePreviousPet() throws IOException {
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        CommonSteps.sendRequestWithAuthentication("DELETE", "/pet/"+Pet.getPetFromSessionVariable(response).getId()) ;
    }

    public List<Pet> getPreviousListOfPets() {
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        return Pet.getPetListFromSessionVariable(response);
    }

    public void assertPetInList() {
        CommonSteps.assertListContainsElement(getPreviousListOfPets(), sessionPet);
    }
}
