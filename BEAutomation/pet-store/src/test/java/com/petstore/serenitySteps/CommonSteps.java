package com.petstore.serenitySteps;
import com.petstore.model.Pet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.decorators.request.RequestSpecificationDecorated;
import static net.serenitybdd.rest.SerenityRest.rest;
import com.petstore.support.Service;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


/**
 * This class contains implementations for the CommonDefinitions
 */
public class CommonSteps {

    public static final String RESPONSE = "response";
    private static RequestSpecification requestSpecification = rest();

    /**
     * Method to create a request
     */
    @Step
    public static void sendRequest(String method, String endpoint) {
        Serenity.setSessionVariable("REQUEST-HEADERS").to((String) ((RequestSpecificationDecorated) requestSpecification).getHeaders().toString());
        Serenity.setSessionVariable("REQUEST-METHOD").to(method);
        Serenity.setSessionVariable("REQUEST-URL").to(RestAssured.baseURI + endpoint);

        Response response = Service.sendRequest(requestSpecification, method, endpoint)
                .then()
                .extract()
                .response();

        Serenity.setSessionVariable(RESPONSE).to(response);
        requestSpecification = rest();
    }

    /**
     * Method to create a request with authentication
     */

    @Step
    public static void sendRequestWithAuthentication(String method, String endpoint) throws IOException {
        requestSpecification.header("Content-Type", "application/json");
        setAuthToken();
        sendRequest(method, endpoint);
    }

    /**
     * Method to create a request with Json Body
     */

    @Step
    public static void sendRequestWithJsonBody(String method, String endpoint, JSONObject body)  {
        Map<String, Object> bodyMap = body.toMap();
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body(bodyMap);
        sendRequest(method, endpoint);
    }

    /**
     * Method to create a request with query params
     */

    @Step
    public static void sendRequestwithQueryParam(String method, String endpoint, List<String> params){
        for (String param: params) {
            requestSpecification.queryParam("status", param);
        }
        sendRequest(method, endpoint);
    }

    /**
     * Method to create a request with authentication
     */

    @Step
    public static void assertStatusCode(int expectedStatusCode) {
        Response res = Serenity.sessionVariableCalled(RESPONSE);

        if (res.getStatusCode() != expectedStatusCode) {
            System.err.println(" Response Body:");
            res.getBody().prettyPrint();
        }

        Assert.assertEquals("status code doesn't match," +
                "\nRequest:\nHeaders: " + Serenity.sessionVariableCalled("REQUEST-HEADERS") + "\nMethod: " + Serenity.sessionVariableCalled("REQUEST-METHOD") +
                "\nUrl: " + Serenity.sessionVariableCalled("REQUEST-URL") +
                "\nResponse:\nHeaders: " + res.getHeaders() + "\n ~Name: " + "~" + "~\n", expectedStatusCode, res.getStatusCode());
    }

    /**
     * Method to set auth token
     */

    public static void setAuthToken() throws IOException {
        RequestSpecification requestSpecification1 = rest();
        HashMap<String, String> hashMap = new HashMap();

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));

        hashMap.put("api_key", prop.getProperty("api_key"));

        requestSpecification1.headers(hashMap);
        requestSpecification = requestSpecification1;
    }

    /**
     * Method to create a request with authentication
     */

    @Step
    public static void assertListIsNotEmpty(List list ) {
        Assert.assertTrue(list.size() > 0 );
    }

    /**
     * Method to create a request with authentication
     */

    @Step
    public static void assertListContainsElement(List<Pet> list, Pet pet ) {
        Assert.assertTrue(list.contains(pet));
    }
}
